package com.github.sweettooth.model.gameEvents;

import com.github.sweettooth.model.gameRounds.GameRound;
import com.github.sweettooth.model.gameSession.GameSession;
import com.github.sweettooth.model.settings.InternSettings;

public final class Withdraw extends Event {
	Withdraw(GameSession sessionData, GameRound gameData){
		super(sessionData, gameData);
	}
	
	@Override
	public String[] process(String stringInput, Integer integerInput, Double doubleInput) {
		String[] returnArray = new String[1];
		double amount = doubleInput > 0 ? doubleInput : 0;
		amount = Math.round(amount * 100) / 100.00;
		
		if(bank.getClientsBalance(player) - amount < InternSettings.BANK_MIN_BALANCE) {
			returnArray[0] = "Die Bank zahlt dir diese Summe nicht aus.";
			return returnArray;
		}
		
		player.addCash(amount);
		bank.reduceClientsBalance(player, amount);
		returnArray[0] = "Betrag ausbezahlt.";
		return returnArray;
	}
}
