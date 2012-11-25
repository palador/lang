package org.pa.lang.collection.revision;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
		newRevision(ModificationOperation.ADD_AT_INDEX, index, element);
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		boolean result = c.size() > 0;
		Iterator<? extends E> iter = c.iterator();
		while (iter.hasNext()) {
			E next = iter.next();
			innerCollection.add(index, next);
			newRevision(ModificationOperation.ADD_AT_INDEX, index, next);
			index++;
		}
		return result;
	}

	@Override
	public E remove(int index) {
		E result = innerCollection.remove(index);
		newRevision(ModificationOperation.REMOVE_AT_INDEX, index, null);
		return result;
	}

	@Override
	public E set(int index, E element) {
		E result = innerCollection.set(index, element);
		newRevision(ModificationOperation.SET_AT_INDEX, index, element);
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

}
