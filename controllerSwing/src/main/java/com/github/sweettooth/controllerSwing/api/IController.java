package com.github.sweettooth.controllerSwing.api;

import com.github.sweettooth.model.api.IGameData;

import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.github.sweettooth.controllerSwing.controllers.SwingController;

@SuppressWarnings("exports")
public interface IController {
	static IController getInstance() {
		return new SwingController();
	}
	
	IController initialize(IGameData gameModel) throws NullPointerException;
	
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
	ActionListener createComboBoxListener(JLabel currentLocation, String eventName, JComponent nextInFocus, JLabel... answerBox);
	
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
}
