package com.github.sweettooth.controllerSwing.comboBoxListener;

import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JComponent;
import javax.swing.JLabel;

import com.github.sweettooth.controllerSwing.controllers.SwingController;
import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.controllerAPI.Processable;


public class ComboBoxListenerFactory {
	private SwingController controller;
	
	public ComboBoxListenerFactory(SwingController controller){
		this.controller = controller;
	}
	
	public ActionListener create(String previousSelection, String eventName, JComponent nextInFocus, JLabel... answerBox) {
		try {
			switch(eventName.toLowerCase()) {
				case "buy": return new DealSelectionListener(nextInFocus);
				case "sell": return new DealSelectionListener(nextInFocus);
				case "travel": {
					IGameData gameData = controller.getGameModel();
					Processable event = controller.getEventFactory().createEvent(eventName, gameData);
					Processable applyInterestEvent = controller.getEventFactory().createEvent("ApplyInterest", gameData);
					return new LocationSelectionListener(Objects.requireNonNull(previousSelection), nextInFocus, gameData, event, applyInterestEvent, answerBox);
				}
				default: return null;
			}
		}
		catch(NullPointerException ex) {
			return null;
		}
	}
}
