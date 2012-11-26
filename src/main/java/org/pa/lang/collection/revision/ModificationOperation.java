package org.pa.lang.collection.revision;


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
}
