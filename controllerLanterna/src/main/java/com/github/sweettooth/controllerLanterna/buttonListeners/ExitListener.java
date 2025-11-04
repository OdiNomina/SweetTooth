package com.github.sweettooth.controllerLanterna.buttonListeners;

import com.github.sweettooth.model.api.IGameRound;
import com.github.sweettooth.model.api.gameEvents.Processable;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Interactable;

public class ExitListener extends ButtonListener {
	IGameRound gameData;
	
	public ExitListener(Interactable nextInFocus, IGameRound gameData, Processable event) {
		super(nextInFocus);
		this.gameData = gameData;
	}
	
	@Override
	public void onTriggered(Button button) {
		gameData.setExitButtonClicked(true);
		gameData.notifyObservers();
	}
}
