package com.github.sweettooth.persistence.api;

import com.github.sweettooth.persistence.jdbc.ExampleAccessJDBC;

public interface PersistenceProvider {
	public static PersistenceProvider getInstance() {
		return ExampleAccessJDBC.getInstance();
	}
	void testAccessDB();
}
