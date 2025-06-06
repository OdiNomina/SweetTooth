package com.github.sweettooth.model.events;

import com.github.sweettooth.model.api.GameModelInterface;
import com.github.sweettooth.model.characters.MoneyDealer;
import com.github.sweettooth.model.characters.Player;

public final class Deposit extends Event {
	Deposit(GameModelInterface gameData){
		super(gameData);
	}
	
	@Override
	public String process(String stringInput, Integer integerInput, Double doubleInput) {
		double amount = doubleInput > 0 ? doubleInput : 0;
		amount = Math.round(amount * 100) / 100.00;
		
		Player player = (Player)gameData.getPlayer();
		if(player.getCash() < amount)
			return "Ups! So viel hab ich gar nicht dabei...";
		player.reduceCash(amount);
		
		MoneyDealer bank = (MoneyDealer)gameData.getBank();
		bank.increaseClientsBalance(player, amount);
		return "Betrag einbezahlt.";
	}

	@Override
	public Answer processMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput) {
		throw new UnsupportedOperationException("The class " + this.getClass().getCanonicalName() + " don't support this operation.");
	}
}
