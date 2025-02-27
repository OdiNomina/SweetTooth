package com.github.SweetTooth.snacks;

public abstract class CandyFactory {
	public static CandyFactory getDefaultFactory() {
		return new DefaultCandyFactory();
	}
	
	public abstract Snackable create(Snackable name);
}
