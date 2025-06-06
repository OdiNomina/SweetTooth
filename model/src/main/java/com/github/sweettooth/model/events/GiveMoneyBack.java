package com.github.sweettooth.model.events;

import com.github.sweettooth.model.api.GameModelInterface;
import com.github.sweettooth.model.characters.MoneyDealer;
import com.github.sweettooth.model.characters.Player;

public final class GiveMoneyBack extends Event {
	GiveMoneyBack(GameModelInterface gameData){
		super(gameData);
	}
	
	@Override
	public String process(String stringInput, Integer integerInput, Double doubleInput) {
		if(!isAtHometown()) 
			return notAtHometown;
		
		double amount = doubleInput > 0 ? doubleInput : 0;
		amount = Math.round(amount * 100) / 100.00;
		
		MoneyDealer loanShark =  (MoneyDealer)gameData.getLoanShark();
		Player player = (Player)gameData.getPlayer();
		
		if(amount + loanShark.getClientsBalance(player) > 0)
			return "Digga was gibst du mir soviel Geld? Hab ich dir gar nicht gegeben.";
		
		player.reduceCash(amount);
		loanShark.increaseClientsBalance(player, amount);
		if(loanShark.getClientsBalance(player) < 0)
			return "Da fehlt aber noch was!";
		
		return "Geht klar Alder, bis zum nächsten Mal.";
	}

	@Override
	public Answer processMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput) {
		throw new UnsupportedOperationException("The class " + this.getClass().getCanonicalName() + " don't support this operation.");
	}
}
