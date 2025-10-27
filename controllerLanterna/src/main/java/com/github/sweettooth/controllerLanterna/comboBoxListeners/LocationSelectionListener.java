package com.github.sweettooth.controllerLanterna.comboBoxListeners;

import java.io.IOException;

import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.ILocation;
import com.github.sweettooth.model.api.gameEvents.Processable;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;

public class LocationSelectionListener extends ComboBoxListener {
	IGameData gameData;
	Processable event;
	Label[] answerBox;
	Processable applyInterestEvent;
	
	public LocationSelectionListener(ComboBox<String> thisComboBox, Interactable nextInFocus, IGameData gameData, Processable event, Processable applyInterestEvent, Label... answerBox) {
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
						gameData.notifyObservers();
						return;
					}
					ILocation location = ILocation.valueOfficialName(thisComboBox.getItem(selectedIndex));	
					Processable.Answer answer = event.processMultipleAnswers(location.toString(), null, null);
	    			
	    			gameData.notifyObservers();
	    			
	            	answerBox[0].setText(answer.answer1());
	            	answerBox[1].setText(answer.answer2());
	            	answerBox[2].setText(answer.answer3());
	            	
	            	answerBox[3].setText(applyInterestEvent.process(null, null, null));
				}
				catch(IllegalArgumentException e) {
					answerBox[0].setText("An exception occurred.");
					thisComboBox.takeFocus();
					e.printStackTrace();
				}
				catch(ArrayIndexOutOfBoundsException e) {
					e.printStackTrace();
					gameData.notifyObservers();
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			nextInFocus.takeFocus();
		}
	}
}
