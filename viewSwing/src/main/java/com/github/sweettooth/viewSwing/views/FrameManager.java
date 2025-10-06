package com.github.sweettooth.viewSwing.views;

import java.util.logging.Logger;

import com.github.sweettooth.model.api.GameSettings;
import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.shared.api.Loggable;
import com.github.sweettooth.shared.api.UpdateGuard;

public class FrameManager implements UpdateGuard, Loggable {
	private final Logger logger;
	IGameData gameData;
	GameSettings settings;
	boolean updating;
	
	FrameManager(IGameData gameData, GameSettings settings) {
		logger = Logger.getLogger(FrameManager.class.getName());
		this.gameData = gameData;
		this.settings = settings;
	}

	@Override
	public Logger getLogger() {
		return logger;
	}

	@Override
    public boolean isUpdating() {
        return updating;
    }
	
	void setGameSettings(GameSettings settings) {
		this.settings = settings;
	}
}
