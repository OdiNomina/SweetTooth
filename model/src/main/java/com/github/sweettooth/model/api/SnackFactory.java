package com.github.sweettooth.model.api;

import java.util.ArrayList;

import com.github.sweettooth.model.snacks.CandyFactory;

public abstract class SnackFactory {
	public SnackFactory(){}
	
	public static ArrayList<? extends Snackable> getDefaultSnacks() {
		return new CandyFactory().getDefaultCandies();
	}
	
	/**
	 * Returns a random default Snackable.
	 * @return a new Snackable instance.
	 */
	public abstract Snackable getRandom();
	
	/**
	 * Searches for a matching default Snackable and returns a new instance.
	 * @param snackName the final name.
	 * @return a new Snackable instance or null if no corresponding Snackable exists.
	 */
	public abstract Snackable valueOf(String snackName);
}
