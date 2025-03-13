package com.github.SweetTooth.snacks;

import java.util.ArrayList;

public abstract class CandyFactory {
	public abstract Snackable create(String snackName);
	public abstract ArrayList<Snackable> getDefaultSnacks();
	
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
