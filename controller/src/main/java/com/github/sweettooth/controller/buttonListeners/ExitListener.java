package com.github.sweettooth.controller.buttonListeners;

import com.github.sweettooth.model.api.GameModelInterface;
import com.github.sweettooth.model.api.Processable;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Interactable;

public class ExitListener extends ButtonListener {
	GameModelInterface gameData;
	
	public ExitListener(Interactable nextInFocus, GameModelInterface gameData, Processable event) {
		super(nextInFocus);
		this.gameData = gameData;
	}
	
	@Override
	public void onTriggered(Button button) {
		gameData.setGameOver(true);
		gameData.gameDataChanged();
	}
}
