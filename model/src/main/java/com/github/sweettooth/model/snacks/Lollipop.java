package com.github.sweettooth.model.snacks;

import com.github.sweettooth.model.commons.Tools;

final class Lollipop extends Candy {
	static double price;
	
	Lollipop(Double min, Double max){
		super("Lutscher", min, max);
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
		return Tools.rounded(price);
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
