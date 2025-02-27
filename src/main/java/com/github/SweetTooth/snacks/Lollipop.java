package com.github.SweetTooth.snacks;

import java.util.concurrent.ThreadLocalRandom;

final class Lollipop extends Candy implements Snackable {
	static double price;
	
	Lollipop(){
		super("Lutscher");
	}
	
	@Override
	void setRandomStaticPrice() {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		price = rounded(random.nextDouble(5, 15));
	}
	
	@Override
	public double getStaticPrice() {
		return rounded(price);
	}
}
