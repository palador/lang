package org.pa.lang.collection.revision;

import java.util.Collection;

public interface MasterCollection<E> extends RevisonControlMaster,
		Collection<E> {

	@Override
	public SlaveCollection<E> createSlave();

}
