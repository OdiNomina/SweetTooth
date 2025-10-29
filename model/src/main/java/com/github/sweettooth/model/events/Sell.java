package com.github.sweettooth.model.events;

import java.util.NoSuchElementException;

import com.github.sweettooth.model.games.GameData;
import com.github.sweettooth.model.session.SessionData;
import com.github.sweettooth.model.snacks.Snack;

public final class Sell extends Event {
	Sell(SessionData sessionData, GameData gameData){
		super(sessionData, gameData);
	}
	
	@Override
	public String process(String stringInput, Integer integerInput, Double doubleInput) {
		try {
			stringInput = clearStringInput(stringInput);
			if(integerInput < 1)
				return "Nix verkauft";
			
			Snack playersCandy = Snack.findSnack(player.getSnacksInPockets(), stringInput);
			if(playersCandy.getQuantity() < integerInput)
				return "Kannst du nicht zählen?";
			if(playersCandy.getQuantity() > integerInput)
				playersCandy.reduceQuantity(integerInput);
			else
				player.getSnacksInPockets().remove(playersCandy);
			player.addCash(playersCandy.getPrice() * integerInput);			
			return "Verkauft";
		} catch(NoSuchElementException ex) {
			return "Lass sehen... das hast du doch gar nicht!";
		}
	}

	@Override
	public Answer processMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput) {
		throw new UnsupportedOperationException("The class " + this.getClass().getCanonicalName() + " don't support this operation.");
	}
}
