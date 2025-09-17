package com.github.sweettooth.controllerSwing.textFieldListener;

import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;

import com.github.sweettooth.controllerSwing.controllers.SwingController;
import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.controllerAPI.Processable;

public class TextFieldListenerFactory {
	private SwingController controller;
	
	public TextFieldListenerFactory(SwingController controller){
		this.controller = controller;
	}
	
	public ActionListener create(String eventName, JComboBox<String> associatedComboBox, JComponent nextInFocus, JLabel answerBox) {
		IGameData gameData = controller.getGameModel();
		Processable event = controller.getEventFactory().createEvent(eventName, gameData);
		switch(eventName.toLowerCase()) {
			case "seek": return new SeekTextFieldListener(gameData, event, nextInFocus, answerBox);
			case "buy": return new DealTextFieldListener(gameData, event, associatedComboBox, nextInFocus, answerBox);
			case "sell": return new DealTextFieldListener(gameData, event, associatedComboBox, nextInFocus, answerBox);
			case "deposit": return new FinancesTextFieldListener(gameData, event, nextInFocus, answerBox);
			case "withdraw": return new FinancesTextFieldListener(gameData, event, nextInFocus, answerBox);
			case "lend": return new FinancesTextFieldListener(gameData, event, nextInFocus, answerBox);
			case "givemoneyback": return new FinancesTextFieldListener(gameData, event, nextInFocus, answerBox);
			default: return null;
		}
	}
}
