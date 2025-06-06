package com.github.sweettooth.launcher.app;

import com.github.sweettooth.controller.api.ControllerInterface;
import com.github.sweettooth.controller.api.ControllerFactory;
import com.github.sweettooth.model.api.GameModelInterface;

import com.github.sweettooth.view.api.DisplayElement;
import com.github.sweettooth.view.api.DisplayFactory;

public class SweetTooth {
	SweetTooth(){}
	
	public static void main(String[] args) {
		try {
			GameModelInterface gameModel = GameModelInterface.createGameModel();
			ControllerInterface lanternaController = ControllerFactory.create(gameModel);
			
			DisplayElement lanternaGUI = DisplayFactory.create(gameModel, lanternaController);
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
