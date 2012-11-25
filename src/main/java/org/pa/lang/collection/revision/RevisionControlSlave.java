package org.pa.lang.collection.revision;

public interface RevisionControlSlave {

	public void setAutoUpdateEnabled(boolean enabled);
	
	public boolean isAutoUpdateEnabled();

	public boolean update();

}
