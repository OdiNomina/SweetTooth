package com.github.SweetTooth.sweettooth;

import com.github.SweetTooth.controller.controllerAPI.LanternaController;
import com.github.SweetTooth.model.characters.Bank;
import com.github.SweetTooth.model.characters.LoanShark;
import com.github.SweetTooth.model.characters.Player;
import com.github.SweetTooth.model.games.Game;
import com.github.SweetTooth.view.lanternaGUI.GUIManager;

public class SweetTooth {

	public static void main(String[] args) {
		try {
			Game game = new Game(new Player(null), new Bank(), new LoanShark());
			LanternaController controller = new LanternaController(game);
	    	GUIManager guiManager = new GUIManager(controller);
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
