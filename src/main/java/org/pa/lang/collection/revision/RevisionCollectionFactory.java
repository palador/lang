package org.pa.lang.collection.revision;

import java.util.ArrayList;
import java.util.List;


public final class RevisionCollectionFactory {

	public CollectionType getCollectionType() {
		return CollectionType.LIST;
	}

	protected Object createInnerMasterCollection() {
		return new ArrayList<Object>();
	}

	@SuppressWarnings("unchecked")
	protected Object createInnerSlaveCollection(Object innerMasterCollection) {
		return new ArrayList<Object>((List<Object>) innerMasterCollection);
	}
	
	@SuppressWarnings("unchecked")
	protected RevisionControlSlave createSlave(Object innserSlaveCollection,
			Revision<Object> latestRevision) {
		return new SlaveList<Object>((List<Object>) innserSlaveCollection,
				latestRevision);
	}

}
