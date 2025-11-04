package com.github.sweettooth.controllerSwing.dealButtonListener;

import java.awt.event.ActionListener;

import javax.swing.JComponent;

import com.github.sweettooth.model.api.IGameRound;

abstract class ButtonListener implements ActionListener {
	IGameRound gameData;
	JComponent nextInFocus;
	
	ButtonListener(IGameRound gameData, JComponent nextInFocus) {
		this.gameData = gameData;
		this.nextInFocus = nextInFocus;
	}
}
