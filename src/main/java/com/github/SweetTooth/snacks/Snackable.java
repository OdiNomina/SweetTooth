package com.github.SweetTooth.snacks;

import java.util.ArrayList;

public interface Snackable {
	public static void changePrices(ArrayList<Snackable> snacks) {
		Candy.setRandomStaticPrices(snacks);
	}
	
	public static Snackable valueOf(ArrayList<Snackable> list, String candyName) {
		return Candy.valueOf(list, candyName);
	}
	
	/**
	 * Returns a cloned new Instance.
	 * @param snack a type that is concrete at runtime.
	 * @exception IllegalArgumentException
	 * 				if argument is null.
	 */
	Snackable clone();
	String getName();
	int getQuantity();
	double getStaticPrice();
	void increaseQuantity(int number);
	void reduceQuantity(int number);
	void setQuantity(int quantity);
}