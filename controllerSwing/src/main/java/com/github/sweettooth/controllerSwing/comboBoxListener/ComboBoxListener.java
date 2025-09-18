package com.github.sweettooth.controllerSwing.comboBoxListener;

import java.awt.event.ActionListener;

import javax.swing.JComponent;

import com.github.sweettooth.shared.api.UpdateGuard;

public abstract class ComboBoxListener implements ActionListener {
	UpdateGuard guard;
	JComponent nextInFocus;
	
	ComboBoxListener(UpdateGuard guard, JComponent nextInFocus) {
		this.guard = guard;
		this.nextInFocus = nextInFocus;
	}
}
