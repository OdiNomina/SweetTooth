package com.github.SweetTooth.game;

import com.github.SweetTooth.characters.Playable;
import com.github.SweetTooth.gui.GUIManager;

public class SweetTooth {
	public static void main(String[] args) throws InterruptedException {	
		try {
			Playable player = Playable.getInstance();
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