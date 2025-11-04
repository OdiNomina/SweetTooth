package com.github.sweettooth.model.api.settings;

import com.github.sweettooth.model.api.snacks.ISnackFactory;
import com.github.sweettooth.model.settings.GameSettings;

public interface IGameSettings {
	/**
	 * Creates a new instance of GameSettings with default configurations.<br>
	 * Default settings:<br>
	 * SnackFactory = CandyFactory
	 * @return a new instance with default settings
	 */
	public static IGameSettings getInstance() {
		return new GameSettings();
	}
	
	/**
	 * Creates a new instance of GameSettings.
	 * @param snackFactory the desired factory
	 * @return a new instance
	 */
	public static IGameSettings getInstance(ISnackFactory snackFactory) {
		return new GameSettings(snackFactory);
	}
	
	ISnackFactory getSnackFactory();
	double getTravelCosts();
}
