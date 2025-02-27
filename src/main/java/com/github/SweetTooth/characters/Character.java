package com.github.SweetTooth.characters;

import com.github.SweetTooth.locations.Location;

abstract class Character {
	Location hometown;
	Location location;
	
	Character(Location hometown) {
		this.hometown = hometown;
		this.location = this.hometown;
	}
	
	public Location getHometown() {
		return hometown;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location cityName) {
		this.location = cityName;
	}
	
	double rounded(double amount) {
		return Math.round(amount * 100) / 100.00;
	}
}
