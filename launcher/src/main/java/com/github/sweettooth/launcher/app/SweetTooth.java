package com.github.sweettooth.launcher.app;

import com.github.sweettooth.controller.api.ControllerInterface;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import com.github.sweettooth.controller.api.ControllerFactory;
import com.github.sweettooth.model.api.GameModelInterface;
import com.github.sweettooth.model.api.Logged;
import com.github.sweettooth.model.api.Settings;
import com.github.sweettooth.view.api.DisplayElement;
import com.github.sweettooth.view.api.DisplayFactory;

public class SweetTooth implements Logged {
	static Logged logged = new SweetTooth();
	
	SweetTooth(){}
	
	public static void main(String[] args) {
		Instant start = Instant.now();
		Settings settings = new Settings(Locale.GERMANY);
		
		try {
			GameModelInterface gameModel = GameModelInterface.createGameModel(settings);
			ControllerInterface lanternaController = ControllerFactory.create(gameModel);
			
			DisplayElement lanternaGUI = DisplayFactory.create(gameModel, lanternaController, settings);
	        lanternaGUI.startGuiThread();
	    }
		catch(RuntimeException e) {
			logged.warn(null, e);
		}
	    catch (Exception e) {
	    	logged.warn(null, e);
	    }
		
		logged.info(String.format(Thread.currentThread() + " stopped: Runtime %s ms", start.until(Instant.now(), ChronoUnit.MILLIS)));
	}
}
