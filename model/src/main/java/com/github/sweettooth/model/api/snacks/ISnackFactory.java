package com.github.sweettooth.model.api.snacks;

import java.util.ArrayList;

import com.github.sweettooth.model.snacks.SnackFactory;

public interface ISnackFactory {
	
	/**
	 * Enum representing the types of snacks that factories can create. 
	 */
	public enum SnackType {
		Candy
	}
	
	/**
	 * Returns the unique instance of a specific SnackFactory that depends on the SnackType.
	 * @param type determines the specific type of factory
	 * @return the unique instance of the desired factory
	 */
	public static ISnackFactory getSnackFactory(SnackType type) {
		return SnackFactory.getSnackFactory(type);
	}
	
	/**
	 * Returns a list of default snacks from this factory.
	 * @return an ArrayList with snacks that all implement Snackable
	 */
	ArrayList<? extends Snackable> getDefaultSnacks();
}
