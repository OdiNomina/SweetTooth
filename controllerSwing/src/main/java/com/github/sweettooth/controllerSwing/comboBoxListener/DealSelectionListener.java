package com.github.sweettooth.controllerSwing.comboBoxListener;

import java.awt.event.ActionEvent;

import javax.swing.JComponent;

public class DealSelectionListener extends ComboBoxListener {
	public DealSelectionListener(JComponent nextInFocus) {
		super(nextInFocus);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JComponent comboBox = (JComponent) e.getSource();
		
	    comboBox.setEnabled(false);
	    nextInFocus.setEnabled(true);
	    nextInFocus.requestFocusInWindow();
	}
}
