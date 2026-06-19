package com.github.sweettooth.persistence.api;

import com.github.sweettooth.persistence.DefaultPPF;
import com.github.sweettooth.persistence.Shareable;

/**
 * Persistence Provider Factory Interface
 */
public interface PPFactory {
    
	static PPFactory getDefault() {
		return new DefaultPPF();
	}
	
	<T extends Shareable> PersistenceProvider<T> getProvider(Class<T> type);
}