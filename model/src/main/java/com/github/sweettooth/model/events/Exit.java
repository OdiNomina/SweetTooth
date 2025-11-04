package com.github.sweettooth.model.events;

import com.github.sweettooth.model.gameRounds.GameRound;
import com.github.sweettooth.model.gameSession.SessionData;

public final class Exit extends Event {
	Exit(SessionData sessionData, GameRound gameData){
		super(sessionData, gameData);
	}
	
	@Override
	public String process(String stringInput, Integer integerInput, Double doubleInput) {
		return "";
	}

	@Override
	public Answer processMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput) {
		throw new UnsupportedOperationException("The class " + this.getClass().getCanonicalName() + " don't support this operation.");
	}
}
