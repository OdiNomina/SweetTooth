package com.github.sweettooth.model.api;

import java.util.Currency;
import java.util.Locale;

import com.github.sweettooth.model.commons.InternSettings;

public class ModelSettings {
	private Locale locale;
	private String currency;
	private ISnackFactory snackFactory;
	
	public ModelSettings(Locale locale, ISnackFactory snackFactory) {
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
	
	public ISnackFactory getSnackFactory() {
		return snackFactory;
	}

	public double getTravelCosts() {
		return InternSettings.TRAVEL_COSTS;
	}
}
