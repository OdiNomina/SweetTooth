package com.github.SweetTooth.gui;

import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.TextBox;

class MyTextBox extends TextBox {
	String initialText;
	
	@Override
	protected void afterEnterFocus(FocusChangeDirection direction, Interactable previouslyInFocus) {
        this.removeLine(0);
    }
	
	@Override
	protected void afterLeaveFocus(FocusChangeDirection direction, Interactable nextInFocus) {
        this.setText(initialText);
    }
	
	public void setInitialText(String initialText) {
		this.initialText = initialText;
	}
}
