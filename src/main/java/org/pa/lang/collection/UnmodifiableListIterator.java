package org.pa.lang.collection;

import static org.pa.lang.util.Validation.notNull;

import java.util.ListIterator;

public class UnmodifiableListIterator<E> implements ListIterator<E> {

	private final ListIterator<E> innerIterator;

	public UnmodifiableListIterator(ListIterator<E> innerIterator)
			throws IllegalArgumentException {
		this.innerIterator = notNull(innerIterator, "innerIterator");
	}

	@Override
	public void add(E e) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void set(E e) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasNext() {
		return innerIterator.hasNext();
	}

	@Override
	public boolean hasPrevious() {
		return innerIterator.hasPrevious();
	}

	@Override
	public E next() {
		return innerIterator.next();
	}

	@Override
	public int nextIndex() {
		return innerIterator.nextIndex();
	}

	@Override
	public E previous() {
		return innerIterator.previous();
	}

	@Override
	public int previousIndex() {
		return innerIterator.previousIndex();
	}

}
