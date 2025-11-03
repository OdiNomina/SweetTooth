package com.github.sweettooth.model.events;

import com.github.sweettooth.model.gameSession.SessionData;
import com.github.sweettooth.model.games.GameData;

public final class Hide extends Event {
	Hide(SessionData sessionData, GameData gameData){
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
