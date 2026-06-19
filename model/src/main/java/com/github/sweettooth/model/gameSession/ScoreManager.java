package com.github.sweettooth.model.gameSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import com.github.sweettooth.model.api.gameSession.ScoreProvider;
import com.github.sweettooth.model.api.settings.IGlobalSettings;
import com.github.sweettooth.persistence.api.PPFactory;
import com.github.sweettooth.persistence.api.sharedDAO.ScoreEntry;
import com.github.sweettooth.persistence.api.PersistenceProvider;
import com.github.sweettooth.shared.api.logging.Loggable;

public class ScoreManager implements Loggable, ScoreProvider {
	private final Logger logger;
	private final PersistenceProvider<ScoreEntry> persistenceProvider;
	private final List<ScoreEntry> scores  = new ArrayList<>();
	private final Locale locale;
	
    public ScoreManager(IGlobalSettings s) {
    	logger = Logger.getLogger(ScoreManager.class.getName());
    	locale = s.getLocale();
    	persistenceProvider = PPFactory.getDefault().getProvider(ScoreEntry.class);
    }
   
    @Override
    public synchronized void addScore(String namePlayer, Double score) {
    	scores.removeIf(e -> e.score() == null || e.score().isNaN());
    	scores.add(new ScoreEntry(namePlayer, score));
        Collections.sort(scores);
        if (scores.size() > 50)
            scores.subList(50, scores.size()).clear();
    }
    
	@Override
	public Logger getLogger() {
		return logger;
	}
	
	@Override
	public synchronized List<ScoreData> getScores() {
		if(scores.size() > 1)
			return scores.stream()
					.filter(entry -> entry.score() != 0)
					.map(entry -> new ScoreData(entry.name(), entry.score()))
					.toList();
		
		return scores.stream()
				.map(entry -> new ScoreData(entry.name(), entry.score()))
				.toList();
	}

	@Override
	public void readPersistentScore() {
	    try {
	    	persistenceProvider.readFromFile(scores, locale);;
	    }
	    catch(IOException | RuntimeException ex) {
	    	error("Exception while reading persistent score.", ex);
	    }
	}

	@Override
	public void writePersistentScore() {
		try {
	    	persistenceProvider.writeToFile(scores, locale);
	    }
		catch(IOException | RuntimeException ex) {
	    	error("Exception while writing persistent score.", ex);
	    }
	}
}
