package com.github.sweettooth.model.session;

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

import com.github.sweettooth.model.api.viewAPI.ScoreProvider;
import com.github.sweettooth.shared.api.Loggable;

public class ScoreManager implements Loggable, ScoreProvider {
	private final Logger logger;
	private final List<ScoreEntry> scores;
	
	Path userHome;
    Path appDir;
    Path scoreFile;
    
    public ScoreManager() {
    	logger = Logger.getLogger(ScoreManager.class.getName());
    	scores = new ArrayList<>();
    	
    	userHome = Path.of(System.getProperty("user.home"));
    	appDir = userHome.resolve(".SweetTooth");
    	scoreFile = appDir.resolve("scores.txt");
    }
   
    @Override
    public synchronized void addScore(String name, Double score, Locale locale) {
    	scores.removeIf(e -> e.score() == null || e.score().isNaN());
    	scores.add(new ScoreEntry(name, score));
        Collections.sort(scores);
        if (scores.size() > 50)
            scores.subList(50, scores.size()).clear();
        writeScores(locale);
    }
    
    @Override
    public synchronized void readScoresFromFile(Locale locale) {
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
    
    private void writeScores(Locale locale) {
    	try {
    		List<String> lines = scores.stream()
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
	public synchronized List<ScoreData> getScores(Locale locale) {
		return scores.stream()
					.map(scoreEntry -> new ScoreData(scoreEntry.name(), toLocaleString(locale, scoreEntry.score())))
					.toList();
	}
	
	/*
	 * %[flags][.precision]conversion
	 * Flag ',': The result will include locale-specific grouping separators.
	 * Conversion 'f': The result is formatted as a decimal number.
	 */
	private static String toLocaleString(Locale locale, Double score) {
		return String.format(locale, "%,.2f", Math.round(score*100)/100.0);
	}
}
