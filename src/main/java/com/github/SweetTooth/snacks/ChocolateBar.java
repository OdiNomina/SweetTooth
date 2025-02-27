package com.github.SweetTooth.snacks;

import java.util.concurrent.ThreadLocalRandom;

final class ChocolateBar extends Candy implements Snackable {
	static double price;
	
	ChocolateBar(){
		super("Schokoriegel");
	}
	
	@Override
	void setRandomStaticPrice() {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		price = rounded(random.nextDouble(3, 9));
	}
	
	@Override
	public double getStaticPrice() {
		return rounded(price);
	}
}
