package com.github.sweettooth.controllerLanterna.api;

import com.github.sweettooth.controllerLanterna.controllers.LanternaController;
import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.gameSession.ISessionData;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.InputFilter;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.TextBox;

@SuppressWarnings("exports")
public interface ILanternaController {
	static ILanternaController getInstance() {
		return new LanternaController();
	}
	
	ILanternaController initialize(ISessionData sessionData, IGameData gameModel) throws NullPointerException;
	
	/**
	 * Creates the matching input filter for the event.
	 * @param eventName the name of the corresponding event.
	 * @param associatedComboBox the content of this combo box can be used.
	 * @param nextInFocus the Interactable that gets the focus after a valid input.
	 * @param answerBox the Label that shows the answer.
	 * @return lanterna.gui2.InputFilter
	 */
	InputFilter createInputFilter(String eventName, ComboBox<String> associatedComboBox, Interactable nextInFocus, Label answerBox);
	
	/**
	 * Creates the matching combo box listener for the event. 
	 * @param thisComboBox the combo box to which the listener is added.
	 * @param eventName the name of the corresponding event.
	 * @param nextInFocus the Interactable that gets the focus after the selection has been changed.
	 * @param answerBox the Label that shows the answer.
	 * @return ComboBox.Listener
	 */
	ComboBox.Listener createComboBoxListener(ComboBox<String> thisComboBox, String eventName, Interactable nextInFocus, Label... answerBox);
	
	/**
	 * Creates the matching button listener for the event.
	 * @param eventName the name of the corresponding event.
	 * @param associatedComboBox the content of this combo box can be used.
	 * @param associatedTextBox the content of this text box can be used.
	 * @param nextInFocus the Interactable that gets the focus after the button has been triggered.
	 * @param answerBox the Label that shows the answer.
	 * @return Button.Listener
	 */
	Button.Listener createButtonListener(String eventName, ComboBox<String> associatedComboBox, TextBox associatedTextBox, Interactable nextInFocus, Label... answerBox);
}
