package org.pa.lang.collection.revision;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.pa.lang.revision.AbstractRevision;
import org.pa.lang.util.Validation;

public class MasterList<E> extends AbstractMasterCollection<E, List<E>>
		implements List<E> {

	MasterList(RevisionCollectionFactory factory)
			throws IllegalArgumentException {
		super(factory);
		Validation.isTrue(factory.getCollectionType() == CollectionType.LIST,
				"Factory doesn't support lists", null);
	}

	@Override
	public SlaveList<E> createSlave() {
		return (SlaveList<E>) super.createSlave();
	}

	@Override
	public void add(int index, E element) {
		innerCollection.add(index, element);
		newRevision(new AddAtIndexRevision(element, index));
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		boolean result = c.size() > 0;
		Iterator<? extends E> iter = c.iterator();
		while (iter.hasNext()) {
			E next = iter.next();
			innerCollection.add(index, next);
			newRevision(new AddAtIndexRevision(next, index));
			index++;
		}
		return result;
	}

	@Override
	public E remove(int index) {
		E result = innerCollection.remove(index);
		newRevision(new RemoveAtIndexRevision(index));
		return result;
	}

	@Override
	public E set(int index, E element) {
		E result = innerCollection.set(index, element);
		newRevision(new SetAtIndexRevision(element, index));
		return result;
	}

	@Override
	public Iterator<E> iterator() {
		return null; // TODO implement iterator
	}

	@Override
	public ListIterator<E> listIterator() {
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return null;
	}

	@Override
	public E get(int index) {
		return innerCollection.get(index);
	}

	@Override
	public int indexOf(Object o) {
		return innerCollection.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return innerCollection.lastIndexOf(o);
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return innerCollection.subList(fromIndex, toIndex);
	}

	protected final class AddAtIndexRevision extends AbstractRevision<List<E>> {
		private final E item;
		private final int index;

		protected AddAtIndexRevision(E item, int index) {
			this.item = item;
			this.index = index;
		}

		@Override
		public void apply(List<E> target) {
			target.add(index, item);
		}
	}

	protected final class RemoveAtIndexRevision extends
			AbstractRevision<List<E>> {
		private final int index;

		protected RemoveAtIndexRevision(int index) {
			this.index = index;
		}

		@Override
		public void apply(List<E> target) {
			target.remove(index);
		}
	}

	protected final class SetAtIndexRevision extends AbstractRevision<List<E>> {
		private final E item;
		private final int index;

		protected SetAtIndexRevision(E item, int index) {
			this.item = item;
			this.index = index;
		}

		@Override
		public void apply(List<E> target) {
			target.set(index, item);
		}
	}

}
