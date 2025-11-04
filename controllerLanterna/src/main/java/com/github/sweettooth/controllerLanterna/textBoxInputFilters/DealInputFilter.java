package com.github.sweettooth.controllerLanterna.textBoxInputFilters;

import com.github.sweettooth.model.api.IGameRound;
import com.github.sweettooth.model.api.gameEvents.Processable;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

public class DealInputFilter extends TextBoxInputFilter {	
	ComboBox<String> associatedComboBox;
	
	public DealInputFilter(IGameRound gameRound, Processable event, ComboBox<String> associatedComboBox, Interactable nextInFocus, Label answerRecipient) {
		super(nextInFocus, gameRound, event, answerRecipient);
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
					String[] eventAnswer = event.process(associatedComboBox.getSelectedItem(), input, null);
					gameRound.notifyObservers();
					
					associatedComboBox.setEnabled(true);
					textbox.setEnabled(false);
					answerRecipient.setText(eventAnswer[0]);
				}
				catch(NumberFormatException ex) {
					textbox.removeLine(0);
					answerRecipient.setText("Du musst eine Zahl eingeben! (<= 100)");
				}
			nextInFocus.takeFocus();
		}
		return false;
	}
}