package com.github.sweettooth.controllerSwing.api;

import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.gameSession.ISessionData;
import com.github.sweettooth.shared.api.gameControl.GameNavigator;
import com.github.sweettooth.shared.api.gameControl.WindowNavigator;
import com.github.sweettooth.shared.api.util.UpdateGuard;

import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.github.sweettooth.controllerSwing.controllers.DealController;

@SuppressWarnings("exports")
public interface IDealController {
	static IDealController getInstance(GameNavigator gameNavigator, WindowNavigator frameNavigator, ISessionData sessionData, IGameData gameData) {
		return new DealController(gameNavigator, frameNavigator, sessionData, gameData);
	}
	
	/**
	 * Creates the matching text field listener for the event.
	 * @param eventName the name of the corresponding event.
	 * @param associatedComboBox the content of this combo box can be used.
	 * @param nextInFocus the component that gets the focus after a valid input.
	 * @param answerBox the label that shows the answer.
	 * @return java.awt.event.ActionListener
	 */
	ActionListener createTextFieldListener(String eventName, JComboBox<String> associatedComboBox, JComponent nextInFocus, JLabel answerBox);
	
	/**
	 * Creates the matching combo box listener for the event. 
	 * @param previousSelection the previous selected item of this combo box.
	 * @param eventName the name of the corresponding event.
	 * @param nextInFocus the component that gets the focus after the selection has been changed.
	 * @param answerBox the label that shows the answer.
	 * @return java.awt.event.ActionListener
	 */
	ActionListener createComboBoxListener(UpdateGuard guard, JLabel currentLocation, String eventName, JComponent nextInFocus, JLabel... answerBox);
	
	/**
	 * Creates the matching button listener for the event.
	 * @param eventName the name of the corresponding event.
	 * @param associatedComboBox the content of this combo box can be used.
	 * @param associatedTextField the content of this text field can be used.
	 * @param nextInFocus the component that gets the focus after the button has been triggered.
	 * @param answerBox the label that shows the answer.
	 * @return java.awt.event.ActionListener
	 */
	ActionListener createButtonListener(String eventName, JComboBox<String> associatedComboBox, JTextField associatedTextField, JComponent nextInFocus, JLabel... answerBox);
	
	WindowListener createWindowCloseListener();
}
