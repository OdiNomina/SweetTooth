package com.github.SweetTooth.snacks;

import java.util.ArrayList;

import com.github.SweetTooth.locations.Location;

public interface Snackable {
	public static void changeCandyPrices(ArrayList<? extends Candy> candies, Location location) {
		for(Candy c : candies) {
			c.setRandomStaticPrice(location);
		}
	}
	
	public static Snackable findSnack(ArrayList<? extends Snackable> list, String snackName) {
		for(Snackable s : list) {
			if(s.getName().equalsIgnoreCase(snackName.strip()))
				return s;
		}
		return null;
	}
	
	<T extends Snackable> T cloneSnack();
	boolean equals(Object obj);
	String getName();
	int getQuantity();
	double getStaticPrice();
	int hashCode();
	void increaseQuantity(int number);
	void reduceQuantity(int number);
	void setQuantity(int quantity);
}