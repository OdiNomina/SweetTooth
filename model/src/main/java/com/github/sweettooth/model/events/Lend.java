package com.github.sweettooth.model.events;

import com.github.sweettooth.model.characters.MoneyDealer;
import com.github.sweettooth.model.characters.Player;
import com.github.sweettooth.model.games.GameData;

public final class Lend extends Event {
	Lend(GameData gameData){
		super(gameData);
	}
	
	@Override
	public String handle(String stringInput, Integer integerInput, Double doubleInput) {
		if(!isAtHometown()) 
			return notAtHometown;

		double amount = doubleInput > 0 ? doubleInput : 0;
		
		MoneyDealer loanShark =  (MoneyDealer)gameData.getLoanShark();
		Player player = (Player)gameData.getPlayer();
		
		if(loanShark.getClientsBalance(player) < 0)
			return "Kannst du vergessen Alder.";
		
		player.addCash(amount);
		loanShark.reduceClientsBalance(player, amount);
		return "Hier, lass dir ruhig Zeit... aber nicht ZU lange!";
	}

	@Override
	public Answer handleMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput) {
		throw new UnsupportedOperationException("The class " + this.getClass().getCanonicalName() + " don't support this operation.");
	}
}
