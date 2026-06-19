package com.github.sweettooth.persistence.api;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.github.sweettooth.persistence.Shareable;

public interface PersistenceProvider<T extends Shareable> {
	
	void readFromFile(List<T> target, Locale l) throws IOException;
	
	void testAccess(T dao);
	
	void writeToFile(List<T> source, Locale l) throws IOException;
}
