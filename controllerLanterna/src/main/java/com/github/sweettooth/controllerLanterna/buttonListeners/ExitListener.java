package com.github.sweettooth.controllerLanterna.buttonListeners;

import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.controllerAPI.Processable;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Interactable;

public class ExitListener extends ButtonListener {
	IGameData gameData;
	
	public ExitListener(Interactable nextInFocus, IGameData gameData, Processable event) {
		super(nextInFocus);
		this.gameData = gameData;
	}
	
	@Override
	public void onTriggered(Button button) {
		gameData.setExitButtonClicked(true);
		gameData.notifyObservers();
	}
}
