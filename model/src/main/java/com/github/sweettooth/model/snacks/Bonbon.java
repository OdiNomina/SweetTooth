package com.github.sweettooth.model.snacks;

import com.github.sweettooth.model.api.viewAPI.Snackable;
import com.github.sweettooth.model.commons.Tools;

final class Bonbon extends Candy implements Snackable {
	static double price;
	
	Bonbon(Double min, Double max) {
		super("Bonbon", min, max);
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
