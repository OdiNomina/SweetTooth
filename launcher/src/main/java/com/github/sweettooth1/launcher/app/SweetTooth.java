package com.github.sweettooth.launcher.app;

import com.github.sweettooth.controller.api.LanternaController;
import com.github.sweettooth.model.characters.Bank;
import com.github.sweettooth.model.characters.LoanShark;
import com.github.sweettooth.model.characters.Player;
import com.github.sweettooth.model.games.Game;

import com.github.sweettooth.view.lanternaGUI.GUIManager;

public class SweetTooth {
	SweetTooth(){}
	
	public static void main(String[] args) {
		try {
			Game game = new Game(new Player(null), new Bank(), new LoanShark());
			LanternaController controller = new LanternaController(game);
			controller.initialize();
	    	GUIManager guiManager = new GUIManager(controller);
	    	guiManager.addObserver();
	        guiManager.start();
	    }
		catch(RuntimeException e) {
			e.printStackTrace();
		}
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
