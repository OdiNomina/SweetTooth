package com.github.sweettooth.controllerLanterna.textBoxInputFilters;

import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.gameEvents.Processable;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

public class SeekQuantityInputFilter extends TextBoxInputFilter {	
	public SeekQuantityInputFilter(IGameData gameData, Processable event, Interactable nextInFocus, Label answerBox){
		super(nextInFocus, gameData, event, answerBox);
	}
	
	@Override
	public boolean onInput(Interactable interactable, KeyStroke keyStroke) {
		TextBox seekQuantity = (TextBox) interactable;
		
		if(keyStroke.getKeyType() == KeyType.Enter) {
			if(seekQuantity.getText().isBlank())
				seekQuantity.removeLine(0);
			else
				try {
					Integer input = Integer.parseInt(seekQuantity.getText().strip());
					if(input > 100) throw new NumberFormatException();
					nextInFocus.setEnabled(true).takeFocus();
					seekQuantity.setEnabled(false);
				}
				catch(NumberFormatException e) {
					seekQuantity.removeLine(0);
					answerBox.setText("Du musst eine Zahl eingeben! (<= 100)");
				}
			nextInFocus.takeFocus();
			return false;
		}
		return true;
	}
}