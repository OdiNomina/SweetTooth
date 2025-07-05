package com.github.sweettooth.model.snacks;

import com.github.sweettooth.model.api.ISnackFactory;
import com.github.sweettooth.model.locations.Location;

public abstract class SnackFactory implements ISnackFactory {
	
	public abstract void changeSnackPrices(Location location);
	
	/**
	 * Returns a random default Snack.
	 * @return a new Snack instance.
	 */
	public abstract Candy getRandom();
	
	/**
	 * Searches for a matching default Snackable and returns a new instance.
	 * @param snackName the final name.
	 * @return a new Snackable instance or null if no corresponding Snackable exists.
	 */
	public abstract Snack valueOf(String snackName);
}