package com.github.sweettooth.model.snacks;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import com.github.sweettooth.model.api.snacks.Snackable;

public abstract class Snack implements Snackable {
	
	public static Snack findSnack(ArrayList<? extends Snack> list, String snackName) throws NoSuchElementException {
		return list.stream().filter( t -> t.getName().equalsIgnoreCase(snackName.strip())).findFirst().get();
	}
	
	final String name;
	int quantity = 1;
	
	Snack(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public abstract double getPrice();

	@Override
	public int getQuantity() {
		return quantity;
	}
	
	public abstract void increaseQuantity(int number);
	public abstract void reduceQuantity(int number);

	public void setQuantity(int quantity) {
		this.quantity = quantity > 0 ? quantity : 0;
	}
	
	public abstract <T extends Snack> T cloneSnack();
}
