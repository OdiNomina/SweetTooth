package com.github.sweettooth.controllerSwing.controllers;

import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.github.sweettooth.controllerSwing.api.IController;
import com.github.sweettooth.controllerSwing.buttonListener.ButtonListenerFactory;
import com.github.sweettooth.controllerSwing.comboBoxListener.ComboBoxListenerFactory;
import com.github.sweettooth.controllerSwing.textFieldListener.TextFieldListenerFactory;
import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.controllerAPI.EventFactory;

public class SwingController implements IController {
	private IGameData gameData;
	private EventFactory eventFactory;
	private TextFieldListenerFactory textFieldListenerFactory;
	private ButtonListenerFactory buttonListenerFactory;
	private ComboBoxListenerFactory comboBoxListenerFactory;
	
	public EventFactory getEventFactory() {
		return eventFactory;
	}

	public IGameData getGameModel() {
		return gameData;
	}

	@Override
	public IController initialize(IGameData gameData) throws NullPointerException {
		this.gameData = Objects.requireNonNull(gameData);
		eventFactory = EventFactory.getDefaultFactory();
		textFieldListenerFactory = new TextFieldListenerFactory(this);
		buttonListenerFactory = new ButtonListenerFactory(this);
		comboBoxListenerFactory = new ComboBoxListenerFactory(this);
		return this;
	}
	
	@Override
	public ActionListener createTextFieldListener(String eventName, JComboBox<String> associatedComboBox, JComponent nextInFocus, JLabel answerBox) {
		return textFieldListenerFactory.create(eventName, associatedComboBox, nextInFocus, answerBox);
	}

	@Override
	public ActionListener createComboBoxListener(JLabel currentLocation, String eventName, JComponent nextInFocus, JLabel... answerBox) {
		return comboBoxListenerFactory.create(currentLocation, eventName, nextInFocus, answerBox);
	}

	@Override
	public ActionListener createButtonListener(String eventName, JComboBox<String> associatedComboBox, JTextField associatedTextBox, JComponent nextInFocus, JLabel... answerBox) {
		return buttonListenerFactory.create(eventName, associatedComboBox, associatedTextBox, nextInFocus, answerBox);
	}

}
