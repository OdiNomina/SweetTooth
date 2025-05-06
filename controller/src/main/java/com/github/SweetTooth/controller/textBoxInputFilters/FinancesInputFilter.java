package com.github.SweetTooth.controller.textBoxInputFilters;

import com.github.SweetTooth.model.events.Event;
import com.github.SweetTooth.model.events.Observer;
import com.github.SweetTooth.model.games.Game;

import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

public class FinancesInputFilter extends TextBoxInputFilter {	
	public FinancesInputFilter(Game game, Event event, Interactable nextInFocus, Label answerBox){
		super(nextInFocus, game, event, answerBox);
	}
	
	@Override
	public boolean onInput(Interactable interactable, KeyStroke keyStroke) {
		TextBox textbox = (TextBox) interactable;
		
		if(keyStroke.getKeyType() == KeyType.Enter) {
			if(textbox.getText().isBlank()) {
				textbox.removeLine(0);
    			return false;
			}
			else {
				try {
					Double input = Double.parseDouble(textbox.getText().strip());
					if(input > 100_000)
						throw new NumberFormatException();
					String eventAnswer = event.handle(null, null, input);
					for(Observer view : game.getViews())
						view.updateObserver();
					answerBox.setText(eventAnswer);
					textbox.removeLine(0);
					
					return false;
				}
				catch(NumberFormatException e) {
					textbox.removeLine(0);
					answerBox.setText("Du musst eine Zahl eingeben! (<= 100.000)");
        			return false;
				}
			}
		}
		return true;
	}
}