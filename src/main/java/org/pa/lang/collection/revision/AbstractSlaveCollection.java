package org.pa.lang.collection.revision;

import static org.pa.lang.util.Validation.notNull;

import java.util.Collection;
import java.util.Iterator;

import org.pa.lang.collection.UnmodifiableIterator;
import org.pa.lang.revision.AbstractRevision;

public abstract class AbstractSlaveCollection<E, IC extends Collection<E>>
		implements SlaveCollection<E> {

	protected final IC innerCollection;
	protected AbstractRevision<IC> currentRevision;

	private boolean isAutoUpdateEnabled = true;

	AbstractSlaveCollection(IC innerCollection,
			AbstractRevision<IC> initialRevision)
			throws IllegalArgumentException {
		this.innerCollection = notNull(innerCollection, "innerCollection");
		this.currentRevision = notNull(initialRevision, "currentRevision");
	}

	protected boolean autoUpdate() {
		return isAutoUpdateEnabled && update();
	}

	@Override
	public boolean isAutoUpdateEnabled() {
		return isAutoUpdateEnabled;
	}

	@Override
	public void setAutoUpdateEnabled(boolean enabled) {
		this.isAutoUpdateEnabled = enabled;
	}

	@Override
	public boolean update() {
		boolean result = false;
		AbstractRevision<IC> nextRev = currentRevision;
		while ((nextRev = nextRev.getNext()) != null) {
			nextRev.apply(innerCollection);
			currentRevision = nextRev;
			result = true;
		}
		return result;
	}

	public boolean add(E e) {
		throw new UnsupportedOperationException();
	}

	public boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	public void clear() {
		throw new UnsupportedOperationException();
	}

	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	public boolean contains(Object o) {
		autoUpdate();
		return innerCollection.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		autoUpdate();
		return innerCollection.containsAll(c);
	}

	public boolean equals(Object o) {
		autoUpdate();
		return innerCollection.equals(o);
	}

	public int hashCode() {
		autoUpdate();
		return innerCollection.hashCode();
	}

	public boolean isEmpty() {
		autoUpdate();
		return innerCollection.isEmpty();
	}

	public Iterator<E> iterator() {
		autoUpdate();
		return new UnmodifiableIterator<>(innerCollection.iterator());
	}

	public int size() {
		autoUpdate();
		return innerCollection.size();
	}

	public Object[] toArray() {
		autoUpdate();
		return innerCollection.toArray();
	}

	public <T> T[] toArray(T[] a) {
		autoUpdate();
		return innerCollection.toArray(a);
	}

}
