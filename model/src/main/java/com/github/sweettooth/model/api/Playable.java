package com.github.sweettooth.model.api;

import java.util.ArrayList;

public interface Playable {
	// --- view
	
	public abstract double getCash();
	public abstract ArrayList<? extends Snackable> getCandies();
	public abstract ArrayList<? extends Snackable> getCandyStash();
	public abstract LocationInterface getLocation();
}
