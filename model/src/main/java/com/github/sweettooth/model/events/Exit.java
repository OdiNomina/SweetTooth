package com.github.sweettooth.model.events;

import com.github.sweettooth.model.games.GameData;
import com.github.sweettooth.model.session.SessionData;

public final class Exit extends Event {
	Exit(SessionData sessionData, GameData gameData){
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
