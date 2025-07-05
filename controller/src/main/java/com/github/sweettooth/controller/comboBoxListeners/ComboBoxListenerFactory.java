package com.github.sweettooth.controller.comboBoxListeners;

import com.github.sweettooth.controller.controlUnits.LanternaController;
import com.github.sweettooth.model.api.GameModelInterface;
import com.github.sweettooth.model.api.controllerAPI.Processable;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;

public class ComboBoxListenerFactory {
	private LanternaController controller;
	
	public ComboBoxListenerFactory(LanternaController controller){
		this.controller = controller;
	}
	
	public ComboBox.Listener create(ComboBox<String> thisComboBox, String eventName, Interactable nextInFocus, Label... answerBox) {
		switch(eventName) {
			case "Buy": return new BuySelectionListener(thisComboBox, nextInFocus);
			case "Sell": return new SellSelectionListener(thisComboBox, nextInFocus);
			case "Travel": {
				GameModelInterface gameData = controller.getGameModel();
				Processable event = controller.getEventFactory().createEvent(eventName, gameData);
				Processable applyInterestEvent = controller.getEventFactory().createEvent("ApplyInterest", gameData);
				return new LocationSelectionListener(thisComboBox, nextInFocus, gameData, event, applyInterestEvent, answerBox);
			}
			default: return null;
		}
	}
}
