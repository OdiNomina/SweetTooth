package com.github.SweetTooth.view.panels;

import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.TextBox;

class TextBoxInitialText extends TextBox {
	String initialText;
	
	TextBoxInitialText(String initialText){
		super(initialText);
		this.initialText = initialText;
	}
	
	@Override
	protected void afterEnterFocus(FocusChangeDirection direction, Interactable previouslyInFocus) {
        this.removeLine(0);
    }
	
	@Override
	protected void afterLeaveFocus(FocusChangeDirection direction, Interactable nextInFocus) {
        this.setText(initialText);
    }
}