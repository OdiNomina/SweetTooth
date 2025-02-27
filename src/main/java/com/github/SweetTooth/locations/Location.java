package com.github.SweetTooth.locations;

public enum Location {
	BRONX("Bronx"),
	GHETTO("Ghetto"),
	CENTRAL_PARK("Central Park"),
	MANHATTEN("Manhatten"),
	CONEY_ISLAND("Coney Island"),
	BROOKLYN("Brooklyn");
	
	private String officialName;
	
	Location(String officialName){
		this.officialName = officialName;
	}
	
	/**
	 * Returns the initial name of the corresponding enum entity.
	 * @param 	officialName the official name of the location represented by enum entity.
	 * @return 	name of enum entity.
	 * @exception IllegalArgumentException
	 * 				if argument is no valid location name.
	 */
	public static String getEnumName(String officialName) {
		for(Location staticObject : Location.values()) {
			if(staticObject.officialName.equalsIgnoreCase(officialName.strip()))
				return staticObject.name();
		}
		throw new IllegalArgumentException("Invalid argument.");
	}
	
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
	
	/**
	 * Returns the official name of the location represented by enum entity.
	 * @return 	the locations's official name.
	 */
	public String getOfficialName() {
		return officialName;
	}
}
