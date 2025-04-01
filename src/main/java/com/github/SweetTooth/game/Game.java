package com.github.SweetTooth.game;

import com.github.SweetTooth.characters.IPlayer;
import com.github.SweetTooth.gui.GUIManager;

public class SweetTooth {
	public static void main(String[] args) throws InterruptedException {	
		try {
			IPlayer player = IPlayer.getInstance(null);
	    	GUIManager guiManager = new GUIManager(player);
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