package com.github.sweettooth.model.locations;

import com.github.sweettooth.model.api.ILocation;

public enum Location implements ILocation {
	BRONX("Bronx", 1),
	GHETTO("Ghetto", 1.1),
	CENTRAL_PARK("Central Park", 1.3),
	MANHATTEN("Manhatten", 1.5),
	CONEY_ISLAND("Coney Island", 1.4),
	BROOKLYN("Brooklyn", 1.2);
	
	/**
	 * Returns the initial name of the corresponding enum entity.
	 * @param 	officialName the official name of the location represented by enum entity.
	 * @return 	name of enum entity.
	 * @exception IllegalArgumentException
	 * 				if argument is no valid location name.
	 */
//	public static String getEnumName(String officialName) {
//		for(Location staticObject : Location.values()) {
//			if(staticObject.officialName.equalsIgnoreCase(officialName.strip()))
//				return staticObject.name();
//		}
//		throw new IllegalArgumentException("Invalid argument.");
//	}
	
	/**
	 * Returns the corresponding enum entity.
	 * @param 	officialName the official name of the location represented by enum entity.
	 * @return 	enum entity.
	 * @exception IllegalArgumentException
	 * 				if argument is no valid location name.
	 */
	public static Location valueOfficialName(String officialName) {
		for(Location staticObject : Location.values()) {
			if(staticObject.officialName.equalsIgnoreCase(officialName.strip()))
				return staticObject;
		}
		throw new IllegalArgumentException("Invalid argument.");
	}

	private String officialName;
	private double priceFactor;
	
	Location(String officialName, double priceFactor){
		this.officialName = officialName;
		this.priceFactor = priceFactor;
	}
	
	/**
	 * Returns the official name of the location represented by enum entity.
	 * @return 	the locations's official name.
	 */
	@Override
	public String getOfficialName() {
		return officialName;
	}
	
	/**
	 * The prices of the snacks vary by the returned factor. 
	 * @return the price factor of this location.
	 */
	public double getPriceFactor() {
		return priceFactor;
	}
}
