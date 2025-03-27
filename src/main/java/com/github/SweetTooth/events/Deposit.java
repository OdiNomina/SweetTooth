package com.github.SweetTooth.events;

import com.github.SweetTooth.characters.IMoneyDealer;

final class Deposit extends Event implements IEvent{
	@Override
	public String handleEvent() {
		return deposit();
	}
	
	private String deposit() {
		double amount = doubleInput != 0 ? doubleInput : integerInput;
		amount = Math.round(amount * 100) / 100.00;
		if(player.getCash() < amount)
			return "Ups! So viel hab ich gar nicht dabei...";
		player.reduceCash(amount);
		IMoneyDealer.create("Bank").increaseClientsBalance(player, amount);
		return "Betrag einbezahlt.";
	}
}
