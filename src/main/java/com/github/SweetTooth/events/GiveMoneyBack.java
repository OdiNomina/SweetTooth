package com.github.SweetTooth.events;

import com.github.SweetTooth.characters.MoneyDealer;
import com.github.SweetTooth.characters.IPlayer;

final class GiveMoneyBack extends Event implements IEvent {
	@Override
	public String handleEvent() {
		if(!isAtHometown()) 
			return notAtHometown;
		
		double amount = doubleInput != 0 ? doubleInput : integerInput;
		amount = Math.round(amount * 100) / 100.00;
		MoneyDealer loanShark =  game.getLoanShark();
		IPlayer player = game.getPlayer();
		if(amount + loanShark.getBalance(player) > 0)
			return "Digga was gibst du mir soviel Geld? Hab ich dir gar nicht gegeben.";
		player.reduceCash(amount);
		loanShark.increaseClientsBalance(player, amount);
		if(loanShark.getBalance(player) < 0)
			return "Da fehlt aber noch was!";
		return "Geht klar Alder, bis zum nächsten Mal.";
	}
}
