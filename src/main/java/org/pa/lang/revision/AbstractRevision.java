package org.pa.lang.revision;

public abstract class AbstractRevision<TT> {

	private volatile AbstractRevision<TT> next;

	public abstract void apply(TT target);

	public AbstractRevision<TT> getNext() {
		return next;
	}

	public AbstractRevision<TT> setNext(AbstractRevision<TT> revision) {
		this.next = revision;
		return revision;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("Revision [next=");
		result.append(next == null ? "unset" : "set");
		return result.append("]").toString();
	}


}
