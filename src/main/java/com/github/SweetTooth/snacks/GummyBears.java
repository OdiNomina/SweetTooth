package com.github.SweetTooth.snacks;

import java.util.concurrent.ThreadLocalRandom;

final class GummyBears extends Candy implements Snackable {
	static double price;
	
	GummyBears(){
		super("Gummibärchen");
	}
	
	@Override
	void setRandomStaticPrice() {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		price  = rounded(random.nextDouble(0.7, 2.5));
	}
	
	@Override
	public double getStaticPrice() {
		return rounded(price);
	}
}
