package com.github.sweettooth.controller.buttonListeners;

import com.github.sweettooth.model.api.Processable;
import com.github.sweettooth.model.games.GameData;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Interactable;

public class ExitListener extends ButtonListener {
	GameData gameData;
	
	public ExitListener(Interactable nextInFocus, GameData gameData, Processable event) {
		super(nextInFocus);
		this.gameData = gameData;
	}
	
	@Override
	public void onTriggered(Button button) {
		gameData.setGameOver(true);
		gameData.gameDataChanged();
	}
}
