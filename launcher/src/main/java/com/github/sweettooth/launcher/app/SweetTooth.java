package com.github.sweettooth.launcher.app;

import com.github.sweettooth.controller.api.ControllerInterface;

import java.lang.Thread.UncaughtExceptionHandler;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.logging.Logger;
import java.util.logging.LogManager;

import com.github.sweettooth.controller.api.ControllerFactory;
import com.github.sweettooth.model.api.GameModelInterface;
import com.github.sweettooth.model.api.Settings;
import com.github.sweettooth.shared.api.Loggable;
import com.github.sweettooth.shared.api.LoggingSetup;
import com.github.sweettooth.view.api.DisplayElement;
import com.github.sweettooth.view.api.DisplayFactory;

public class SweetTooth implements Loggable {
	private static final Logger LOGGER = Logger.getLogger(SweetTooth.class.getName());
	private static SweetTooth app;
	
	SweetTooth(){}
	
	public static void main(String[] args) {
		try {
			Instant start = Instant.now();
			app = new SweetTooth();
			LoggingSetup.initialize(SweetTooth.class);
			app.setDefaultUncaughtExceptionHandler();
			app.addShutdownHook();
			
			Settings settings = new Settings(Locale.GERMANY);
			GameModelInterface gameModel = GameModelInterface.createGameModel();
			ControllerInterface lanternaController = ControllerFactory.create();
			DisplayElement lanternaGUI = DisplayFactory.create();
			try {
				gameModel.initialize(settings, null);
				lanternaController.initialize(gameModel);
				lanternaGUI.initialize(gameModel, lanternaController, settings);
			}
			catch(RuntimeException e) { app.error(e.getClass().getName() + " during initialization of modules.", e); }
			
			new Thread(lanternaGUI, "CreateLanternaGUI").start();
			app.info(String.format(Thread.currentThread().getName() + " thread stopped: Runtime %s ms", start.until(Instant.now(), ChronoUnit.MILLIS)));
		}
		catch(Exception e) { app.error(Thread.currentThread().getName() + " thread throws " + e.getClass().getName(), e); }
	}

	private void addShutdownHook() {
		try {
			Runtime.getRuntime().addShutdownHook(new Thread(() -> {
				app.info("Shutdown hook is executed - Logger is reset.");
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
