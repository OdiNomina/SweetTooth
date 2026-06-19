package com.github.sweettooth.persistence.jdbc;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

public class PersistenceAccessTest {

	 @Test
	    void testJdbcAccess() {
	        ExampleAccessJDBC jdbc = new ExampleAccessJDBC();
	        
	        assertDoesNotThrow(jdbc::testAccessDB);
	    }

}
