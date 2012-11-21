package org.pa.lang.collection.revision;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public enum ModificationOperation {
	/*
	 * Generic Collections and Sets
	 */
	INIT, ADD, CLEAR, REMOVE,
	/*
	 * Lists
	 */
	ADD_AT_INDEX, REMOVE_AT_INDEX, SET_AT_INDEX,
	/*
	 * Maps
	 */
	PUT;
	
	public <E> void applyToCollection(Collection<E> collection, E value)
			throws UnsupportedOperationException {
		switch (this) {
		case INIT:
			// just be happy
			break;
		case ADD:
			collection.add(value);
			break;
		case CLEAR:
			collection.clear();
			break;
		case REMOVE:
			collection.remove(value);
			break;

		default:
			String typeCategory = collection instanceof List ? "lists"
					: collection instanceof Set ? "sets"
							: "generic collections";
			throw new UnsupportedOperationException(
					"Operation not supported on " + typeCategory + ": " + this);
		}
	}

	public <E> void applyToList(List<E> list, int index, E value)
			throws UnsupportedOperationException {
		switch (this) {
		case ADD_AT_INDEX:
			list.add(index, value);
			break;
		case REMOVE_AT_INDEX:
			list.remove(index);
			break;
		case SET_AT_INDEX:
			list.set(index, value);
			break;

		default:
			applyToCollection(list, value);
			break;
		}
	}

	public <K, V> void applyToMap(Map<K, V> map, K key, V value)
			throws UnsupportedOperationException {
		switch (this) {
		case INIT:
			// lalallalaa
			break;
		case PUT:
			map.put(key, value);
			break;
		case CLEAR:
			map.clear();
			break;
		case REMOVE:
			map.remove(key);
			break;

		default:
			throw new UnsupportedOperationException(
					"Operation not supported on maps: " + this);
		}
	}
}
