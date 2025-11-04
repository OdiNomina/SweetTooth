package com.github.sweettooth.model.gameEvents;

import com.github.sweettooth.model.gameRounds.GameRound;
import com.github.sweettooth.model.gameSession.GameSession;

public final class Lend extends Event {
	Lend(GameSession sessionData, GameRound gameData){
		super(sessionData, gameData);
	}
	
	@Override
	public String[] process(String stringInput, Integer integerInput, Double doubleInput) {
		String[] returnArray = new String[1];
		
		if(!isAtHometown()) {
			returnArray[0] = notAtHometown;
			return returnArray;
		}
		
		if(loanShark.getClientsBalance(player) < 0) {
			returnArray[0] = "Kannst du vergessen Alder.";
			return returnArray;
		}
		
		double amount = doubleInput > 0 ? doubleInput : 0;
		player.addCash(amount);
		loanShark.reduceClientsBalance(player, amount);
		returnArray[0] = "Hier, lass dir ruhig Zeit... aber nicht ZU lange!";
		return returnArray;
	}
}
