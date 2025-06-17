package com.github.sweettooth.model.api;

import java.util.Currency;
import java.util.Locale;

public class Settings {
	// --- view
	
	private Locale locale;
	private String currency;
	private double travelCosts;
	
	public Settings(Locale locale) {
		this.locale = locale;
		currency = Currency.getInstance(locale).getSymbol();
		travelCosts = Double.valueOf(10);
	}
	
	public Locale getLocale() {
		return locale;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public double getTravelCosts() {
		return travelCosts;
	}
}
