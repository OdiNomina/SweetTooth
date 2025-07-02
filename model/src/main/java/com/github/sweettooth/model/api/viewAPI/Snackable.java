package com.github.sweettooth.model.api.viewAPI;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import com.github.sweettooth.model.api.ILocation;
import com.github.sweettooth.model.snacks.Candy;

public interface Snackable {
	// --- model
	
	public static void changeSnackPrices(ArrayList<? extends Snackable> snacks, ILocation location) {
		snacks.stream().map( t -> (Candy)t ).forEach(  t -> t.setRandomStaticPrice(location) );
	}
	
	public static Snackable findSnack(ArrayList<? extends Snackable> list, String snackName) throws NoSuchElementException {
		return list.stream().filter( t -> t.getName().equalsIgnoreCase(snackName.strip())).findFirst().get();
	}
	
	<T extends Snackable> T cloneSnack();
	void increaseQuantity(int number);
	void reduceQuantity(int number);
	void setQuantity(int quantity);
	
	// --- model and view
	
	String getName();
	int getQuantity();
	double getStaticPrice();
}