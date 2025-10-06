package com.github.sweettooth.controllerSwing.dealButtonListener;

import java.awt.event.ActionListener;

import javax.swing.JComponent;

import com.github.sweettooth.model.api.IGameData;

abstract class ButtonListener implements ActionListener {
	IGameData gameData;
	JComponent nextInFocus;
	
	ButtonListener(IGameData gameData, JComponent nextInFocus) {
		this.gameData = gameData;
		this.nextInFocus = nextInFocus;
	}
}
