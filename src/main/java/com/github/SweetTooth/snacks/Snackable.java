package com.github.SweetTooth.snacks;

import java.util.ArrayList;

public interface Snackable {
	public static void changeCandyPrices(ArrayList<Snackable> snacks) {
		Candy.setRandomStaticPrices(snacks);
	}
	
	public static Snackable findSnack(ArrayList<Snackable> list, String snackName) {
		for(Snackable s : list) {
			if(s.getName().equalsIgnoreCase(snackName.strip()))
				return s;
		}
		return null;
	}
	
	Snackable clone();
	boolean equals(Object obj);
	String getName();
	int getQuantity();
	double getStaticPrice();
	int hashCode();
	void increaseQuantity(int number);
	void reduceQuantity(int number);
	void setQuantity(int quantity);
}