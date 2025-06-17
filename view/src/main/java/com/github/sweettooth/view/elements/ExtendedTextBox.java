package com.github.sweettooth.view.elements;

import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.TextBox;

class ExtendedTextBox extends TextBox {
	private String initialText;
	
	ExtendedTextBox(String initialText){
		super(initialText);
	}
	
	public TextBox setInitialText(String text) {
		this.initialText = text;
		return this;
	}
	
	public String getInitialText() {
		return initialText;
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