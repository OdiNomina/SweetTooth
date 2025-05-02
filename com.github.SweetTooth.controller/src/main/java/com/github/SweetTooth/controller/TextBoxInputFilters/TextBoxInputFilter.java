package com.github.SweetTooth.controller.TextBoxInputFilters;

import com.github.SweetTooth.controller.controllerAPI.LanternaController;
import com.github.SweetTooth.model.events.Event;
import com.github.SweetTooth.model.events.EventFactory;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Label;

abstract class TextBoxInputFilter extends LanternaController {
	Event event;
	Label answerBox;
	
	TextBoxInputFilter(String textBoxName) {
		switchEvent(textBoxName);
		switchAnswerBox(textBoxName);
	}
	
	void switchEvent(String textBoxName) {
		switch(textBoxName) {
			case "buyQuantity" -> event = getEventFactory().create("Buy", getGame());
			case "sellQuantity" -> event = getEventFactory().create("Sell", getGame());
			case "deposit" -> event = getEventFactory().create("Deposit", getGame());
			case "withdraw" -> event = getEventFactory().create("Withdraw", getGame());
			case "lend" -> event = getEventFactory().create("Lend", getGame());
			case "giveBack" -> event = getEventFactory().create("GiveMoneyBack", getGame());
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
