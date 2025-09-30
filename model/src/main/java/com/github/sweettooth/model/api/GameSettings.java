package com.github.sweettooth.model.api;

import java.util.Locale;

import com.github.sweettooth.model.api.ISnackFactory.SnackType;
import com.github.sweettooth.model.commons.InternSettings;

public class GameSettings {
	private Locale locale;
	private ISnackFactory snackFactory;
	
	public GameSettings() {
		locale = Locale.GERMANY;
		snackFactory = ISnackFactory.getFactory(SnackType.Candy);
	}
	
	public GameSettings(Locale locale, ISnackFactory snackFactory) {
		this.locale = locale;
		this.snackFactory = snackFactory;
	}
	
	// --- view
	
	public double getTravelCosts() {
		return InternSettings.TRAVEL_COSTS;
	}
	
	// --- model and view
	
	public Locale getLocale() {
		return locale;
	}
	
	public ISnackFactory getSnackFactory() {
		return snackFactory;
	}
}
