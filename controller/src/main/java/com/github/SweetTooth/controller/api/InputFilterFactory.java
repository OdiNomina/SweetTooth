package com.github.sweettooth.controller.api;

import com.github.sweettooth.controller.textBoxInputFilters.DealInputFilter;
import com.github.sweettooth.controller.textBoxInputFilters.FinancesInputFilter;
import com.github.sweettooth.controller.textBoxInputFilters.SeekQuantityInputFilter;
import com.github.sweettooth.model.events.Event;
import com.github.sweettooth.model.games.Game;

import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.InputFilter;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;

@SuppressWarnings("exports")
public class InputFilterFactory {
	private LanternaController controller;
	
	public InputFilterFactory(LanternaController controller){
		this.controller = controller;
	}
	
	public InputFilter createInputFilter(String eventName, ComboBox<String> associatedComboBox, Interactable nextInFocus, Label answerBox) {
		Event event = controller.getEventFactory().create(eventName, controller.getGame());
		Game game = controller.getGame();
		switch(eventName) {
			case "Seek": return new SeekQuantityInputFilter(game, event, nextInFocus, answerBox);
			case "Buy": return new DealInputFilter(game, event, associatedComboBox, nextInFocus, answerBox);
			case "Sell": return new DealInputFilter(game, event, associatedComboBox, nextInFocus, answerBox);
			case "Deposit": return new FinancesInputFilter(game, event, nextInFocus, answerBox);
			case "Withdraw": return new FinancesInputFilter(game, event, nextInFocus, answerBox);
			case "Lend": return new FinancesInputFilter(game, event, nextInFocus, answerBox);
			case "GiveMoneyBack": return new FinancesInputFilter(game, event, nextInFocus, answerBox);
			default: return null;
		}
	}
}
