package com.github.SweetTooth.model.events;

import com.github.SweetTooth.model.characters.Player;

final class Deposit extends Event {
	@Override
	public String handle(String stringInput, Integer integerInput, Double doubleInput) {
		double amount = doubleInput != 0 ? doubleInput : integerInput;
		amount = Math.round(amount * 100) / 100.00;
		Player player = game.getPlayer();
		if(player.getCash() < amount)
			return "Ups! So viel hab ich gar nicht dabei...";
		player.reduceCash(amount);
		game.getBank().increaseClientsBalance(player, amount);
		return "Betrag einbezahlt.";
	}

	@Override
	public Answer handleMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput) {
		throw new UnsupportedOperationException("The class " + this.getClass().getCanonicalName() + " don't support this operation.");
	}
}
