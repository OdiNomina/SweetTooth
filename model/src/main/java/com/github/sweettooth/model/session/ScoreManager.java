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
    public synchronized void addScore(String name, Double score) {
    	scores.removeIf(e -> e.score() == null || e.score().isNaN());
    	scores.add(new ScoreEntry(name, score));
        Collections.sort(scores);
        if (scores.size() > 50)
            scores.subList(50, scores.size()).clear();
        writeScores();
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
    			reader.lines().map(ScoreEntry::fromString).forEach(scores::add);
    		}
    		Collections.sort(scores);
    		if (scores.size() > 50)
    		    scores.subList(50, scores.size()).clear();
        } catch(IOException | RuntimeException ex) {
        	error("Exception while reading scores from file. ", ex);
        }
    }
    
    private void writeScores() {
    	try {
    		List<String> lines = scores.stream()
    				.map(ScoreEntry::toString)
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
		return scores.stream()
					.map(e -> new ScoreData(e.name(), e.score()))
					.toList();
	}
}
