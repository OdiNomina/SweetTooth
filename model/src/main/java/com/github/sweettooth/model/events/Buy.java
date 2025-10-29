package com.github.sweettooth.model.events;

import com.github.sweettooth.model.commons.Tools;
import com.github.sweettooth.model.games.GameData;
import com.github.sweettooth.model.session.SessionData;
import com.github.sweettooth.model.snacks.Snack;
import com.github.sweettooth.model.snacks.SnackFactory;

public final class Buy extends Event {
	Buy(SessionData sessionData, GameData gameData){
		super(sessionData, gameData);
	}
	
	@Override
	public String process(String stringInput, Integer integerInput, Double doubleInput) {
		stringInput = clearStringInput(stringInput);
		if(integerInput < 1)
			return "Nix gekauft";
		if(Tools.isTooMuchToCarry(player, integerInput))
			return "Soviel kannst du gar nicht tragen.";
		SnackFactory snackFactory = (SnackFactory)gameSettings.getSnackFactory();
		Snack kindOfCandy = snackFactory.valueOf(stringInput);
		if(player.getCash() < kindOfCandy.getPrice() * integerInput)
			return "Soviel Geld hast du nicht dabei, musst du erst besorgen...";
		player.addSnack(kindOfCandy, player.getSnacksInPockets(), integerInput);
		player.reduceCash(kindOfCandy.getPrice() * integerInput);
		return "Gekauft!";
	}

	@Override
	public Answer processMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput) {
		throw new UnsupportedOperationException("The class " + this.getClass().getCanonicalName() + " don't support this operation.");
	}
}