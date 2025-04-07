package com.github.SweetTooth.gui;

import com.github.SweetTooth.events.Event;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.InputFilter;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

class InputFilterDeal implements InputFilter {	
	PanelContentMain contentPanel;
	Event event;
	Label answerBox;
	ComboBox<String> selectionBox;
	
	InputFilterDeal(PanelContentMain contentPanel, Event event){
		this.contentPanel = contentPanel;
		this.event = event;
		switchBoxes();
	}
	
	private void switchBoxes() {
		answerBox = contentPanel.labels.get("buySellInfo");
		switch(event.getClass().getSimpleName()) {
			case "Buy"-> selectionBox = contentPanel.comboBoxes.get("buySelection");
			case "Sell" -> selectionBox = contentPanel.comboBoxes.get("sellSelection");
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
					contentPanel.updateContent();
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