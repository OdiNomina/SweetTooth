package com.github.sweettooth.launcher.app;

import com.github.sweettooth.controller.api.Controller;
import com.github.sweettooth.controller.api.ControllerFactory;
import com.github.sweettooth.model.api.ActorFactory;
import com.github.sweettooth.model.games.GameData;

import com.github.sweettooth.view.api.DisplayElement;
import com.github.sweettooth.view.api.DisplayFactory;

public class SweetTooth {
	SweetTooth(){}
	
	public static void main(String[] args) {
		try {
			GameData gameData = new GameData(ActorFactory.createPlayer(), ActorFactory.createInteractable("bank"), ActorFactory.createInteractable("loanshark"));
			Controller controller = ControllerFactory.create(gameData);
			controller.initializeFactories();
			DisplayElement lanternaGUI = DisplayFactory.create(controller);
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
