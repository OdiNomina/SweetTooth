package com.github.SweetTooth.model.events;

import com.github.SweetTooth.model.characters.MoneyDealer;
import com.github.SweetTooth.model.characters.Player;

final class Withdraw extends Event {
	@Override
	public String handle(String stringInput, Integer integerInput, Double doubleInput) {
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

	@Override
	public Answer handleMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput) {
		throw new UnsupportedOperationException("The class " + this.getClass().getCanonicalName() + " don't support this operation.");
	}
}
