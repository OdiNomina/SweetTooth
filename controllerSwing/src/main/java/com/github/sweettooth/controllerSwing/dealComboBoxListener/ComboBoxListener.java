package com.github.sweettooth.controllerSwing.dealComboBoxListener;

import java.awt.event.ActionListener;

import javax.swing.JComponent;

import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.shared.api.UpdateGuard;

public abstract class ComboBoxListener implements ActionListener {
	IGameData gameData;
	UpdateGuard guard;
	JComponent nextInFocus;
	
	ComboBoxListener(IGameData gameData, UpdateGuard guard, JComponent nextInFocus) {
		this.gameData = gameData;
		this.guard = guard;
		this.nextInFocus = nextInFocus;
	}
}
