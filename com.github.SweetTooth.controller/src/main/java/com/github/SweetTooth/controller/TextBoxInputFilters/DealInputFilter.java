package com.github.SweetTooth.controller.TextBoxInputFilters;

import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.InputFilter;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

public class DealInputFilter extends TextBoxInputFilter implements InputFilter {	
	ComboBox<String> selectionBox;
	
	public DealInputFilter(String textBoxName){
		super(textBoxName);
		switchSelectionBox(textBoxName);
	}
	
	private void switchSelectionBox(String textBoxName) {
		switch(textBoxName) {
			case "buyQuantity" -> selectionBox = getPanelContent().getComboBoxes().get("buySelection");
			case "sellQuantity" -> selectionBox = getPanelContent().getComboBoxes().get("sellSelection");
		}
	}
	
	@Override
	public boolean onInput(Interactable interactable, KeyStroke keyStroke) {
		TextBox textbox = (TextBox) interactable;
		
		if(keyStroke.getKeyType() == KeyType.Character || keyStroke.getKeyType() == KeyType.Backspace)
			return true;
		
		if(keyStroke.getKeyType() == KeyType.Enter) {
			if(textbox.getText().isBlank()) {
				textbox.removeLine(0);
    			return false;
			}
			else {
				try {
					Integer input = Integer.parseInt(textbox.getText().strip());
					if(input > 100)
						throw new NumberFormatException();
					String eventAnswer = event.handle(selectionBox.getSelectedItem(), input, null);
					getPanelContent().updateContent();
					selectionBox.setEnabled(true).takeFocus();
					textbox.setEnabled(false);
					answerBox.setText(eventAnswer);
					return false;
				}
				catch(NumberFormatException e) {
					textbox.removeLine(0);
					answerBox.setText("Du musst eine Zahl eingeben! (<= 100)");
        			return false;
				}
			}
		}
		return false;
	}
}