package com.github.sweettooth.view.api;

import com.github.sweettooth.model.api.GameSettings;
import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.view.elements.LanternaGUI;

public interface DisplayElement extends Runnable {
	@SuppressWarnings("exports")
	static DisplayElement getInstance(IGameData gameData) {
		return new LanternaGUI(gameData);
	}
	
	@SuppressWarnings("exports")
	DisplayElement initialize(GameSettings modelSettings) throws NullPointerException;
	
	void run();
}
