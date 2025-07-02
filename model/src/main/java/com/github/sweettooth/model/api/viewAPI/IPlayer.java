package com.github.sweettooth.model.api.viewAPI;

import java.util.ArrayList;

import com.github.sweettooth.model.api.ILocation;
import com.github.sweettooth.model.characters.Player;

public interface IPlayer {
	public default double cash() {
		return ((Player)this).getCash();
	}
	
	public default ArrayList<? extends Snackable> snacks() {
		return ((Player)this).getCandies();
	}
	
	public default ArrayList<? extends Snackable> stash() {
		return ((Player)this).getCandyStash();
	}
	
	public default ILocation location() {
		return ((Player)this).getLocation();
	}
}
