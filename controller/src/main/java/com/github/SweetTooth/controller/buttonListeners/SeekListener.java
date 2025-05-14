package com.github.SweetTooth.controller.buttonListeners;

import com.github.SweetTooth.model.events.Event;
import com.github.SweetTooth.model.events.Observer;
import com.github.SweetTooth.model.games.Game;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.TextBox;

public class SeekListener extends ButtonListener {
	ComboBox<String> associatedComboBox;
	TextBox associatedTextBox;
	Game game;
	Event event;
	Label[] answerBox;
	
	public SeekListener(ComboBox<String> associatedComboBox, TextBox associatedTextBox, Interactable nextInFocus, Game game, Event event, Label... answerBox) {
		super(nextInFocus);
		this.associatedComboBox = associatedComboBox;
		this.associatedTextBox = associatedTextBox;
		this.game = game;
		this.event = event;
		this.answerBox = answerBox;
	}
	
	@Override
	public void onTriggered(Button button) {
		String snackInput = associatedComboBox.getSelectedItem();
		Integer snackQuantity;
		try {
			snackQuantity = Integer.parseInt(associatedTextBox.getText());
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
			snackQuantity = 0;
		}
		String answer = event.handle(snackInput, snackQuantity, null);
		for(Observer view : game.getViews())
			view.updateObserver();
		answerBox[0].setText(answer);
		associatedTextBox.setEnabled(true);
		button.setEnabled(false);
		nextInFocus.takeFocus();
	}
}
