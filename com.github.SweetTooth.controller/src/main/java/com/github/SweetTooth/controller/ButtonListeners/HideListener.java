package com.github.SweetTooth.controller.ButtonListeners;

import com.github.SweetTooth.model.events.Event;
import com.github.SweetTooth.model.events.Observer;
import com.github.SweetTooth.model.games.Game;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;

public class HideListener extends ButtonListener {
	Game game;
	Event event;
	Label[] answerBox;
	
	public HideListener(Interactable nextInFocus, Game game, Event event, Label... answerBox) {
		super(nextInFocus);
		this.game = game;
		this.event = event;
		this.answerBox = answerBox;
	}
	
	@Override
	public void onTriggered(Button button) {
		String answer = event.handle(null, null, null);
		for(Observer view : game.getViews())
			view.updateObserver();
		answerBox[0].setText(answer);
	}
}
