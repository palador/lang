package org.pa.lang.collection.revision;

import java.util.List;

public interface MasterList<E> extends List<E>, MasterCollection<E> {

	@Override
	public SlaveList<E> createSlave();

}
