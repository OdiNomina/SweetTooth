package com.github.sweettooth.model.characters;

import java.util.Objects;

import com.github.sweettooth.model.locations.Location;

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

	@Override
	public int hashCode() {
		return Objects.hash(hometown, location);
	}

	@Override
	public boolean equals(Object obj) {
		Character other = (Character) obj;
		return hometown == other.hometown && location == other.location;
	}
}
