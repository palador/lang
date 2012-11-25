package org.pa.lang.collection.revision;


public class Test {
	public static void main(String[] args) {
		MasterList<String> master = new MasterList<>(
				new RevisionCollectionFactory());
		SlaveList<String> slave = master.createSlave();
		for (String s : "Das ist so wie es ist".split(" ")) {
			master.add(s);
		}

		System.out.println("MASTER: ");
		for (int i = 0; i < master.size(); i++) {
			System.out.println(" " + master.get(i));
		}
		// slave.setAutoUpdateEnabled(false);
		System.out.println();
		System.out.println("SLAVE: ");
		for (int i = 0; i < slave.size(); i++) {
			System.out.println(" " + slave.get(i));
		}

	}
}
