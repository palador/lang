package org.pa.lang.collection.revision;

import java.util.List;
import java.util.Map;
import java.util.Set;

public enum CollectionType {
	LIST(List.class), SET(Set.class), MAP(Map.class);

	private final Class<?> collectionInterface;

	private CollectionType(Class<?> collectionInterface) {
		this.collectionInterface = collectionInterface;
	}

	public Class<?> getCollectionInterface() {
		return collectionInterface;
	}
}
