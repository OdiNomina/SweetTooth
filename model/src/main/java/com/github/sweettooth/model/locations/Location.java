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
	 * Returns the corresponding enum object for the specified official name of a location.
	 * @param name the official name of a location
	 * @return an enum object representing the specified name
	 * @exception IllegalArgumentException if the argument is not a valid location name
	 */
	public static Location valueOfficialName(String name) {
		for(Location staticObject : Location.values()) {
			if(staticObject.officialName.equalsIgnoreCase(name.strip()))
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
