package com.github.sweettooth.model.api.characters;

import java.util.ArrayList;

import com.github.sweettooth.model.api.ILocation;
import com.github.sweettooth.model.api.snacks.Snackable;

public interface IPlayer {
	double getCash();
	ILocation getCurrentLocation();
	String getName();
	ArrayList<? extends Snackable> getSnacksFromPockets();
	ArrayList<? extends Snackable> getSnacksFromStash();
	void resetRoundData();
}
