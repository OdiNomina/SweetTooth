package com.github.sweettooth.utilityApp.persistence;

import com.github.sweettooth.persistence.api.PersistenceProvider;
import com.github.sweettooth.persistence.api.PersistenceProvider.ConcretePersistenceProvider;

public class PersistenceTestAccess {
	
	public static void main(String... args) {
		PersistenceProvider.getInstance().testAccess(ConcretePersistenceProvider.ExampleAccessJDBC);
	}
}
