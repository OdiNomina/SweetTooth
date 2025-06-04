package com.github.sweettooth.model.events;

import com.github.sweettooth.model.games.GameData;

public final class Exit extends Event {
	Exit(GameData gameData){
		super(gameData);
	}
	
	@Override
	public String handle(String stringInput, Integer integerInput, Double doubleInput) {
		return "";
	}

	@Override
	public Answer handleMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput) {
		throw new UnsupportedOperationException("The class " + this.getClass().getCanonicalName() + " don't support this operation.");
	}
}
