package com.github.sweettooth.controllerSwing.controllers;

import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.Objects;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.github.sweettooth.controllerSwing.api.IDealController;
import com.github.sweettooth.controllerSwing.comboBoxListener.ComboBoxListenerFactory;
import com.github.sweettooth.controllerSwing.dealButtonListener.ButtonListenerFactory;
import com.github.sweettooth.controllerSwing.dealWindowListener.WindowCloseListener;
import com.github.sweettooth.controllerSwing.textFieldListener.TextFieldListenerFactory;
import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.ISessionData;
import com.github.sweettooth.model.api.controllerAPI.EventFactory;
import com.github.sweettooth.shared.api.FrameNavigator;
import com.github.sweettooth.shared.api.UpdateGuard;

public class DealController implements IDealController {
	private final FrameNavigator frameNavigator;
	private ISessionData sessionData;
	private IGameData gameData;
	private EventFactory eventFactory;
	private TextFieldListenerFactory textFieldListenerFactory;
	private ButtonListenerFactory buttonListenerFactory;
	private ComboBoxListenerFactory comboBoxListenerFactory;
	
	public DealController(FrameNavigator frameNavigator, ISessionData sessionData, IGameData gameData) {
		this.frameNavigator = frameNavigator;
		this.sessionData = sessionData;
		this.gameData = Objects.requireNonNull(gameData);
		initialize();
	}
	
	public EventFactory getEventFactory() {
		return eventFactory;
	}

	public IGameData getGameData() {
		return gameData;
	}

	private void initialize() {
		eventFactory = EventFactory.getDefaultFactory(sessionData);
		textFieldListenerFactory = new TextFieldListenerFactory(this);
		buttonListenerFactory = new ButtonListenerFactory(this);
		comboBoxListenerFactory = new ComboBoxListenerFactory(this);
	}
	
	@Override
	public ActionListener createTextFieldListener(String eventName, JComboBox<String> associatedComboBox, JComponent nextInFocus, JLabel answerBox) {
		return textFieldListenerFactory.create(eventName, associatedComboBox, nextInFocus, answerBox);
	}

	@Override
	public ActionListener createComboBoxListener(UpdateGuard guard, JLabel currentLocation, String eventName, JComponent nextInFocus, JLabel... answerBox) {
		return comboBoxListenerFactory.create(guard, currentLocation, eventName, nextInFocus, answerBox);
	}

	@Override
	public ActionListener createButtonListener(String eventName, JComboBox<String> associatedComboBox, JTextField associatedTextBox, JComponent nextInFocus, JLabel... answerBox) {
		return buttonListenerFactory.create(eventName, associatedComboBox, associatedTextBox, nextInFocus, answerBox);
	}
	
	@Override
	public WindowListener createWindowCloseListener() {
		return new WindowCloseListener(frameNavigator, sessionData, gameData);
	}
}
