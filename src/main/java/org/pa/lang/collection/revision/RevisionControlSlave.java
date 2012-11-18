package org.pa.lang.collection.revision;

public interface RevisionControlSlave {

	public RevisonControlMaster getMaster();

	public long getRevisionNumber();

	public void setAutoUpdateEnabled(boolean enabled);
	
	public boolean isAutoUpdateEnabled();

}
