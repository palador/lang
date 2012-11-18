package org.pa.lang.collection.revision;

import java.util.Comparator;


public final class RevisionCollectionFactory<E, V> {

	public RevisionCollectionFactory(Class<E> entryType)
			throws IllegalArgumentException {
	}

	public RevisionCollectionFactory(Class<E> keyType, Class<V> valueType)
			throws IllegalArgumentException {
	}

	public RevisionCollectionFactory<E, V> setMasterType(Class<?> type)
			throws IllegalArgumentException {
		return this;
	}

	public RevisionCollectionFactory<E, V> setSlaveType(Class<?> type)
			throws IllegalArgumentException {
		return this;
	}

	public RevisionCollectionFactory<E, V> setComparator(
			Comparator<E> comparator) {
		return this;
	}

	public MasterCollection<E> createMasterCollection() {
		return null;
	}

	public MasterList<E> createMasterList() throws IllegalStateException {
		return null;
	}

	public MasterSet<E> createMasterSet() throws IllegalStateException {
		return null;
	}

	public MasterMap<E, V> createMasterMap() throws IllegalStateException {
		return null;
	}

}
