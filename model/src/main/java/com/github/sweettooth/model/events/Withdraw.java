package com.github.sweettooth.model.events;

import com.github.sweettooth.model.apiView.Settings;
import com.github.sweettooth.model.characters.MoneyDealer;
import com.github.sweettooth.model.characters.Player;
import com.github.sweettooth.model.games.GameData;

public final class Withdraw extends Event {
	Withdraw(GameData gameData){
		super(gameData);
	}
	
	@Override
	public String handle(String stringInput, Integer integerInput, Double doubleInput) {
		double amount = doubleInput > 0 ? doubleInput : 0;
		amount = Math.round(amount * 100) / 100.00;
		Player player = (Player)gameData.getPlayer();
		MoneyDealer bank = (MoneyDealer)gameData.getBank();
		if(bank.getClientsBalance(player) - amount < Settings.BANK_MIN_BALANCE)
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
