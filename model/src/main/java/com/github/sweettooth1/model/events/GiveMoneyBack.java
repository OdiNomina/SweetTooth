package com.github.sweettooth.model.events;

import com.github.sweettooth.model.characters.MoneyDealer;
import com.github.sweettooth.model.characters.Player;

final class GiveMoneyBack extends Event {
	@Override
	public String handle(String stringInput, Integer integerInput, Double doubleInput) {
		if(!isAtHometown()) 
			return notAtHometown;
		
		double amount = doubleInput > 0 ? doubleInput : 0;
		amount = Math.round(amount * 100) / 100.00;
		MoneyDealer loanShark =  game.getLoanShark();
		Player player = game.getPlayer();
		if(amount + loanShark.getBalance(player) > 0)
			return "Digga was gibst du mir soviel Geld? Hab ich dir gar nicht gegeben.";
		player.reduceCash(amount);
		loanShark.increaseClientsBalance(player, amount);
		if(loanShark.getBalance(player) < 0)
			return "Da fehlt aber noch was!";
		return "Geht klar Alder, bis zum nächsten Mal.";
	}

	@Override
	public Answer handleMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput) {
		throw new UnsupportedOperationException("The class " + this.getClass().getCanonicalName() + " don't support this operation.");
	}
}
