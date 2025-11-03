package com.github.sweettooth.controllerSwing.dealComboBoxListener;

import java.awt.event.ActionEvent;

import javax.swing.JComponent;

import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.shared.api.util.UpdateGuard;

public class DealSelectionListener extends ComboBoxListener {
	public DealSelectionListener(IGameData gameData, UpdateGuard guard, JComponent nextInFocus) {
		super(gameData, guard, nextInFocus);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(guard.isUpdating()) return; // programmatisches Event ignorieren
		
		gameData.notifyObservers();
		
	    nextInFocus.setEnabled(true);
	    nextInFocus.requestFocusInWindow();
	}
}
