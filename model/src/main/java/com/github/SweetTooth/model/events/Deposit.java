package com.github.sweettooth.model.events;

import com.github.sweettooth.model.characters.Player;

final class Deposit extends Event {
	@Override
	public String handle(String stringInput, Integer integerInput, Double doubleInput) {
		double amount = doubleInput > 0 ? doubleInput : 0;
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
