package com.github.sweettooth.model.apiView;

import java.util.ArrayList;

import com.github.sweettooth.model.locations.Location;
import com.github.sweettooth.model.snacks.Candy;

public interface Snackable {
	public static void changeSnackPrices(ArrayList<? extends Snackable> snacks, Location location) {
		for(Snackable s : snacks) {
			Candy c = (Candy)s;
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