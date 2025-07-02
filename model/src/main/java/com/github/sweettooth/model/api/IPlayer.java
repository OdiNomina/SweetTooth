package com.github.sweettooth.model.api;

import java.util.ArrayList;

public interface IPlayer {
	// --- view
	
	public abstract double getCash();
	public abstract ArrayList<? extends Snackable> getCandies();
	public abstract ArrayList<? extends Snackable> getCandyStash();
	public abstract ILocation getLocation();
}
