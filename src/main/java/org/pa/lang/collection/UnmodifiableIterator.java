package org.pa.lang.collection;

import static org.pa.lang.util.Validation.notNull;

import java.util.Iterator;

public class UnmodifiableIterator<E> implements Iterator<E> {

	private final Iterator<E> innerIterator;

	public UnmodifiableIterator(Iterator<E> innerIterator)
			throws IllegalArgumentException {
		this.innerIterator = notNull(innerIterator, "innerIterator");
	}

	@Override
	public boolean hasNext() {
		return innerIterator.hasNext();
	}

	@Override
	public E next() {
		return innerIterator.next();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
