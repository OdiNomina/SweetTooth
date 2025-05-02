package com.github.SweetTooth.sweettooth;

import com.github.SweetTooth.model.characters.Bank;
import com.github.SweetTooth.model.characters.LoanShark;
import com.github.SweetTooth.model.characters.Player;
import com.github.SweetTooth.model.game.Game;
import com.github.SweetTooth.view.lanternaGUI.GUIManager;

public class SweetTooth {

	public static void main(String[] args) {
		try {
			Game game = new Game(new Player(null), new Bank(), new LoanShark());

	    	GUIManager guiManager = new GUIManager();
	        guiManager.start(game);
	    }
		catch(RuntimeException e) {
			e.printStackTrace();
		}
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
