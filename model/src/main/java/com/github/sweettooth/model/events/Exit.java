package com.github.sweettooth.model.events;

import com.github.sweettooth.model.api.GameModelInterface;

public final class Exit extends Event {
	Exit(GameModelInterface gameData){
		super(gameData);
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
