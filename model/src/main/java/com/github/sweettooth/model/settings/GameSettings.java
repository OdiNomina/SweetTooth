package com.github.sweettooth.model.settings;

import com.github.sweettooth.model.api.settings.IGameSettings;
import com.github.sweettooth.model.api.snacks.ISnackFactory;
import com.github.sweettooth.model.api.snacks.ISnackFactory.SnackType;

public class GameSettings implements IGameSettings {
	private ISnackFactory snackFactory;
	
	public GameSettings() {
		snackFactory = ISnackFactory.getSnackFactory(SnackType.Candy);
	}
	
	public GameSettings(ISnackFactory snackFactory) {
		this.snackFactory = snackFactory;
	}
	
	public ISnackFactory getSnackFactory() {
		return snackFactory;
	}

	public double getTravelCosts() {
		return InternSettings.TRAVEL_COSTS;
	}
}
