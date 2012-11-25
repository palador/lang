package org.pa.lang.collection.revision;

import static org.pa.lang.util.Validation.notNull;

import java.util.Map;

public final class Revision<E> {

	private volatile Revision<E> next;
	private final ModificationOperation modOp;
	private final int index;
	private final E value;

	public Revision(ModificationOperation modOp, int index, E value) {
		this.modOp = notNull(modOp, "modOp");
		this.value = value;
		this.index = index;
	}

	public Revision<E> getNext() {
		return next;
	}

	public Revision<E> setNext(Revision<E> next) {
		this.next = next;
		return next;
	}

	public ModificationOperation getModOp() {
		return modOp;
	}

	public int getIndex() {
		return index;
	}

	public E getValue() {
		return value;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("Revision [next=");
		result.append(next == null ? "unset" : "set").append(", modOp=");
		result.append(modOp).append(", ");
		if (index >= 0) {
			result.append("index=").append(index).append(", ");
		}
		if (value != null && value instanceof Map.Entry) {
			Map.Entry<?, ?> entry = (Map.Entry<?, ?>) value;
			result.append("key=").append(entry.getKey()).append(", ")
					.append("value=").append(entry.getValue());
		} else {
			result.append("value=").append(value);
		}
		return result.append("]").toString();
	}

}
