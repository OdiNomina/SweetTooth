package com.github.sweettooth.controller.api;

import com.github.sweettooth.controller.buttonListeners.ExitListener;
import com.github.sweettooth.controller.buttonListeners.HideListener;
import com.github.sweettooth.controller.buttonListeners.SeekListener;
import com.github.sweettooth.model.events.Event;
import com.github.sweettooth.model.games.Game;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.TextBox;

@SuppressWarnings("exports")
public class ButtonListenerFactory {
	private LanternaController controller;
	
	public ButtonListenerFactory(LanternaController controller){
		this.controller = controller;
	}
	
	public Button.Listener createButtonListener(String eventName, ComboBox<String> associatedComboBox, TextBox associatedTextBox, Interactable nextInFocus, Label... answerBox) {
		Event event = controller.getEventFactory().create(eventName, controller.getGame());
		Game game = controller.getGame();
		switch(eventName) {
			case "Hide": return new HideListener(nextInFocus, game, event, answerBox);
			case "Seek": return new SeekListener(associatedComboBox, associatedTextBox, nextInFocus, game, event, answerBox);
			case "Exit": return new ExitListener(nextInFocus, game, event);
			default: return null;
		}
	}
}
