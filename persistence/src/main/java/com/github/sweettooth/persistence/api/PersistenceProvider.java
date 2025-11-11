package com.github.sweettooth.persistence.api;

import com.github.sweettooth.persistence.PersistenceManager;

public interface PersistenceProvider {
	
	public enum ConcretePersistenceProvider {ExampleAccessJDBC}
	
	public static PersistenceProvider getInstance() {
		return PersistenceManager.getInstance();
	}
	
	void testAccess(ConcretePersistenceProvider p);
}
