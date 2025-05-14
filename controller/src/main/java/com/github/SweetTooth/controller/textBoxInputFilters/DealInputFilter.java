package com.github.SweetTooth.controller.textBoxInputFilters;

import com.github.SweetTooth.model.events.Event;
import com.github.SweetTooth.model.events.Observer;
import com.github.SweetTooth.model.games.Game;

import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

public class DealInputFilter extends TextBoxInputFilter {	
	ComboBox<String> associatedComboBox;
	
	public DealInputFilter(Game game, Event event, ComboBox<String> associatedComboBox, Interactable nextInFocus, Label answerBox) {
		super(nextInFocus, game, event, answerBox);
		this.associatedComboBox = associatedComboBox;
	}
	
	@Override
	public boolean onInput(Interactable interactable, KeyStroke keyStroke) {
		TextBox textbox = (TextBox) interactable;
		
		if(keyStroke.getKeyType() == KeyType.Character || keyStroke.getKeyType() == KeyType.Backspace)
			return true;
		
		if(keyStroke.getKeyType() == KeyType.Enter) {
			if(textbox.getText().isBlank())
				textbox.removeLine(0);
			else
				try {
					Integer input = Integer.parseInt(textbox.getText().strip());
					if(input > 100)
						throw new NumberFormatException();
					String eventAnswer = event.handle(associatedComboBox.getSelectedItem(), input, null);
					for(Observer view : game.getViews())
						view.updateObserver();
					associatedComboBox.setEnabled(true);
					textbox.setEnabled(false);
					answerBox.setText(eventAnswer);
				}
				catch(NumberFormatException e) {
					textbox.removeLine(0);
					answerBox.setText("Du musst eine Zahl eingeben! (<= 100)");
				}
			nextInFocus.takeFocus();
		}
		return false;
	}
}