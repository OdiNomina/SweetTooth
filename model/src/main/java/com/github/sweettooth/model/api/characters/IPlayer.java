package com.github.sweettooth.model.api.characters;

import java.util.ArrayList;

import com.github.sweettooth.model.api.ILocation;
import com.github.sweettooth.model.api.snacks.Snackable;
import com.github.sweettooth.model.characters.Player;

public interface IPlayer {
	public default ArrayList<? extends Snackable> snacks() {
		return ((Player)this).getSnacksInPockets();
	}
	
	public default ArrayList<? extends Snackable> stash() {
		return ((Player)this).getSnacksInStash();
	}
	
	double getCash();
	ILocation getLocation();
	String getName();
	void reset();
}
