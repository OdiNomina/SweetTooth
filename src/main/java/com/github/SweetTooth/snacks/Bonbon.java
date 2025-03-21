package com.github.SweetTooth.snacks;

final class Bonbon extends Candy implements Snackable {
	static double price;
	
	Bonbon() {
		super("Bonbon", 10, 45);
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
