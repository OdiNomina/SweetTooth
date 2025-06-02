package com.github.sweettooth.model.events;

import com.github.sweettooth.model.characters.Player;

final class Hide extends Event {
	@Override
	public String handle(String stringInput, Integer integerInput, Double doubleInput) {
		if(!isAtHometown())
			return notAtHometown;

		Player player = game.getPlayer();
		player.addAllSnacks(player.getCandies(), player.getCandyStash());
		player.getCandies().clear();
		return "Alles versteckt!";
	}

	@Override
	public Answer handleMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput) {
		throw new UnsupportedOperationException("The class " + this.getClass().getCanonicalName() + " don't support this operation.");
	}
}
