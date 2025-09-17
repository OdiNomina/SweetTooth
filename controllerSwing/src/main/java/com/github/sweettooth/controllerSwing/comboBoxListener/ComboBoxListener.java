package com.github.sweettooth.controllerSwing.comboBoxListener;

import java.awt.event.ActionListener;

import javax.swing.JComponent;

public abstract class ComboBoxListener implements ActionListener {
	JComponent nextInFocus;
	
	ComboBoxListener(JComponent nextInFocus) {
		this.nextInFocus = nextInFocus;
	}
}
