package com.github.sweettooth.viewLanterna.api;

import com.github.sweettooth.model.api.IGameRound;
import com.github.sweettooth.model.api.gameSession.IGameSession;
import com.github.sweettooth.viewLanterna.views.LanternaGUI;

public interface DisplayElement extends Runnable {
	
	@SuppressWarnings("exports")
	static DisplayElement getInstance(IGameSession sessionData, IGameRound gameData) {
		return new LanternaGUI(sessionData, gameData);
	}
	
	void run();
}
