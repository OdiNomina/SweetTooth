package com.github.sweettooth.model.commons;

import java.util.Locale;

import com.github.sweettooth.model.api.settings.IGlobalSettings;

public class GlobalSettings implements IGlobalSettings {
	private Locale locale;

	public GlobalSettings() {
		locale = Locale.GERMANY;
	}
	
	public GlobalSettings(Locale locale) {
		this.locale = locale;
	}
	
	public Locale getLocale() {
		return locale;
	}
}
