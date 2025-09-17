package com.github.sweettooth.controllerSwing.buttonListener;

import java.awt.event.ActionEvent;

import javax.swing.JComponent;

import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.controllerAPI.Processable;

public class ExitListener extends ButtonListener {
	IGameData gameData;
	
	public ExitListener(JComponent nextInFocus, IGameData gameData, Processable event) {
		super(nextInFocus);
		this.gameData = gameData;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gameData.setExitButtonClicked(true);
		gameData.notifyObservers();
	}
}
