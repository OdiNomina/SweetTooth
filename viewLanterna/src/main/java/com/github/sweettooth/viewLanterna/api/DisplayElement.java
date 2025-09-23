package com.github.sweettooth.viewLanterna.api;

import com.github.sweettooth.model.api.GameSettings;
import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.viewLanterna.views.LanternaGUI;

public interface DisplayElement extends Runnable {
	@SuppressWarnings("exports")
	static DisplayElement getInstance(IGameData gameData) {
		return new LanternaGUI(gameData);
	}
	
	@SuppressWarnings("exports")
	DisplayElement initialize(GameSettings modelSettings) throws NullPointerException;
	
	void run();
}
