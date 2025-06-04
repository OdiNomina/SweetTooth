package com.github.sweettooth.controller.comboBoxListeners;

import java.io.IOException;

import com.github.sweettooth.model.api.Processable;
import com.github.sweettooth.model.games.GameData;
import com.github.sweettooth.model.locations.Location;

import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;

public class LocationSelectionListener extends ComboBoxListener {
	GameData gameData;
	Processable event;
	Label[] answerBox;
	Processable applyInterestEvent;
	
	public LocationSelectionListener(ComboBox<String> thisComboBox, Interactable nextInFocus, GameData gameData, Processable event, Processable applyInterestEvent, Label... answerBox) {
		super(thisComboBox, nextInFocus);
		this.gameData = gameData;
		this.event = event;
		this.answerBox = answerBox;
		this.applyInterestEvent = applyInterestEvent;
	}
	
	@Override
	public void onSelectionChanged(int selectedIndex, int previousSelection, boolean changedByUserInteraction) {
		if(changedByUserInteraction) {
			if(selectedIndex == previousSelection)
				answerBox[0].setText("Du bist doch schon da!");
			else
				try {
					gameData.increaseDayOfGame(1);
					if(gameData.isGameOver()) {
						gameData.gameDataChanged();
						return;
					}
					Location location = Location.valueOfficialName(thisComboBox.getItem(selectedIndex));	
					Processable.Answer answer = event.handleMultipleAnswers(location.toString(), null, null);
	    			
	    			gameData.gameDataChanged();
	    			
	            	answerBox[0].setText(answer.answer1());
	            	answerBox[1].setText(answer.answer2());
	            	answerBox[2].setText(answer.answer3());
	            	
	            	answerBox[3].setText(applyInterestEvent.handle(null, null, null));
				}
				catch(IllegalArgumentException e) {
					answerBox[0].setText("An exception occurred.");
					thisComboBox.takeFocus();
					e.printStackTrace();
				}
				catch(ArrayIndexOutOfBoundsException e) {
					e.printStackTrace();
					gameData.gameDataChanged();
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			nextInFocus.takeFocus();
		}
	}
}
