package com.github.SweetTooth.events;

import com.github.SweetTooth.characters.IMoneyDealer;

final class Withdraw extends Event implements IEvent{
	@Override
	public String handleEvent() {
		if(!isAtHometown()) 
			return notAtHometown;
		return withdraw();
	}
	
	private String withdraw() {
		double amount = doubleInput > 0 ? doubleInput : integerInput;
		amount = Math.round(amount * 100) / 100.00;
		IMoneyDealer bank = IMoneyDealer.create("Bank");
		if(bank.getBalance(player) - amount < IMoneyDealer.getBankMinBalance())
			return "Die Bank zahlt dir diese Summe nicht aus.";
		player.addCash(amount);
		bank.reduceClientsBalance(player, amount);
		return "Betrag ausbezahlt.";
	}
}
