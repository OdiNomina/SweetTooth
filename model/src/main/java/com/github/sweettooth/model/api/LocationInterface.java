package com.github.sweettooth.model.api;

import com.github.sweettooth.model.locations.Location;

public interface LocationInterface {
	public static LocationInterface[] values() {
		return Location.values();
	}
	
	/**
	 * Returns the initial name of the corresponding enum entity.
	 * @param 	officialName the official name of the location represented by enum entity.
	 * @return 	name of enum entity.
	 * @exception IllegalArgumentException
	 * 				if argument is no valid location name.
	 */
	static String getEnumName(String officialName) {
		return Location.getEnumName(officialName);
	}
	
	/**
	 * Returns the corresponding enum entity.
	 * @param 	officialName the official name of the location represented by enum entity.
	 * @return 	enum entity.
	 * @exception IllegalArgumentException
	 * 				if argument is no valid location name.
	 */
	public static LocationInterface valueOfficialName(String officialName) {
		return Location.valueOfficialName(officialName);
	}
	
	/**
	 * Returns the official name of the location represented by enum entity.
	 * @return 	the locations's official name.
	 */
	public String getOfficialName();
	
	/**
	 * The prices of the snacks vary by the returned factor. 
	 * @return the price factor of this location.
	 */
	public double getPriceFactor();
}
