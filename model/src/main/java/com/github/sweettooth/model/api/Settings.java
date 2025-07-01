package com.github.sweettooth.model.api;

import java.util.Currency;
import java.util.Locale;

public class Settings {
	// --- view
	
	private Locale locale;
	private String currency;
	private double travelCosts;
	private SnackFactory snackFactory;
	
	public Settings(Locale locale, SnackFactory snackFactory) {
		this.locale = locale;
		this.snackFactory = snackFactory;
		currency = Currency.getInstance(locale).getSymbol();
		travelCosts = Double.valueOf(10);
	}
	
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
		return travelCosts;
	}
}
