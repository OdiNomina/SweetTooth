package com.github.sweettooth.controller.textBoxInputFilters;

import com.github.sweettooth.model.api.GameModelInterface;
import com.github.sweettooth.model.api.Processable;

import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

public class FinancesInputFilter extends TextBoxInputFilter {	
	public FinancesInputFilter(GameModelInterface gameData, Processable event, Interactable nextInFocus, Label answerBox) {
		super(nextInFocus, gameData, event, answerBox);
	}
	
	@Override
	public boolean onInput(Interactable interactable, KeyStroke keyStroke) {
		TextBox textbox = (TextBox) interactable;
		
		if(keyStroke.getKeyType() == KeyType.Enter) {
			if(textbox.getText().isBlank())
				textbox.removeLine(0);
			else
				try {
					Double input = Double.parseDouble(textbox.getText().strip());
					if(input > 100_000)
						throw new NumberFormatException();
					String eventAnswer = event.process(null, null, input);
					gameData.gameDataChanged();
					
					answerBox.setText(eventAnswer);
					textbox.removeLine(0);
					nextInFocus.takeFocus();
				}
				catch(NumberFormatException e) {
					textbox.removeLine(0);
					answerBox.setText("Du musst eine Zahl eingeben! (<= 100.000)");
				}
			return false;
		}
		return true;
	}
}