package com.github.sweettooth.model.snacks;

import java.util.ArrayList;

public abstract class SnackFactory {
	SnackFactory(){}
	
	public abstract Snackable create(String snackName);
	public abstract ArrayList<? extends Snackable> getDefaultSnacks(); //Generics sind nicht kovariant
	
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
