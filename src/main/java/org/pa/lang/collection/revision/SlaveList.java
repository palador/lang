package org.pa.lang.collection.revision;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import org.pa.lang.collection.UnmodifiableListIterator;

public class SlaveList<E> extends AbstractSlaveCollection<E, List<E>> implements
		List<E> {

	SlaveList(List<E> innerCollection, Revision<E> initialRevision)
			throws IllegalArgumentException {
		super(innerCollection, initialRevision);
	}

	@Override
	protected void applyRevision(Revision<E> revision) {
		revision.getModOp().applyToList(innerCollection, revision.getIndex(),
				revision.getValue());
	}

	@Override
	public void add(int index, E element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E remove(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E set(int index, E element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E get(int index) {
		autoUpdate();
		return innerCollection.get(index);
	}

	@Override
	public int indexOf(Object o) {
		autoUpdate();
		return innerCollection.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		autoUpdate();
		return innerCollection.lastIndexOf(o);
	}

	@Override
	public ListIterator<E> listIterator() {
		autoUpdate();
		return new UnmodifiableListIterator<>(innerCollection.listIterator());
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		autoUpdate();
		return new UnmodifiableListIterator<>(
				innerCollection.listIterator(index));
	}

	@Override
	public SlaveList<E> subList(int fromIndex, int toIndex) {
		autoUpdate();
		return null; // TODO implement sublist
	}
}
