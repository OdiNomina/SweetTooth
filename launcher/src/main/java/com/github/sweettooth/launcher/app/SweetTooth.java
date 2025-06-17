package com.github.sweettooth.launcher.app;

import com.github.sweettooth.controller.api.ControllerInterface;

import java.util.Locale;

import com.github.sweettooth.controller.api.ControllerFactory;
import com.github.sweettooth.model.api.GameModelInterface;
import com.github.sweettooth.model.api.Settings;
import com.github.sweettooth.view.api.DisplayElement;
import com.github.sweettooth.view.api.DisplayFactory;

public class SweetTooth {
	SweetTooth(){}
	
	public static void main(String[] args) {
		try {
			Settings settings = new Settings(Locale.GERMANY);
			
			GameModelInterface gameModel = GameModelInterface.createGameModel(settings);
			ControllerInterface lanternaController = ControllerFactory.create(gameModel);
			
			DisplayElement lanternaGUI = DisplayFactory.create(gameModel, lanternaController, settings);
	        lanternaGUI.display();
	    }
		catch(RuntimeException e) {
			e.printStackTrace();
		}
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
