package org.pa.lang.collection.revision;

import java.util.List;

public interface MasterSet<E> extends List<E>, MasterCollection<E> {

	@Override
	public SlaveSet<E> createSlave();

}
