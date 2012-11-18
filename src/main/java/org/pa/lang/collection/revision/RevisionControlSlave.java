package org.pa.lang.collection.revision;

public interface RevisionControlSlave {

	public RevisonControlMaster getMaster();

	public long getRevisionNumber();

}
