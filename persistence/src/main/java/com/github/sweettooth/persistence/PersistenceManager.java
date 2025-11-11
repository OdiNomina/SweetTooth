package com.github.sweettooth.persistence;

import com.github.sweettooth.persistence.api.PersistenceProvider;
import com.github.sweettooth.persistence.jdbc.ExampleAccessJDBC;

public class PersistenceManager implements PersistenceProvider {
	
	private static PersistenceManager uniqueInstance;
	
	public static PersistenceManager getInstance() {
		if(uniqueInstance == null)
			uniqueInstance = new PersistenceManager();
		
		return uniqueInstance;
	}
	
	@Override
	public void testAccess(ConcretePersistenceProvider p) {
		switch(p) {
			case ExampleAccessJDBC -> ExampleAccessJDBC.getInstance().testAccessDB();
		};
	}
	
}
