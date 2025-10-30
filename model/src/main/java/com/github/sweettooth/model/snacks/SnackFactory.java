package com.github.sweettooth.model.snacks;

import java.util.ArrayList;

import com.github.sweettooth.model.api.ISnackFactory;
import com.github.sweettooth.model.api.viewAPI.Snackable;
import com.github.sweettooth.model.locations.Location;

public abstract class SnackFactory implements ISnackFactory {
	
	public static ISnackFactory getSnackFactory(SnackType type) {
		return switch(type) {
			case Candy -> CandyFactory.getInstance();
		};
	}
	
	/**
	 * Changes the prices of all default snacks from this factory.
	 * @param location prices depend on location
	 */
	public abstract void changeSnackPrices(Location location);
	
	@Override
	public abstract ArrayList<? extends Snackable> getDefaultSnacks();

	/**
	 * Returns a clone of a random default snack.
	 * @return a copy of a snack object
	 */
	public abstract Snack getRandomSnack();

	/**
	 * Searches for a matching default snack and returns a new instance.
	 * @param snackName the name of the desired snack
	 * @return a new Snack instance or null if no corresponding snack exists
	 */
	public abstract Snack valueOf(String snackName);
}