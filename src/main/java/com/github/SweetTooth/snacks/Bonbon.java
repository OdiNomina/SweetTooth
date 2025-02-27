package com.github.SweetTooth.snacks;

import java.util.concurrent.ThreadLocalRandom;

final class Bonbon extends Candy implements Snackable {
	static double price;
	
	Bonbon() {
		super("Bonbon");
	}
	
	@Override
	void setRandomStaticPrice() {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		price = rounded(random.nextDouble(10, 45));
	}

	@Override
	public double getStaticPrice() {
		return rounded(price);
	}
}
