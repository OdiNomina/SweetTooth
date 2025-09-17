package com.github.sweettooth.controllerSwing.buttonListener;

import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.github.sweettooth.controllerSwing.controllers.SwingController;
import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.controllerAPI.Processable;


public class ButtonListenerFactory {
	private SwingController controller;
	
	public ButtonListenerFactory(SwingController controller){
		this.controller = controller;
	}
	
	public ActionListener create(String eventName, JComboBox<String> associatedComboBox, JTextField associatedTextBox, JComponent nextInFocus, JLabel... answerBox) {
		IGameData gameData = controller.getGameModel();
		Processable event = controller.getEventFactory().createEvent(eventName, gameData);
		switch(eventName.toLowerCase()) {
			case "hide": return new HideListener(nextInFocus, gameData, event, answerBox);
			case "seek": return new SeekListener(associatedComboBox, associatedTextBox, nextInFocus, gameData, event, answerBox);
			case "exit": return new ExitListener(nextInFocus, gameData, event);
			default: return null;
		}
	}
}
