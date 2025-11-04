package com.github.sweettooth.controllerLanterna.textBoxInputFilters;

import com.github.sweettooth.controllerLanterna.controllers.LanternaController;
import com.github.sweettooth.model.api.IGameRound;
import com.github.sweettooth.model.api.gameEvents.Processable;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.InputFilter;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;

public class InputFilterFactory {
	private LanternaController  controller;
	
	public InputFilterFactory(LanternaController controller){
		this.controller = controller;
	}
	
	public InputFilter create(String eventName, ComboBox<String> associatedComboBox, Interactable nextInFocus, Label answerBox) {
		IGameRound gameData = controller.getGameModel();
		Processable event = controller.getEventFactory().createEvent(eventName, gameData);
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
