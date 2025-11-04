package com.github.sweettooth.controllerSwing.dealComboBoxListener;

import java.awt.event.ActionListener;

import javax.swing.JComponent;

import com.github.sweettooth.model.api.IGameRound;
import com.github.sweettooth.shared.api.util.UpdateGuard;

public abstract class ComboBoxListener implements ActionListener {
	IGameRound gameRound;
	UpdateGuard guard;
	JComponent nextInFocus;
	
	ComboBoxListener(IGameRound gameData, UpdateGuard guard, JComponent nextInFocus) {
		this.gameRound = gameData;
		this.guard = guard;
		this.nextInFocus = nextInFocus;
	}
}
