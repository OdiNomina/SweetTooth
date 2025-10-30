package com.github.sweettooth.model.api.settings;

import com.github.sweettooth.model.api.snacks.ISnackFactory;
import com.github.sweettooth.model.commons.GameSettings;

public interface IGameSettings {
	/**
	 * <pre>
	 * Creates a new instance of GameSettings with default configurations.
	 * Default settings:
	 * SnackFactory = CandyFactory
	 * @return a new instance with default settings
	 * </pre>
	 */
	public static IGameSettings getInstance() {
		return (IGameSettings)new GameSettings();
	}
	
	/**
	 * <pre>
	 * Creates a new instance of GameSettings.
	 * @param snackFactory the desired factory
	 * @return a new instance
	 * </pre>
	 */
	public static IGameSettings getInstance(ISnackFactory snackFactory) {
		return (IGameSettings)new GameSettings(snackFactory);
	}
	
	ISnackFactory getSnackFactory();
	double getTravelCosts();
}
