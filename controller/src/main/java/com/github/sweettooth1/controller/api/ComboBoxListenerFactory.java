package com.github.sweettooth.controller.api;

import com.github.sweettooth.controller.comboBoxListeners.BuySelectionListener;
import com.github.sweettooth.controller.comboBoxListeners.LocationSelectionListener;
import com.github.sweettooth.controller.comboBoxListeners.SellSelectionListener;

import com.github.sweettooth.model.events.Event;
import com.github.sweettooth.model.games.Game;

import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;

@SuppressWarnings("exports")
public class ComboBoxListenerFactory {
	private LanternaController controller;
	
	public ComboBoxListenerFactory(LanternaController controller){
		this.controller = controller;
	}
	
	public ComboBox.Listener createComboBoxListener(ComboBox<String> thisComboBox, String eventName, Interactable nextInFocus, Label... answerBox) {
		switch(eventName) {
			case "Buy": return new BuySelectionListener(thisComboBox, nextInFocus);
			case "Sell": return new SellSelectionListener(thisComboBox, nextInFocus);
			case "Travel": {
				Event event = controller.getEventFactory().create(eventName, controller.getGame());
				Game game = controller.getGame();
				Event applyInterestEvent = controller.getEventFactory().create("ApplyInterest", controller.getGame());
				return new LocationSelectionListener(thisComboBox, nextInFocus, game, event, applyInterestEvent, answerBox);
			}
			default: return null;
		}
	}
}
