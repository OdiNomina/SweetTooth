package com.github.SweetTooth.gui;

import com.github.SweetTooth.events.Event;
import com.googlecode.lanterna.gui2.InputFilter;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

class InputFilterFinances implements InputFilter {	
	PanelContentMain contentPanel;
	Event event;
	Label answerBox;
	
	InputFilterFinances(PanelContentMain contentPanel, Event event){
		this.contentPanel = contentPanel;
		this.event = event;
		switchBoxes();
	}
	
	private void switchBoxes() {
		switch(event.getClass().getSimpleName()) {
			case "Deposit", "Withdraw" -> answerBox = contentPanel.labels.get("bankInfo");
			case "Lend", "GiveMoneyBack" -> answerBox = contentPanel.labels.get("loansharkInfo");
		}
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
					contentPanel.updateContent();
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