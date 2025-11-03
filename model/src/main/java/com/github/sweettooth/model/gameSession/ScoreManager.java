package com.github.sweettooth.model.gameSession;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import com.github.sweettooth.model.api.gameSession.ScoreProvider;
import com.github.sweettooth.model.api.settings.IGlobalSettings;
import com.github.sweettooth.shared.api.logging.Loggable;

public class ScoreManager implements Loggable, ScoreProvider {
	private final Logger logger;
	private final List<ScoreEntry> scores  = new ArrayList<>();
	private Locale locale;
	
	Path userHome;
    Path appDir;
    Path scoreFile;
    
    public ScoreManager(IGlobalSettings globalSettings) {
    	logger = Logger.getLogger(ScoreManager.class.getName());
    	locale = globalSettings.getLocale();
    	
    	userHome = Path.of(System.getProperty("user.home"));
    	appDir = userHome.resolve(".SweetTooth");
    	scoreFile = appDir.resolve("scores.txt");
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
    public synchronized void writeScoresToFile() {
		try {
			List<String> lines = scores.stream()
					.filter(entry -> entry.score() != 0.0)
					.map(score -> score.toLocaleString(locale))
					.toList();
			
			Files.write(scoreFile, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch(IOException ex) {
			error("Exception while writing scores in file.", ex);
		}
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
					.map(scoreEntry -> new ScoreData(scoreEntry.name(), doubleToLocaleString(locale, scoreEntry.score())))
					.toList();
		
		return scores.stream()
				.map(scoreEntry -> new ScoreData(scoreEntry.name(), doubleToLocaleString(locale, scoreEntry.score())))
				.toList();
	}
	
	/*
	 * %[flags][.precision]conversion
	 * Flag ',': The result will include locale-specific grouping separators.
	 * Conversion 'f': The result is formatted as a decimal number.
	 */
	private static String doubleToLocaleString(Locale locale, Double score) {
		if(score == 0)
			return "";
		
		return String.format(locale, "%,.2f", Math.round(score*100)/100.0);
	}

	@Override
	public synchronized void readScoresFromFile() {
	    try {
	    	if(Files.notExists(appDir))
	    		Files.createDirectories(appDir);
	    	
	    	if(Files.notExists(scoreFile))
	    		try (InputStream is = getClass().getResourceAsStream("/defaultScores.txt")) {
	    			if (is == null) 
	                    throw new FileNotFoundException("Resource defaultScores.txt not found!");
	    			Files.copy(is, scoreFile);
	    		}
	    	
	    	scores.clear();
			try (BufferedReader reader = Files.newBufferedReader(scoreFile)) {
				reader.lines().map(line -> ScoreEntry.fromLocaleString(line, locale)).forEach(scores::add);
			}
			Collections.sort(scores);
			if (scores.size() > 50)
			    scores.subList(50, scores.size()).clear();
	    } catch(IOException | RuntimeException ex) {
	    	error("Exception while reading scores from file. ", ex);
	    }
	}
}
