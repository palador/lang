package org.pa.lang.revision;

public class NullRevision<TT> extends AbstractRevision<TT> {
	@Override
	public void apply(TT target) {
	}
}
