package com.github.sweettooth.controllerSwing.dealComboBoxListener;

import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JComponent;
import javax.swing.JLabel;

import com.github.sweettooth.controllerSwing.controllers.DealController;
import com.github.sweettooth.model.api.IGameRound;
import com.github.sweettooth.model.api.gameEvents.Processable;
import com.github.sweettooth.shared.api.util.UpdateGuard;

public class ComboBoxListenerFactory {
	private DealController controller;
	
	public ComboBoxListenerFactory(DealController controller){
		this.controller = controller;
	}
	
	public ActionListener create(UpdateGuard guard, JLabel currentLocation, String eventName, JComponent nextInFocus, JLabel... answerBox) {
		IGameRound gameData = controller.getGameData();
		try {
			switch(eventName.toLowerCase()) {
				case "buy": return new DealSelectionListener(gameData, guard,nextInFocus);
				case "sell": return new DealSelectionListener(gameData, guard,nextInFocus);
				case "travel": {
					Processable event = controller.getEventFactory().createEvent(eventName, gameData);
					Processable applyInterestEvent = controller.getEventFactory().createEvent("ApplyInterest", gameData);
					return new LocationSelectionListener(guard, Objects.requireNonNull(currentLocation), nextInFocus, gameData, event, applyInterestEvent, answerBox);
				}
				default: return null;
			}
		}
		catch(NullPointerException ex) {
			return null;
		}
	}
}
