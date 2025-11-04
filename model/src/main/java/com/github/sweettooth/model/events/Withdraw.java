package com.github.sweettooth.model.events;

import com.github.sweettooth.model.commons.InternSettings;
import com.github.sweettooth.model.gameRounds.GameRound;
import com.github.sweettooth.model.gameSession.SessionData;

public final class Withdraw extends Event {
	Withdraw(SessionData sessionData, GameRound gameData){
		super(sessionData, gameData);
	}
	
	@Override
	public String process(String stringInput, Integer integerInput, Double doubleInput) {
		double amount = doubleInput > 0 ? doubleInput : 0;
		amount = Math.round(amount * 100) / 100.00;
		if(bank.getClientsBalance(player) - amount < InternSettings.BANK_MIN_BALANCE)
			return "Die Bank zahlt dir diese Summe nicht aus.";
		player.addCash(amount);
		bank.reduceClientsBalance(player, amount);
		return "Betrag ausbezahlt.";
	}

	@Override
	public Answer processMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput) {
		throw new UnsupportedOperationException("The class " + this.getClass().getCanonicalName() + " don't support this operation.");
	}
}
