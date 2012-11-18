package org.pa.lang.collection.revision;

import java.util.List;

public interface SlaveList<E> extends List<E>, SlaveCollection<E> {

	@Override
	public MasterList<E> getMaster();

}
