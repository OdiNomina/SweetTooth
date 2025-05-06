package com.github.SweetTooth.controller.textBoxInputFilters;

import com.github.SweetTooth.model.events.Event;
import com.github.SweetTooth.model.games.Game;

import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

public class SeekQuantityInputFilter extends TextBoxInputFilter {	
	public SeekQuantityInputFilter(Game game, Event event, Interactable nextInFocus, Label answerBox){
		super(nextInFocus, game, event, answerBox);
	}
	
	@Override
	public boolean onInput(Interactable interactable, KeyStroke keyStroke) {
		TextBox seekQuantity = (TextBox) interactable;
		
		if(keyStroke.getKeyType() == KeyType.Enter) {
			if(seekQuantity.getText().isBlank()) { 
				seekQuantity.removeLine(0);
    			return false;
			}
			try {
				Integer input = Integer.parseInt(seekQuantity.getText().strip());
				if(input > 100) throw new NumberFormatException();
				nextInFocus.setEnabled(true).takeFocus();
				seekQuantity.setEnabled(false);
				return false;
			}
			catch(NumberFormatException e) {
				seekQuantity.removeLine(0);
				answerBox.setText("Du musst eine Zahl eingeben! (<= 100)");
    			return false;
			}
		}
		return true;
	}
}