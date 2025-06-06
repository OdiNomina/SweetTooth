package com.github.sweettooth.model.events;

import com.github.sweettooth.model.api.GameModelInterface;
import com.github.sweettooth.model.characters.Player;

public final class Hide extends Event {
	Hide(GameModelInterface gameData){
		super(gameData);
	}
	
	@Override
	public String process(String stringInput, Integer integerInput, Double doubleInput) {
		if(!isAtHometown())
			return notAtHometown;

		Player player = (Player)gameData.getPlayer();
		player.addAllSnacks(player.getCandies(), player.getCandyStash());
		player.getCandies().clear();
		return "Alles versteckt!";
	}

	@Override
	public Answer processMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput) {
		throw new UnsupportedOperationException("The class " + this.getClass().getCanonicalName() + " don't support this operation.");
	}
}
