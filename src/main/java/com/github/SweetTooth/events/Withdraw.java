package com.github.SweetTooth.events;

import com.github.SweetTooth.characters.MoneyDealer;
import com.github.SweetTooth.characters.Player;

final class Withdraw extends Event implements IEvent{
	@Override
	public String handleEvent() {
		double amount = doubleInput > 0 ? doubleInput : integerInput;
		amount = Math.round(amount * 100) / 100.00;
		Player player = game.getPlayer();
		MoneyDealer bank = game.getBank();
		if(bank.getBalance(player) - amount < MoneyDealer.getBankMinBalance())
			return "Die Bank zahlt dir diese Summe nicht aus.";
		player.addCash(amount);
		bank.reduceClientsBalance(player, amount);
		return "Betrag ausbezahlt.";
	}
}
