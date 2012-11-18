package org.pa.lang.collection.revision;

public interface RevisonControlMaster {

	public RevisionControlSlave createSlave();

	public long getRevisionNumber();

}
