package com.github.SweetTooth.controller.TextBoxInputFilters;

import com.github.SweetTooth.controller.controllerAPI.LanternaController;
import com.googlecode.lanterna.gui2.InputFilter;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

public class FinancesInputFilter extends TextBoxInputFilter {	
	public FinancesInputFilter(String textBoxName, LanternaController controller){
		super(textBoxName, controller);
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
					getPanelContent().updateContent();
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