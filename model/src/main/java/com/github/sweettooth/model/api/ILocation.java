package com.github.sweettooth.model.api;

import com.github.sweettooth.model.locations.Location;

public interface ILocation {
	// --- view
	
	static ILocation[] values() {
		return Location.values();
	}
	
	/**
	 * Returns the official name of the location represented by enum entity.
	 * @return 	the locations's official name.
	 */
	String getOfficialName();
	
	// --- controller
	
	/**
	 * Returns the corresponding enum entity.
	 * @param 	officialName the official name of the location represented by enum entity.
	 * @return 	enum entity.
	 * @exception IllegalArgumentException
	 * 				if argument is no valid location name.
	 */
	static ILocation valueOfficialName(String officialName) {
		return Location.valueOfficialName(officialName);
	}
}
