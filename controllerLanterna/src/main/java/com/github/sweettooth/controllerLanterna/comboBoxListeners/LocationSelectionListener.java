package com.github.sweettooth.controllerLanterna.comboBoxListeners;

import java.io.IOException;

import com.github.sweettooth.model.api.IGameRound;
import com.github.sweettooth.model.api.ILocation;
import com.github.sweettooth.model.api.gameEvents.Processable;

import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;

public class LocationSelectionListener extends ComboBoxListener {
	IGameRound gameRound;
	Processable event;
	Label[] answerRecipient;
	Processable applyInterestEvent;
	
	public LocationSelectionListener(ComboBox<String> thisComboBox, Interactable nextInFocus, IGameRound gameRound, Processable event, Processable applyInterestEvent, Label... answerRecipient) {
		super(thisComboBox, nextInFocus);
		this.gameRound = gameRound;
		this.event = event;
		this.answerRecipient = answerRecipient;
		this.applyInterestEvent = applyInterestEvent;
	}
	
	@Override
	public void onSelectionChanged(int selectedIndex, int previousSelection, boolean changedByUserInteraction) {
		if(changedByUserInteraction) {
			if(selectedIndex == previousSelection)
				answerRecipient[0].setText("Du bist doch schon da!");
			else {
				try {
					gameRound.increaseDayOfGame(1);
					if(gameRound.isGameOver()) {
						gameRound.notifyObservers();
						return;
					}
					ILocation location = ILocation.valueOfficialName(thisComboBox.getItem(selectedIndex));	
					String[] eventAnswer = event.process(location.toString(), null, null);
	    			
	    			gameRound.notifyObservers();
	    			
	            	answerRecipient[0].setText(eventAnswer[0]);
	            	answerRecipient[1].setText(eventAnswer[1]);
	            	answerRecipient[2].setText(eventAnswer[2]);
	            	
	            	answerRecipient[3].setText(applyInterestEvent.process(null, null, null)[0]);
				}
				catch(IllegalArgumentException ex) {
					answerRecipient[0].setText("An exception occurred.");
					thisComboBox.takeFocus();
					ex.printStackTrace();
				}
				catch(ArrayIndexOutOfBoundsException ex) {
					ex.printStackTrace();
					gameRound.notifyObservers();
				}
				catch(IOException ex) {
					ex.printStackTrace();
				}
			}
			nextInFocus.takeFocus();
		}
	}
}
