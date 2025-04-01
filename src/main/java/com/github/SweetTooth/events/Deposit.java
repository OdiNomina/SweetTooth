package com.github.SweetTooth.events;

import com.github.SweetTooth.characters.Player;

final class Deposit extends Event implements IEvent{
	@Override
	public String handleEvent() {
		double amount = doubleInput != 0 ? doubleInput : integerInput;
		amount = Math.round(amount * 100) / 100.00;
		Player player = game.getPlayer();
		if(player.getCash() < amount)
			return "Ups! So viel hab ich gar nicht dabei...";
		player.reduceCash(amount);
		game.getBank().increaseClientsBalance(player, amount);
		return "Betrag einbezahlt.";
	}
}
