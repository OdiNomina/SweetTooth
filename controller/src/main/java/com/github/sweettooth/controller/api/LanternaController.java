package com.github.sweettooth.controller.api;

import com.github.sweettooth.model.games.Game;
import com.github.sweettooth.model.events.EventFactory;
import com.github.sweettooth.model.events.DefaultEventFactory;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.InputFilter;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.TextBox;

@SuppressWarnings("exports")
public class LanternaController {
	private Game game;
	private EventFactory eventFactory;
	private InputFilterFactory inputFilterFactory;
	private ButtonListenerFactory buttonListenerFactory;
	private ComboBoxListenerFactory comboBoxListenerFactory;
	
	public LanternaController(Game game) {
		this.game = game;
		eventFactory = new DefaultEventFactory();
	}
	
	public void initialize() {
		inputFilterFactory = new InputFilterFactory(this);
		buttonListenerFactory = new ButtonListenerFactory(this);
		comboBoxListenerFactory = new ComboBoxListenerFactory(this);
	}
	
	public Game getGame() {
		return game;
	}
	
	EventFactory getEventFactory() {
		return eventFactory;
	}
	
	/**
	 * Creates the matching input filter for the event.
	 * @param eventName the name of the corresponding event.
	 * @param associatedComboBox the content of this combo box can be used.
	 * @param nextInFocus the Interactable that gets the focus after a valid input.
	 * @param answerBox the Label that shows the answer.
	 * @return lanterna.gui2.InputFilter
	 */
	public InputFilter createInputFilter(String eventName, ComboBox<String> associatedComboBox, Interactable nextInFocus, Label answerBox) {
		return inputFilterFactory.createInputFilter(eventName, associatedComboBox, nextInFocus, answerBox);
	}
	
	/**
	 * Creates the matching combo box listener for the event. 
	 * @param thisComboBox the combo box to which the listener is added.
	 * @param eventName the name of the corresponding event.
	 * @param nextInFocus the Interactable that gets the focus after the selection has been changed.
	 * @param answerBox the Label that shows the answer.
	 * @return ComboBox.Listener
	 */
	public ComboBox.Listener createComboBoxListener(ComboBox<String> thisComboBox, String eventName, Interactable nextInFocus, Label... answerBox) {
		return comboBoxListenerFactory.createComboBoxListener(thisComboBox, eventName, nextInFocus, answerBox);
	}
	
	/**
	 * Creates the matching button listener for the event.
	 * @param eventName the name of the corresponding event.
	 * @param associatedComboBox the content of this combo box can be used.
	 * @param associatedTextBox the content of this text box can be used.
	 * @param nextInFocus the Interactable that gets the focus after the button has been triggered.
	 * @param answerBox the Label that shows the answer.
	 * @return Button.Listener
	 */
	public Button.Listener createButtonListener(String eventName, ComboBox<String> associatedComboBox, TextBox associatedTextBox, Interactable nextInFocus, Label... answerBox) {
		return buttonListenerFactory.createButtonListener(eventName, associatedComboBox, associatedTextBox, nextInFocus, answerBox);
	}
}
