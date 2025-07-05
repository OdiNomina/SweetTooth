package com.github.sweettooth.model.snacks;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import com.github.sweettooth.model.api.viewAPI.Snackable;
import com.github.sweettooth.model.locations.Location;

public abstract class Snack implements Snackable {
	public static Snack findSnack(ArrayList<? extends Snack> list, String snackName) throws NoSuchElementException {
		return list.stream().filter( t -> t.getName().equalsIgnoreCase(snackName.strip())).findFirst().get();
	}
	
	public abstract <T extends Snack> T cloneSnack();
	public abstract String getName();
	public abstract int getQuantity();
	public abstract double getStaticPrice();
	public abstract void increaseQuantity(int number);
	public abstract void reduceQuantity(int number);
	public abstract void setQuantity(int quantity);
	public abstract void setRandomStaticPrice(Location location);
}
