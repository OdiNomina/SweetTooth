package com.github.sweettooth.model.gameEvents;

import com.github.sweettooth.model.gameRounds.GameRound;
import com.github.sweettooth.model.gameSession.GameSession;

public final class Deposit extends Event {
	Deposit(GameSession sessionData, GameRound gameData){
		super(sessionData, gameData);
	}
	
	@Override
	public String[] process(String stringInput, Integer integerInput, Double doubleInput) {
		String[] returnArray = new String[1];
		double amount = doubleInput > 0 ? doubleInput : 0;
		amount = Math.round(amount * 100) / 100.00;
		
		if(player.getCash() < amount) {
			returnArray[0] =  "Ups! So viel hab ich gar nicht dabei...";
			return returnArray;
		}

		player.reduceCash(amount);
		bank.increaseClientsBalance(player, amount);
		returnArray[0] = "Betrag einbezahlt.";
		return returnArray;
	}
}
