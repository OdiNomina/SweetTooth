package com.github.sweettooth.controller.api;

import com.github.sweettooth.model.api.EventFactory;
import com.github.sweettooth.model.games.GameData;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.InputFilter;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.TextBox;

@SuppressWarnings("exports")
public interface Controller {
	void initializeFactories();
	GameData getGameData();
	InputFilter createInputFilter(String eventName, ComboBox<String> associatedComboBox, Interactable nextInFocus, Label answerBox);
	ComboBox.Listener createComboBoxListener(ComboBox<String> thisComboBox, String eventName, Interactable nextInFocus, Label... answerBox);
	Button.Listener createButtonListener(String eventName, ComboBox<String> associatedComboBox, TextBox associatedTextBox, Interactable nextInFocus, Label... answerBox);
	EventFactory getEventFactory();
}
