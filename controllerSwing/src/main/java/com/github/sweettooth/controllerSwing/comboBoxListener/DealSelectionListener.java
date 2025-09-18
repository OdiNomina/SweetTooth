package com.github.sweettooth.controllerSwing.comboBoxListener;

import java.awt.event.ActionEvent;

import javax.swing.JComponent;

import com.github.sweettooth.shared.api.UpdateGuard;

public class DealSelectionListener extends ComboBoxListener {
	public DealSelectionListener(UpdateGuard guard, JComponent nextInFocus) {
		super(guard, nextInFocus);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(guard.isUpdating()) return; // programmatisches Event ignorieren
		
		JComponent comboBox = (JComponent) e.getSource();
		
	    comboBox.setEnabled(false);
	    nextInFocus.setEnabled(true);
	    nextInFocus.requestFocusInWindow();
	}
}
