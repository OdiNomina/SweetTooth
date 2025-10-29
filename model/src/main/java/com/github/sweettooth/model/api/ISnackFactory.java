package com.github.sweettooth.model.api;

import java.util.ArrayList;

import com.github.sweettooth.model.api.viewAPI.Snackable;
import com.github.sweettooth.model.snacks.CandyFactory;

public interface ISnackFactory {
	// --- launcher
	
	public enum SnackType {
		Candy
	}
	
	public static ISnackFactory getFactory(SnackType type) {
		return switch(type) {
			case Candy -> CandyFactory.getInstance();
		};
	}
	
	// --- view
	
	public default ArrayList<? extends Snackable> defaultSnacks() {
		return this.getDefault();
	}
	
	ArrayList<? extends Snackable> getDefault();
}
