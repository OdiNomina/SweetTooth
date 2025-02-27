package com.github.SweetTooth.snacks;

import java.util.concurrent.ThreadLocalRandom;

final class BubbleGum extends Candy implements Snackable {
	static double price;
	
	BubbleGum(){
		super("Kaugummi");
	}
	
	@Override
	void setRandomStaticPrice() {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		price = rounded(random.nextDouble(15, 30));
	}

	@Override
	public double getStaticPrice() {
		return rounded(price);
	}
}
