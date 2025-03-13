package com.github.SweetTooth.snacks;

import java.util.concurrent.ThreadLocalRandom;

final class Lollipop extends Candy implements Snackable {
	static double price;
	
	Lollipop(){
		super("Lutscher");
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if (!super.equals(obj))
			return false;
		return true;
	}
	
	@Override
	public double getStaticPrice() {
		return rounded(price);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public void setRandomStaticPrice() {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		price = rounded(random.nextDouble(5, 15));
	}
}
