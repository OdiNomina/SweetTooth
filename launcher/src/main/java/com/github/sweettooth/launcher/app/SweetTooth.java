package com.github.sweettooth.launcher.app;

import java.lang.Thread.UncaughtExceptionHandler;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.logging.Logger;
import java.util.logging.LogManager;

import com.github.sweettooth.model.api.GameSettings;
import com.github.sweettooth.model.api.ISessionData;
import com.github.sweettooth.model.api.ISnackFactory;
import com.github.sweettooth.model.api.ISnackFactory.SnackType;
import com.github.sweettooth.model.api.viewAPI.ScoreProvider;
import com.github.sweettooth.shared.logging.Loggable;
import com.github.sweettooth.shared.logging.LoggingSetup;
import com.github.sweettooth.viewSwing.api.SwingDisplay;

public class SweetTooth implements Loggable {
	private static final Logger LOGGER = Logger.getLogger(SweetTooth.class.getName());
	private static SweetTooth gameInstance;
	
	SweetTooth(){}
	
	public static void main(String[] args) {
		try {
			Instant start = Instant.now();
			gameInstance = new SweetTooth();
			
			LoggingSetup.initialize(SweetTooth.class);
			gameInstance.setDefaultUncaughtExceptionHandler();
			gameInstance.addShutdownHook();
			
			ScoreProvider scoreProvider = ScoreProvider.createScoreProvider();
			GameSettings gameSettings = new GameSettings(Locale.GERMANY, ISnackFactory.getFactory(SnackType.Candy));
			ISessionData sessionData = ISessionData.createSessionData(scoreProvider, gameSettings, null);
			
			SwingDisplay.getInstance(sessionData).start();
			
			gameInstance.info(String.format(Thread.currentThread().getName() + " thread stopped: Runtime %s ms", start.until(Instant.now(), ChronoUnit.MILLIS)));
		}
		catch(Exception ex) {
			gameInstance.error(Thread.currentThread().getName() + " thread throws " + ex.getClass().getName(), ex);
		}
	}

	@Override
	public Logger getLogger() {
		return LOGGER;
	}

	private void addShutdownHook() {
		try {
			Runtime.getRuntime()
				.addShutdownHook(new Thread( () -> {
						gameInstance.info(Thread.currentThread().getName() + " shutdown hook is executed: Logger is reset.\n");
				        try {
				        	LogManager.getLogManager().reset();  // Schließt alle globalen Handler (z.B. FileHandler)
				        }
				        catch(SecurityException ex) {
				        	error(ex.getClass().getName() + " when attempting to reset log manager.", ex);
				        }
			        }
				));
		}
		catch(IllegalArgumentException | IllegalStateException | SecurityException ex) {
			error(ex.getClass().getName() + " when adding 'shutdown hook'.", ex);
		}
	}
	
	private void setDefaultUncaughtExceptionHandler() {
		UncaughtExceptionHandler ueh = (thread, exception) -> {
											error("Uncaught exception in thread '" + thread.getName() + "': ", exception);
										};
		try {
			Thread.setDefaultUncaughtExceptionHandler(ueh);
		}
		catch(SecurityException ex) {
			error(ex.getClass().getName() + " when setting 'default uncaught exception handler'.", ex);
		}
	}
}
