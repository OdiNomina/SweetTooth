package com.github.sweettooth.controller.textBoxInputFilters;

import com.github.sweettooth.controller.api.Controller;

import com.github.sweettooth.model.api.Processable;
import com.github.sweettooth.model.games.GameData;

import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.InputFilter;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;

public class InputFilterFactory {
	private Controller  controller;
	
	public InputFilterFactory(Controller controller){
		this.controller = controller;
	}
	
	public InputFilter create(String eventName, ComboBox<String> associatedComboBox, Interactable nextInFocus, Label answerBox) {
		GameData gameData = controller.getGameData();
		Processable event = controller.getEventFactory().create(eventName, gameData);
		switch(eventName) {
			case "Seek": return new SeekQuantityInputFilter(gameData, event, nextInFocus, answerBox);
			case "Buy": return new DealInputFilter(gameData, event, associatedComboBox, nextInFocus, answerBox);
			case "Sell": return new DealInputFilter(gameData, event, associatedComboBox, nextInFocus, answerBox);
			case "Deposit": return new FinancesInputFilter(gameData, event, nextInFocus, answerBox);
			case "Withdraw": return new FinancesInputFilter(gameData, event, nextInFocus, answerBox);
			case "Lend": return new FinancesInputFilter(gameData, event, nextInFocus, answerBox);
			case "GiveMoneyBack": return new FinancesInputFilter(gameData, event, nextInFocus, answerBox);
			default: return null;
		}
	}
}
