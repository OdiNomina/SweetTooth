package com.github.sweettooth.launcher.app;

import java.lang.Thread.UncaughtExceptionHandler;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;
import java.util.logging.LogManager;

import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.ISnackFactory;
import com.github.sweettooth.model.api.ISnackFactory.SnackType;
import com.github.sweettooth.model.api.GameSettings;
import com.github.sweettooth.shared.api.Loggable;
import com.github.sweettooth.shared.api.LoggingSetup;
import com.github.sweettooth.viewSwing.api.SwingDisplay;

public class SweetTooth implements Loggable {
	private static final Logger LOGGER = Logger.getLogger(SweetTooth.class.getName());
	private static SweetTooth app;
	
	SweetTooth(){}
	
	public static void main(String[] args) {
		try {
			Instant start = Instant.now();
			ExecutorService executor = Executors.newCachedThreadPool();
			app = new SweetTooth();
			
			LoggingSetup.initialize(SweetTooth.class);
			app.setDefaultUncaughtExceptionHandler();
			app.addShutdownHook();
			
			GameSettings gameSettings = new GameSettings(Locale.GERMANY, ISnackFactory.getFactory(SnackType.Candy));
			
			IGameData gameModel = IGameData.createGameData().initialize(gameSettings, null);
			
			Future<SwingDisplay> gui = executor.submit( () ->
				SwingDisplay.getInstance(gameModel).initialize(gameSettings) );
			
			executor.submit(gui.get());
			
			executor.shutdown();
			app.info(String.format(Thread.currentThread().getName() + " thread stopped: Runtime %s ms", start.until(Instant.now(), ChronoUnit.MILLIS)));
		}
		catch(Exception e) { app.error(Thread.currentThread().getName() + " thread throws " + e.getClass().getName(), e); }
	}

	private void addShutdownHook() {
		try {
			Runtime.getRuntime().addShutdownHook(new Thread(() -> {
				app.info("Shutdown hook is executed - Logger is reset.\n");
		        try { LogManager.getLogManager().reset(); } // Schließt alle globalen Handler (z.B. FileHandler)
		        catch(SecurityException e) { error(e.getClass().getName() + " when attempting to reset log manager.", e); }
		    }));
		}
		catch(IllegalArgumentException | IllegalStateException | SecurityException e) { error(e.getClass().getName() + " when adding 'shutdown hook'.", e); }
	}
	
	@Override
	public Logger getLogger() {
		return LOGGER;
	}
	
	private void setDefaultUncaughtExceptionHandler() {
		UncaughtExceptionHandler ueh = (thread, exception) -> {
			error("Uncaught exception in thread '" + thread.getName() + "': ", exception);
		};
		try { Thread.setDefaultUncaughtExceptionHandler(ueh); }
		catch(SecurityException e) { error(e.getClass().getName() + " when setting 'default uncaught exception handler'.", e); }
	}
}
