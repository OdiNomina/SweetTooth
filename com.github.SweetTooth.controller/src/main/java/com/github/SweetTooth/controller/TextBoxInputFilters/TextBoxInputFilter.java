package com.github.SweetTooth.controller.TextBoxInputFilters;

import com.github.SweetTooth.controller.controllerAPI.LanternaController;
import com.github.SweetTooth.model.events.Event;
import com.github.SweetTooth.model.events.EventFactory;
import com.github.SweetTooth.model.game.Game;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.InputFilter;
import com.googlecode.lanterna.gui2.Label;

public abstract class TextBoxInputFilter implements InputFilter {
	EventFactory eventFactory;
	Game game;
	Event event;
	Label answerBox;
	
	TextBoxInputFilter(String textBoxName, LanternaController controller) {
		eventFactory = controller.getEventFactory();
		game = controller.getGame();
		switchEvent(textBoxName);
		switchAnswerBox(textBoxName);
	}
	
	void switchEvent(String textBoxName) {
		switch(textBoxName) {
			case "buyQuantity" -> event = eventFactory.create("Buy", game);
			case "sellQuantity" -> event = eventFactory.create("Sell", game);
			case "deposit" -> event = eventFactory.create("Deposit", game);
			case "withdraw" -> event = eventFactory.create("Withdraw", game);
			case "lend" -> event = eventFactory.create("Lend", game);
			case "giveBack" -> event = eventFactory.create("GiveMoneyBack", game);
		}
	}
	
	void switchAnswerBox(String textBoxName) {
		switch(textBoxName) {
			case "buyQuantity", "sellQuantity" -> answerBox = getPanelContent().getLabels().get("buySellInfo");
			case "seekQuantity" -> answerBox = getPanelContent().getLabels().get("hideSeekInfo");
			case "deposit", "withdraw" -> answerBox = getPanelContent().getLabels().get("bankInfo");
			case "lend", "giveBack" -> answerBox = getPanelContent().getLabels().get("loansharkInfo");
		}
	}
}
