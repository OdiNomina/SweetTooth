package com.github.sweettooth.controller.comboBoxListeners;

import com.github.sweettooth.controller.api.Controller;
import com.github.sweettooth.model.api.Processable;
import com.github.sweettooth.model.games.GameData;

import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;

public class ComboBoxListenerFactory {
	private Controller controller;
	
	public ComboBoxListenerFactory(Controller controller){
		this.controller = controller;
	}
	
	public ComboBox.Listener create(ComboBox<String> thisComboBox, String eventName, Interactable nextInFocus, Label... answerBox) {
		switch(eventName) {
			case "Buy": return new BuySelectionListener(thisComboBox, nextInFocus);
			case "Sell": return new SellSelectionListener(thisComboBox, nextInFocus);
			case "Travel": {
				GameData gameData = controller.getGameData();
				Processable event = controller.getEventFactory().create(eventName, gameData);
				Processable applyInterestEvent = controller.getEventFactory().create("ApplyInterest", gameData);
				return new LocationSelectionListener(thisComboBox, nextInFocus, gameData, event, applyInterestEvent, answerBox);
			}
			default: return null;
		}
	}
}
