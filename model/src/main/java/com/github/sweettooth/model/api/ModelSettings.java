package com.github.sweettooth.model.api;

import java.util.Currency;
import java.util.Locale;

import com.github.sweettooth.model.commons.InternSettings;

public class ModelSettings {
	private Locale locale;
	private String currency;
	private SnackFactory snackFactory;
	
	public ModelSettings(Locale locale, SnackFactory snackFactory) {
		this.locale = locale;
		this.snackFactory = snackFactory;
		currency = Currency.getInstance(locale).getSymbol();
	}
	
	// --- model and view
	
	public String getCurrency() {
		return currency;
	}
	
	public Locale getLocale() {
		return locale;
	}
	
	public SnackFactory getSnackFactory() {
		return snackFactory;
	}

	public double getTravelCosts() {
		return InternSettings.TRAVEL_COSTS;
	}
}
