package com.github.SweetTooth.controller.ButtonListeners;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Interactable;

abstract class ButtonListener implements Button.Listener {
	Interactable nextInFocus;
	
	ButtonListener(Interactable nextInFocus) {
		this.nextInFocus = nextInFocus;
	}
}
