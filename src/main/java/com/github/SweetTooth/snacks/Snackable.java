package com.github.SweetTooth.snacks;

import java.util.ArrayList;

public interface Snackable {
	public static void changePrices() {
		Candy.setRandomStaticPrices();
	}
	
	/**
	 * Searches for a matching CandyType and returns the corresponding default candy instance.
	 * @param candyName the final name of candy.
	 * @return candy instance or null if no corresponding CandyType exists.
	 */
	public static Snackable valueOf(String candyName) {
		return Candy.valueOf(candyName);
	}
	
	public static Snackable valueOf(ArrayList<Snackable> list, String candyName) {
		return Candy.valueOf(list, candyName);
	}
	
	String getName();
	int getQuantity();
	double getStaticPrice();
	void increaseQuantity(int number);
	void reduceQuantity(int number);
	void setQuantity(int quantity);
}