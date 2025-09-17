package com.github.sweettooth.controllerSwing.buttonListener;

import java.awt.event.ActionListener;

import javax.swing.JComponent;

abstract class ButtonListener implements ActionListener {
	JComponent nextInFocus;
	
	ButtonListener(JComponent nextInFocus) {
		this.nextInFocus = nextInFocus;
	}
}
