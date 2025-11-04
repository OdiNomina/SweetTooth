package com.github.sweettooth.controllerSwing.dealButtonListener;

import java.awt.event.ActionEvent;

import javax.swing.JComponent;

import com.github.sweettooth.model.api.IGameRound;
import com.github.sweettooth.model.api.gameEvents.Processable;

public class ExitListener extends ButtonListener {
	
	public ExitListener(JComponent nextInFocus, IGameRound gameData, Processable event) {
		super(gameData, nextInFocus);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gameRound.setExitButtonClicked(true);
		gameRound.notifyObservers();
	}
}
