package com.github.sweettooth.shared.logging;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Objects;

public interface Loggable {
	public Logger getLogger();
	
	default void info(String message) {
		Logger logger = getLogger();
		logger.log(Level.INFO, logger.getName() + " | -> | " + Objects.toString(message, ""));
	}
	
	default void warn(String message, Throwable exception) {
		Logger logger = getLogger();
		logger.log(Level.WARNING, logger.getName() + " | -> | " + Objects.toString(message, ""), exception);
	}
	
	default void error(String message, Throwable exception) {
		Logger logger = getLogger();
		logger.log(Level.SEVERE, logger.getName() + " | -> | " + Objects.toString(message, ""), exception);
	}
}
