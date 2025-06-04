package com.github.sweettooth.controller.buttonListeners;

import com.github.sweettooth.model.api.Processable;
import com.github.sweettooth.model.games.GameData;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.TextBox;

public class SeekListener extends ButtonListener {
	ComboBox<String> associatedComboBox;
	TextBox associatedTextBox;
	GameData gameData;
	Processable event;
	Label[] answerBox;
	
	public SeekListener(ComboBox<String> associatedComboBox, TextBox associatedTextBox, Interactable nextInFocus, GameData gameData, Processable event, Label... answerBox) {
		super(nextInFocus);
		this.associatedComboBox = associatedComboBox;
		this.associatedTextBox = associatedTextBox;
		this.gameData = gameData;
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
		gameData.gameDataChanged();
		answerBox[0].setText(answer);
		associatedTextBox.setEnabled(true);
		button.setEnabled(false);
		nextInFocus.takeFocus();
	}
}
