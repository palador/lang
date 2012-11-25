package org.pa.lang.collection.revision;

import java.util.Collection;

// TODO support transactions
public interface Transaction<E, C extends Collection<E>> {

	/**
	 * Performs a trasaction (a sequence of multiple read and write operations).
	 * 
	 * @param collection
	 *            the collection to perform the operations on
	 * @throws if
	 *             something fails. If so, all changes won't be applied
	 */
	public void perform(C collection) throws RuntimeException;

}
