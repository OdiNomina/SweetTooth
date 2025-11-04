package com.github.sweettooth.controllerLanterna.buttonListeners;

import com.github.sweettooth.model.api.IGameRound;
import com.github.sweettooth.model.api.gameEvents.Processable;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.TextBox;

public class SeekListener extends ButtonListener {
	ComboBox<String> associatedComboBox;
	TextBox associatedTextBox;
	IGameRound gameRound;
	Processable event;
	Label[] answerRecipient;
	
	public SeekListener(ComboBox<String> associatedComboBox, TextBox associatedTextBox, Interactable nextInFocus, IGameRound gameRound, Processable event, Label... answerRecipient) {
		super(nextInFocus);
		this.associatedComboBox = associatedComboBox;
		this.associatedTextBox = associatedTextBox;
		this.gameRound = gameRound;
		this.event = event;
		this.answerRecipient = answerRecipient;
	}
	
	@Override
	public void onTriggered(Button button) {
		String snackInput = associatedComboBox.getSelectedItem();
		Integer snackQuantity;
		try {
			snackQuantity = Integer.parseInt(associatedTextBox.getText());
		}
		catch(NumberFormatException ex) {
			ex.printStackTrace();
			snackQuantity = 0;
		}
		String[] eventAnswer = event.process(snackInput, snackQuantity, null);
		
		gameRound.notifyObservers();
		
		answerRecipient[0].setText(eventAnswer[0]);
		
		associatedTextBox.setEnabled(true);
		button.setEnabled(false);
		nextInFocus.takeFocus();
	}
}
