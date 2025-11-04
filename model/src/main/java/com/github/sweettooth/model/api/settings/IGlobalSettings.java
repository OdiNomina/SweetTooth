package com.github.sweettooth.model.api.settings;

import java.util.Locale;

import com.github.sweettooth.model.settings.GlobalSettings;

public interface IGlobalSettings {
	/**
	 * Creates a new instance of GlobalSettings with default configurations.<br>
	 * Default settings:<br>
	 * java.util.Locale.GERMANY
	 * @return a new instance with default settings
	 */
	public static IGlobalSettings getInstance() {
		return new GlobalSettings();
	}
	
	/**
	 * Creates a new instance of GlobalSettings.
	 * @param locale the global regional settings for formatting 
	 * @return a new instance
	 */
	public static IGlobalSettings getInstance(Locale locale) {
		return new GlobalSettings(locale);
	}
	
	public Locale getLocale();
}
