package com.github.sweettooth.model.events;

import com.github.sweettooth.model.games.GameData;

public final class Hide extends Event {
	Hide(GameData gameData){
		super(gameData);
	}
	
	@Override
	public String process(String stringInput, Integer integerInput, Double doubleInput) {
		if(!isAtHometown())
			return notAtHometown;

		player.addAllSnacks(player.getCandies(), player.getCandyStash());
		player.getCandies().clear();
		return "Alles versteckt!";
	}

	@Override
	public Answer processMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput) {
		throw new UnsupportedOperationException("The class " + this.getClass().getCanonicalName() + " don't support this operation.");
	}
}
