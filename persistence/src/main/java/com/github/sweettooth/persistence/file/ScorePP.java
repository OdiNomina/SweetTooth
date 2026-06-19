package com.github.sweettooth.persistence.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import com.github.sweettooth.persistence.api.PersistenceProvider;
import com.github.sweettooth.persistence.api.sharedDAO.ScoreEntry;
import com.github.sweettooth.shared.api.logging.Loggable;

public class ScorePP implements Loggable, PersistenceProvider<ScoreEntry> {
	private final Logger logger;
	private final Path appDir;
	private final Path scoreFile;
	
	public ScorePP() {
		logger = Logger.getLogger(ScorePP.class.getName());
		
		Path userHome = Path.of(System.getProperty("user.home"));
    	appDir = userHome.resolve(".SweetTooth");
    	scoreFile = appDir.resolve("scores.txt");
	}
	
	@Override
	public Logger getLogger() {
		return logger;
	}

	@Override
	public synchronized void readFromFile(List<ScoreEntry> target, Locale l) throws IOException {
		try {
	    	if(Files.notExists(appDir))
	    		Files.createDirectories(appDir);
	    	
	    	if(Files.notExists(scoreFile))
	    		try (InputStream is = getClass().getResourceAsStream("/defaultScores.txt")) {
	    			if (is == null) 
	                    throw new FileNotFoundException("Resource defaultScores.txt not found!");
	    			Files.copy(is, scoreFile);
	    		}
	    	
	    	target.clear();
			try (BufferedReader reader = Files.newBufferedReader(scoreFile)) {
				reader.lines().map(line -> ScoreEntry.fromLocaleString(line, l)).forEach(entry -> target.add(entry));
			}
			Collections.sort(target);
			if (target.size() > 50)
				target.subList(50, target.size()).clear();
	    }
		catch (IOException ex) {
	        logSuppressed(ex);
	        throw ex;
	    }
		catch (RuntimeException ex) {
	        logSuppressed(ex);
	        throw ex;
	    }
	}

	@Override
	public synchronized void testAccess(ScoreEntry dao) {
		throw new UnsupportedOperationException();
	}

	@Override
	public synchronized void writeToFile(List<ScoreEntry> source, Locale l) throws IOException {
		try {
			List<String> lines = source.stream()
					.filter(entry -> entry.score() != 0.0)
					.map(score -> score.toLocaleString(l))
					.toList();
			
			Files.write(scoreFile, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		}
		catch (IOException ex) {
	        logSuppressed(ex);
	        throw ex;
	    }
		catch (RuntimeException ex) {
	        logSuppressed(ex);
	        throw ex;
	    }
	}
	
	private void logSuppressed(Throwable ex) {
	    error("Exception when accessing scores in file.", ex);
	    for (Throwable t : ex.getSuppressed()) {
	        error("Suppressed exception:", t);
	    }
	}
}
