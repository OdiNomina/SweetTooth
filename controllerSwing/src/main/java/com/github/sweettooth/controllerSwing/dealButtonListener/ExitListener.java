package com.github.sweettooth.controllerSwing.dealButtonListener;

import java.awt.event.ActionEvent;

import javax.swing.JComponent;

import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.controllerAPI.Processable;

public class ExitListener extends ButtonListener {
	
	public ExitListener(JComponent nextInFocus, IGameData gameData, Processable event) {
		super(gameData, nextInFocus);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gameData.setExitButtonClicked(true);
		gameData.notifyObservers();
	}
}
