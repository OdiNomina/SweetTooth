package com.github.sweettooth.model.api.viewAPI;

import java.util.ArrayList;

import com.github.sweettooth.model.api.ILocation;

public interface IPlayer {
	// --- model and view
	
	public abstract double getCash();
	public abstract ArrayList<? extends Snackable> getCandies();
	public abstract ArrayList<? extends Snackable> getCandyStash();
	public abstract ILocation getLocation();
}
