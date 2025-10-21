package com.github.sweettooth.controllerSwing.dealTextFieldListener;

import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;

import com.github.sweettooth.controllerSwing.controllers.DealController;
import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.controllerAPI.Processable;

public class TextFieldListenerFactory {
	private DealController controller;
	
	public TextFieldListenerFactory(DealController controller){
		this.controller = controller;
	}
	
	public ActionListener create(String eventName, JComboBox<String> associatedComboBox, JComponent nextInFocus, JLabel answerBox) {
		IGameData gameData = controller.getGameData();
		Processable event = controller.getEventFactory().createEvent(eventName, gameData);
		Locale locale = controller.getLocale();
		switch(eventName.toLowerCase()) {
			case "seek": return new SeekTextFieldListener(gameData, event, nextInFocus, answerBox);
			case "buy": return new DealTextFieldListener(gameData, event, associatedComboBox, nextInFocus, answerBox);
			case "sell": return new DealTextFieldListener(gameData, event, associatedComboBox, nextInFocus, answerBox);
			case "deposit": return new FinancesTextFieldListener(locale, gameData, event, nextInFocus, answerBox);
			case "withdraw": return new FinancesTextFieldListener(locale, gameData, event, nextInFocus, answerBox);
			case "lend": return new FinancesTextFieldListener(locale, gameData, event, nextInFocus, answerBox);
			case "givemoneyback": return new FinancesTextFieldListener(locale, gameData, event, nextInFocus, answerBox);
			default: return null;
		}
	}
}
