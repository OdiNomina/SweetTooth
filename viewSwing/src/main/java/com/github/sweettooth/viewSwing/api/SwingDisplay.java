package com.github.sweettooth.viewSwing.api;

import com.github.sweettooth.model.api.GameSettings;
import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.viewSwing.views.SwingGUI;

public interface SwingDisplay extends Runnable {
	@SuppressWarnings("exports")
	static SwingDisplay getInstance(IGameData gameData) {
		return new SwingGUI(gameData);
	}
	
	@SuppressWarnings("exports")
	SwingDisplay initialize(GameSettings modelSettings) throws NullPointerException;
	
	void run();
}
