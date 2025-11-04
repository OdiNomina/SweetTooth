package com.github.sweettooth.model.api.settings;

import java.util.Locale;

import com.github.sweettooth.model.settings.GlobalSettings;

public interface IGlobalSettings {
	/**
	 * <pre>
	 * Creates a new instance of GlobalSettings with default configurations.
	 * Default settings:
	 * java.util.Locale.GERMANY
	 * @return a new instance with default settings
	 * </pre>
	 */
	public static IGlobalSettings getInstance() {
		return (IGlobalSettings) new GlobalSettings();
	}
	
	/**
	 * Creates a new instance of GlobalSettings.
	 * @param locale the global regional settings for formatting 
	 * @return a new instance
	 */
	public static IGlobalSettings getInstance(Locale locale) {
		return (IGlobalSettings) new GlobalSettings(locale);
	}
	
	public Locale getLocale();
}
