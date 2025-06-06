package com.github.sweettooth.model.snacks;

import com.github.sweettooth.model.api.Snackable;

final class Lollipop extends Candy implements Snackable {
	static double price;
	
	Lollipop(){
		super("Lutscher", 5, 15);
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
	void setStaticPrice(double staticPrice) {
		price = staticPrice;
	}
}
