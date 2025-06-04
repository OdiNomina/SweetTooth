package com.github.sweettooth.model.api;

import java.util.ArrayList;

import com.github.sweettooth.model.locations.Location;

public interface Playable {
	public abstract double getCash();
	public abstract ArrayList<? extends Snackable> getCandies();
	public abstract ArrayList<? extends Snackable> getCandyStash();
	public abstract Location getLocation();
}
