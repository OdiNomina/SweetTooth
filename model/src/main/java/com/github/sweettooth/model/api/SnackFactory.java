package com.github.sweettooth.model.api;

import java.util.ArrayList;

import com.github.sweettooth.model.api.viewAPI.Snackable;
import com.github.sweettooth.model.snacks.CandyFactory;

public abstract class SnackFactory {
	// --- launcher
	
	public enum SnackType {
		Candy
	}
	
	public static SnackFactory createFactory(SnackType type) {
		return switch(type) {
			case Candy -> CandyFactory.getInstance();
		};
	}
	
	// --- model and launcher
	
	public abstract ArrayList<Snackable> getDefaultSnacks();
	
	// --- model
	
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
