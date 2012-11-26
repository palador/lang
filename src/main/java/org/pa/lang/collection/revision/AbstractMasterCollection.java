package org.pa.lang.collection.revision;

import static org.pa.lang.util.Validation.isTrue;
import static org.pa.lang.util.Validation.notNull;

import java.util.Collection;
import java.util.List;

import org.pa.lang.revision.AbstractRevision;
import org.pa.lang.revision.NullRevision;

public abstract class AbstractMasterCollection<E, CT extends Collection<E>>
		implements MasterCollection<E> {

	protected final RevisionCollectionFactory factory;
	protected final CT innerCollection;
	private AbstractRevision<CT> currentRevision;

	@SuppressWarnings("unchecked")
	AbstractMasterCollection(RevisionCollectionFactory factory)
			throws IllegalArgumentException {

		this.factory = notNull(factory, "factory");
		isTrue(Collection.class.isAssignableFrom(factory.getCollectionType()
				.getCollectionInterface()),
				"Factory collection type not compatible to collection interface: ",
				factory.getCollectionType());
		this.innerCollection = (CT) factory.createInnerMasterCollection();

		currentRevision = new NullRevision<CT>();
	}

	protected void newRevision(AbstractRevision<CT> revision) {
		// TODO nullcheck
		currentRevision = currentRevision.setNext(revision);
	}

	//
	//
	//
	// TODO simplify factory: only one type
	// TODO add transactions
	//
	//
	//

	@SuppressWarnings("unchecked")
	@Override
	public SlaveCollection<E> createSlave() {
		try {
			// init inner slave collection with data from master
			CT innerSlaveCollection = (CT) factory
					.createInnerSlaveCollection(innerCollection);

			// init slave with current revision
			SlaveCollection<E> result = (SlaveCollection<E>) factory
					.createSlave(innerSlaveCollection,
							(AbstractRevision<List<Object>>) currentRevision);

			return result;
		} catch (Exception e) {
			throw new RuntimeException("Instantiation of slave failed", e);
		}
	}

	public boolean add(E e) {
		boolean hasChanged = innerCollection.add(e);
		if (hasChanged) {
			newRevision(new AddRevision(e));
		}
		return hasChanged;
	}

	public boolean addAll(Collection<? extends E> c) {
		boolean result = false;
		for (E e : c) {
			result = add(e) || result;
		}
		return result;
	}

	public void clear() {
		if (!innerCollection.isEmpty()) {
			innerCollection.clear();
			newRevision(new ClearRevision());
		}
	}

	public boolean remove(Object o) {
		boolean hasChanged = innerCollection.remove(o);
		if (hasChanged) {
			newRevision(new RemoveRevision(o));
		}
		return hasChanged;
	}

	public boolean removeAll(Collection<?> c) {
		boolean result = false;
		for (Object o : c) {
			result = remove(o) || result;
		}
		return result;
	}

	public boolean retainAll(Collection<?> c) {
		boolean result = false;
		for (Object o : c) {
			if (!innerCollection.contains(o)) {
				result = remove(o) || result;
			}
		}
		return result;
	}

	public boolean contains(Object o) {
		return innerCollection.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return innerCollection.containsAll(c);
	}

	public boolean equals(Object o) {
		return innerCollection.equals(o);
	}

	public int hashCode() {
		return innerCollection.hashCode();
	}

	public boolean isEmpty() {
		return innerCollection.isEmpty();
	}

	public int size() {
		return innerCollection.size();
	}

	public Object[] toArray() {
		return innerCollection.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return innerCollection.toArray(a);
	}

	protected final class AddRevision extends AbstractRevision<CT> {
		private final E item;

		protected AddRevision(E item) {
			this.item = item;
		}

		@Override
		public void apply(CT target) {
			target.add(item);
		}
	}

	protected final class RemoveRevision extends AbstractRevision<CT> {
		private final Object item;

		protected RemoveRevision(Object item) {
			this.item = item;
		}

		@Override
		public void apply(CT target) {
			target.remove(item);
		}
	}

	protected final class ClearRevision extends AbstractRevision<CT> {
		@Override
		public void apply(CT target) {
			target.clear();
		}
	}

}
