package com.github.sweettooth.shared.api.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.sweettooth.shared.logging.LoggingSetup;

import java.util.Objects;

/**<pre>
 * Example usage:
 * 
 * Thread logging info:
 * info(Thread.currentThread().getName() + 
 * 		" is running: "+ getClass().getSimpleName() +
 * 		" > " + Thread.currentThread().getStackTrace()[1].getMethodName()
 * 	)
 * </pre>
 */

public interface Loggable {
	static void setUpLogger(Class<?> launcher) {
		LoggingSetup.setUpLogger(launcher);
	}
	
	Logger getLogger();
	
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
