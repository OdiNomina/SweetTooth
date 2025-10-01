package com.github.sweettooth.controllerLanterna.controllers;

import java.util.Objects;

import com.github.sweettooth.controllerLanterna.api.ILanternaController;
import com.github.sweettooth.controllerLanterna.buttonListeners.ButtonListenerFactory;
import com.github.sweettooth.controllerLanterna.comboBoxListeners.ComboBoxListenerFactory;
import com.github.sweettooth.controllerLanterna.textBoxInputFilters.InputFilterFactory;
import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.controllerAPI.EventFactory;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.InputFilter;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.TextBox;

public class LanternaController implements ILanternaController {
	private IGameData gameModel;
	private EventFactory eventFactory;
	private InputFilterFactory inputFilterFactory;
	private ButtonListenerFactory buttonListenerFactory;
	private ComboBoxListenerFactory comboBoxListenerFactory;
	
	public LanternaController() {}
	
	@Override
	public ILanternaController initialize(IGameData gameModel) throws NullPointerException {
		this.gameModel = Objects.requireNonNull(gameModel);
		eventFactory = EventFactory.getDefaultFactory();
		inputFilterFactory = new InputFilterFactory(this);
		buttonListenerFactory = new ButtonListenerFactory(this);
		comboBoxListenerFactory = new ComboBoxListenerFactory(this);
		return this;
	}
	
	public IGameData getGameModel() {
		return gameModel;
	}
	
	public EventFactory getEventFactory() {
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
	@Override
	public InputFilter createInputFilter(String eventName, ComboBox<String> associatedComboBox, Interactable nextInFocus, Label answerBox) {
		return inputFilterFactory.create(eventName, associatedComboBox, nextInFocus, answerBox);
	}
	
	/**
	 * Creates the matching combo box listener for the event. 
	 * @param thisComboBox the combo box to which the listener is added.
	 * @param eventName the name of the corresponding event.
	 * @param nextInFocus the Interactable that gets the focus after the selection has been changed.
	 * @param answerBox the Label that shows the answer.
	 * @return ComboBox.Listener
	 */
	@Override
	public ComboBox.Listener createComboBoxListener(ComboBox<String> thisComboBox, String eventName, Interactable nextInFocus, Label... answerBox) {
		return comboBoxListenerFactory.create(thisComboBox, eventName, nextInFocus, answerBox);
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
	@Override
	public Button.Listener createButtonListener(String eventName, ComboBox<String> associatedComboBox, TextBox associatedTextBox, Interactable nextInFocus, Label... answerBox) {
		return buttonListenerFactory.create(eventName, associatedComboBox, associatedTextBox, nextInFocus, answerBox);
	}
}
