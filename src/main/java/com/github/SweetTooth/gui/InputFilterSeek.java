package com.github.SweetTooth.gui;

import com.github.SweetTooth.events.Event;
import com.googlecode.lanterna.gui2.InputFilter;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

class InputFilterSeek implements InputFilter {	
	PanelContentMain contentPanel;
	Event event;
	
	InputFilterSeek(PanelContentMain contentPanel, Event event){
		this.contentPanel = contentPanel;
		this.event = event;
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
					contentPanel.buttons.get("seek").setEnabled(true).takeFocus();
					textbox.setEnabled(false);
					return false;
				}
				catch(NumberFormatException e) {
					textbox.removeLine(0);
					contentPanel.labels.get("hideSeekInfo").setText("Du musst eine Zahl eingeben! (<= 100)");
        			return false;
				}
			}
		}
		return true;
	}
}