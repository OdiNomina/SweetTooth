package com.github.sweettooth.model.games;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import com.github.sweettooth.model.api.viewAPI.ScoreProvider;
import com.github.sweettooth.shared.api.Loggable;

public class ScoreManager implements Loggable, ScoreProvider {
	final Logger logger;
    Path userHome = Path.of(System.getProperty("user.home"));
    Path appDir = userHome.resolve(".SweetTooth");
    Path scoreFile = appDir.resolve("scores.txt");
    
    List<ScoreEntry> scores = new LinkedList<>();
    
    public ScoreManager() {
    	logger = Logger.getLogger(ScoreManager.class.getName());
    }
    
    public void addScore(String name, double score) {
        scores.add(new ScoreEntry(name, score));
        Collections.sort(scores);
        scores = scores.stream().limit(50).toList();
        writeScores();
    }
    
    @Override
    public void readScores() {
        try {
        	if(Files.notExists(appDir))
        		Files.createDirectories(appDir);
        	
        	if(Files.notExists(scoreFile))
        		try (InputStream is = getClass().getResourceAsStream("/defaultScores.txt")) {
        			if (is == null) 
                        throw new FileNotFoundException("Resource defaultScores.txt nicht gefunden!");
                    
        			Files.copy(is, scoreFile);
        		}
        	
    		try (BufferedReader reader = Files.newBufferedReader(scoreFile)) {
    			reader.lines()
    				.map(ScoreEntry::fromString)
    				.forEach(scores::add);
    		}
    		Collections.sort(scores);
    		scores = scores.stream().limit(50).toList();
        } catch(IOException ex) {
        	error("IOException while reading score file.", ex);
        } catch(RuntimeException ex) {
        	error("Exception while reading scores ", ex);
        }
    }
    
    private void writeScores() {
    	try {
    		List<String> lines = scores.stream()
    				.map(ScoreEntry::toString)
    				.toList();
    		
    		Files.write(scoreFile, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    	} catch(IOException ex) {
    		error("IOException while writing score file.", ex);
    	}
    }

	@Override
	public Logger getLogger() {
		return logger;
	}

	@Override
	public List<ScoreData> getScores() {
		return Collections.unmodifiableList(
				scores.stream()
					.map(e -> new ScoreData(e.name(), e.score()))
					.toList()
				);
	}
}
