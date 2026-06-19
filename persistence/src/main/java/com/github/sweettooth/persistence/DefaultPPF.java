package com.github.sweettooth.persistence;

import java.util.logging.Logger;

import com.github.sweettooth.persistence.api.PPFactory;
import com.github.sweettooth.persistence.api.sharedDAO.ScoreEntry;
import com.github.sweettooth.persistence.api.PersistenceProvider;
import com.github.sweettooth.persistence.file.ScorePP;
import com.github.sweettooth.shared.api.logging.Loggable;

/**
 * Simple Default Persistence Provider Factory
 */
public class DefaultPPF implements Loggable, PPFactory {
	private final Logger logger;
	
	public DefaultPPF() {
		logger = Logger.getLogger(DefaultPPF.class.getName());
	}
	
	@Override
	public Logger getLogger() {
		return logger;
	}

	@SuppressWarnings("unchecked")
	@Override
    public <T extends Shareable> PersistenceProvider<T> getProvider(Class<T> type) {
        if (type.equals(ScoreEntry.class)) {
            return (PersistenceProvider<T>) new ScorePP();
        }
        throw new IllegalArgumentException("No provider for type: " + type);
    }
}
