package com.github.sweettooth.model.events;

import com.github.sweettooth.model.gameRounds.GameRound;
import com.github.sweettooth.model.gameSession.SessionData;

public final class Hide extends Event {
	Hide(SessionData sessionData, GameRound gameData){
		super(sessionData, gameData);
	}
	
	@Override
	public String process(String stringInput, Integer integerInput, Double doubleInput) {
		if(!isAtHometown())
			return notAtHometown;

		player.addAllSnacks(player.getSnacksFromPockets(), player.getSnacksFromStash());
		player.getSnacksFromPockets().clear();
		return "Alles versteckt!";
	}

	@Override
	public Answer processMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput) {
		throw new UnsupportedOperationException("The class " + this.getClass().getCanonicalName() + " don't support this operation.");
	}
}
