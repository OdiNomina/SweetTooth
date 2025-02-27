package com.github.SweetTooth.snacks;

import java.util.ArrayList;

abstract sealed class Candy implements Snackable permits
	Bonbon, BubbleGum, ChewyCandy, ChocolateBar, GummyBears, Lollipop
{
	private final String name;
	private int quantity = 1;
	
	Candy(String name) {
		this.name = name;
	}
	
	static void setRandomStaticPrices() {
		Candy candy = null;
		for(CandyType ct : CandyType.values()) {
			candy = (Candy)ct.getCandy();
			candy.setRandomStaticPrice();
		}
	}
	
	static Snackable valueOf(ArrayList<Snackable> list, String candyName) {
		for(Snackable s : list) {
			if(s.getName().equalsIgnoreCase(candyName.strip()))
				return s;
		}
		return null;
	}
	
	/**
	 * Searches for a matching CandyType and returns the corresponding default candy instance.
	 * @param candyName the final name of candy.
	 * @return candy instance or null if no corresponding CandyType exists.
	 */
	static Snackable valueOf(String candyName) {
		Snackable curCandy = null;
		for(CandyType ct : CandyType.values()) {
			curCandy = ct.getCandy();
			if(curCandy.getName().equalsIgnoreCase(candyName.strip()))
				return curCandy;
		}
		return null;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public int getQuantity() {
		return quantity;
	}
	
	@Override
	public void increaseQuantity(int number) {
		quantity += number;
	}

	@Override
	public void reduceQuantity(int number) {
		quantity -= number;
	}

	double rounded(double amount) {
		return Math.round(amount * 100) / 100.00;
	}
	
	@Override
	public void setQuantity(int quantity) {
		this.quantity = quantity > 0 ? quantity : 0;
	}
	
	abstract void setRandomStaticPrice();
}
