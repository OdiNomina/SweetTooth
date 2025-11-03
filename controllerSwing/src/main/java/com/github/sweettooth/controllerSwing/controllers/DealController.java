package com.github.sweettooth.controllerSwing.controllers;

import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.Locale;
import java.util.Objects;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.github.sweettooth.controllerSwing.api.IDealController;
import com.github.sweettooth.controllerSwing.dealButtonListener.ButtonListenerFactory;
import com.github.sweettooth.controllerSwing.dealComboBoxListener.ComboBoxListenerFactory;
import com.github.sweettooth.controllerSwing.dealTextFieldListener.TextFieldListenerFactory;
import com.github.sweettooth.controllerSwing.dealWindowListener.DealWindowCloseListener;
import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.gameEvents.EventFactory;
import com.github.sweettooth.model.api.gameSession.ISessionData;
import com.github.sweettooth.shared.api.gameControl.GameNavigator;
import com.github.sweettooth.shared.api.gameControl.WindowNavigator;
import com.github.sweettooth.shared.api.util.UpdateGuard;

public class DealController implements IDealController {
	private final GameNavigator gameNavigator;
	private final WindowNavigator windowNavigator;
	private ISessionData sessionData;
	private IGameData gameData;
	private EventFactory eventFactory;
	private TextFieldListenerFactory textFieldListenerFactory;
	private ButtonListenerFactory buttonListenerFactory;
	private ComboBoxListenerFactory comboBoxListenerFactory;
	
	public DealController(GameNavigator gameNavigator, WindowNavigator windowNavigator, ISessionData sessionData, IGameData gameData) {
		this.gameNavigator = gameNavigator;
		this.windowNavigator = windowNavigator;
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
	
	public GameNavigator getGameNavigator() {
		return gameNavigator;
	}
	
	public Locale getLocale() {
		return sessionData.getGlobalSettings().getLocale();
	}
	
	public WindowNavigator getWindowNavigator() {
		return windowNavigator;
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
		return new DealWindowCloseListener(this, sessionData, gameData);
	}
}
