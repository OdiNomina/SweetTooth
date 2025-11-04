package com.github.sweettooth.model.gameEvents;

import com.github.sweettooth.model.gameRounds.GameRound;
import com.github.sweettooth.model.gameSession.GameSession;

public final class GiveMoneyBack extends Event {
	GiveMoneyBack(GameSession sessionData, GameRound gameData){
		super(sessionData, gameData);
	}
	
	@Override
	public String[] process(String stringInput, Integer integerInput, Double doubleInput) {
		String[] returnArray = new String[1];
		
		if(!isAtHometown()) {
			returnArray[0] = notAtHometown;
			return returnArray;
		}
		
		double amount = doubleInput > 0 ? doubleInput : 0;
		amount = Math.round(amount * 100) / 100.00;
		
		if(amount + loanShark.getClientsBalance(player) > 0) {
			returnArray[0] = "Digga was gibst du mir soviel Geld? Hab ich dir gar nicht gegeben.";
			return returnArray;
		}

		player.reduceCash(amount);
		loanShark.increaseClientsBalance(player, amount);
		if(loanShark.getClientsBalance(player) < 0) {
			returnArray[0] = "Da fehlt aber noch was!";
			return returnArray;
		}
		
		returnArray[0] = "Geht klar Alder, bis zum nächsten Mal.";
		return returnArray;
	}
}
