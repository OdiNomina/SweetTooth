package com.github.SweetTooth.snacks;

import java.util.concurrent.ThreadLocalRandom;

final class ChewyCandy extends Candy implements Snackable {
	static double price;
	
	ChewyCandy(){
		super("Kaubonbon");
	}
	
	@Override
	void setRandomStaticPrice() {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		price = rounded(random.nextDouble(0.1, 0.6));
	}
	
	@Override
	public double getStaticPrice() {
		return rounded(price);
	}
}
