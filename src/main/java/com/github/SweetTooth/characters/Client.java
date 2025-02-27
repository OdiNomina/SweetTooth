package com.github.SweetTooth.characters;

import com.github.SweetTooth.locations.Location;

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
}
