package com.github.SweetTooth.controller.comboBoxListeners;

import java.io.IOException;

import com.github.SweetTooth.model.events.Event;
import com.github.SweetTooth.model.events.Observer;
import com.github.SweetTooth.model.games.Game;
import com.github.SweetTooth.model.locations.Location;

import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;

public class LocationSelectionListener extends ComboBoxListener {
	Game game;
	Event event;
	Label[] answerBox;
	Event applyInterestEvent;
	
	public LocationSelectionListener(ComboBox<String> thisComboBox, Interactable nextInFocus, Game game, Event event, Event applyInterestEvent, Label... answerBox) {
		super(thisComboBox, nextInFocus);
		this.game = game;
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
					for(Observer view : game.getViews())
						view.updateObserver();
					Location location = Location.valueOfficialName(thisComboBox.getItem(selectedIndex));
	    			String input = location.toString();
	            	Event.Answer answer = event.handleMultipleAnswers(input, null, null);
	            	answerBox[0].setText(answer.answer1());
	            	answerBox[1].setText(answer.answer2());
	            	answerBox[2].setText(answer.answer3());
	            	
	            	answerBox[3].setText(applyInterestEvent.handle(null, null, null));
	            	
	            	game.increaseDayOfGame(1);
	            	
	            	for(Observer view : game.getViews())
						view.updateObserver(); //Prüft ob Game Over!
				}
				catch(IllegalArgumentException e) {
					answerBox[0].setText("An exception occurred.");
					thisComboBox.takeFocus();
					e.printStackTrace();
				}
				catch(ArrayIndexOutOfBoundsException e) {
					e.printStackTrace();
					for(Observer view : game.getViews())
						view.updateObserver(); //Prüft ob Game Over!
				}
				catch(IOException e) {
					e.printStackTrace();
				}
		}
	}
}
