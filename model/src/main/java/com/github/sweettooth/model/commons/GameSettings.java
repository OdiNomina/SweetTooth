package com.github.sweettooth.model.commons;

import com.github.sweettooth.model.api.ISnackFactory;
import com.github.sweettooth.model.api.ISnackFactory.SnackType;
import com.github.sweettooth.model.api.settings.IGameSettings;

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
