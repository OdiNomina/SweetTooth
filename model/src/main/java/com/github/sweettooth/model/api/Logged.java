package com.github.sweettooth.model.api;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.lang.System.LoggerFinder;

public interface Logged {
	default void error(String message, Throwable exception) {
		if(message == null || message.isBlank())
			message = "";
		Logger logger = LoggerFinder.getLoggerFinder().getLogger(this.getClass().getName(), this.getClass().getModule());
		logger.log(Level.ERROR, logger.getName() + " | -> | " + message, exception);
	}
	
	default void warn(String message, Throwable exception) {
		if(message == null || message.isBlank())
			message = "";
		Logger logger = LoggerFinder.getLoggerFinder().getLogger(this.getClass().getName(), this.getClass().getModule());
		logger.log(Level.WARNING, logger.getName() + " | -> | " + message, exception);
	}
	
	default void info(String message) {
		if(message == null || message.isBlank())
			message = "";
		Logger logger = LoggerFinder.getLoggerFinder().getLogger(this.getClass().getName(), this.getClass().getModule());
		logger.log(Level.INFO, logger.getName() + " | -> | " + message);
	}
}
