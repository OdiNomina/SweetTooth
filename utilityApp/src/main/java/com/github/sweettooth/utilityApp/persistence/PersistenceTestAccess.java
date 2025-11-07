package com.github.sweettooth.utilityApp.accessDB;

import com.github.sweettooth.persistence.api.PersistenceProvider;

public class AccessDB {
	
	public static void main(String... args) {
		PersistenceProvider connectable = PersistenceProvider.getInstance();
		
		connectable.testAccessDB();
	}
}
