package com.github.sweettooth.controller.buttonListeners;

import java.io.IOException;

import com.github.sweettooth.model.events.Event;
import com.github.sweettooth.model.events.Observer;
import com.github.sweettooth.model.games.Game;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Interactable;

public class ExitListener extends ButtonListener {
	Game game;
	
	public ExitListener(Interactable nextInFocus, Game game, Event event) {
		super(nextInFocus);
		this.game = game;
	}
	
	@Override
	public void onTriggered(Button button) {
		try {
			for(Observer view : game.getViews())
				view.stopObserver();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
