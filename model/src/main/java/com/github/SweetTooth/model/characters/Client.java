package com.github.SweetTooth.model.characters;

import java.util.Objects;

import com.github.SweetTooth.model.locations.Location;

class Client extends Character {
	final Player identity;
	private double balance;
	
	Client(Player player){
		super(Location.BRONX);
		identity = player;
	}
	
	double getBalance() {
		return rounded(balance);
	}
	
	void addAmount(double amount) {
		balance = rounded(balance) + rounded(amount);
	}
	
	void removeAmount(double amount) {
		balance = rounded(balance) - rounded(amount);
	}

	@Override
	public String toString() {
		StringBuffer builder = new StringBuffer();
		builder.append("Client [identity=").append(identity)
			.append(", balance=").append(balance)
			.append(", hometown=").append(hometown)
			.append(", location=").append(location)
			.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(balance, identity);
		return result;
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
		Client other = (Client) obj;
		long thisBalance = balance == 0.0 ? 0L : Double.doubleToLongBits(balance);
		long otherBalance = other.balance == 0.0 ? 0L : Double.doubleToLongBits(other.balance);
		return thisBalance == otherBalance
				&& Objects.equals(identity, other.identity);
	}
}
