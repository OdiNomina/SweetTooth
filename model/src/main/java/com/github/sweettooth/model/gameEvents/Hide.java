package com.github.sweettooth.model.gameEvents;

import com.github.sweettooth.model.gameRounds.GameRound;
import com.github.sweettooth.model.gameSession.GameSession;

public final class Hide extends Event {
	Hide(GameSession sessionData, GameRound gameData){
		super(sessionData, gameData);
	}
	
	@Override
	public String[] process(String stringInput, Integer integerInput, Double doubleInput) {
		String[] returnArray = new String[1];
		
		if(!isAtHometown()) {
			returnArray[0] = notAtHometown;
			return returnArray;
		}
		
		player.addAllSnacks(player.getSnacksFromPockets(), player.getSnacksFromStash());
		player.getSnacksFromPockets().clear();
		returnArray[0] = "Alles versteckt!";
		return returnArray;
	}
}
