package com.github.sweettooth.controllerLanterna.buttonListeners;

import com.github.sweettooth.controllerLanterna.controllers.LanternaController;
import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.gameEvents.Processable;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.TextBox;

public class ButtonListenerFactory {
	private LanternaController controller;
	
	public ButtonListenerFactory(LanternaController controller){
		this.controller = controller;
	}
	
	public Button.Listener create(String eventName, ComboBox<String> associatedComboBox, TextBox associatedTextBox, Interactable nextInFocus, Label... answerBox) {
		IGameData gameData = controller.getGameModel();
		Processable event = controller.getEventFactory().createEvent(eventName, gameData);
		switch(eventName) {
			case "Hide": return new HideListener(nextInFocus, gameData, event, answerBox);
			case "Seek": return new SeekListener(associatedComboBox, associatedTextBox, nextInFocus, gameData, event, answerBox);
			case "Exit": return new ExitListener(nextInFocus, gameData, event);
			default: return null;
		}
	}
}
