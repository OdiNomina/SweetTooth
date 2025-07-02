package com.github.sweettooth.model.events;

import com.github.sweettooth.model.api.viewAPI.Snackable;
import com.github.sweettooth.model.commons.Tools;
import com.github.sweettooth.model.games.GameData;

public final class Buy extends Event {
	Buy(GameData gameData){
		super(gameData);
	}
	
	@Override
	public String process(String stringInput, Integer integerInput, Double doubleInput) {
		stringInput = splitStringInput(stringInput);
		if(integerInput < 1)
			return "Nix gekauft";
		if(Tools.isTooMuchToCarry(player, integerInput))
			return "Soviel kannst du gar nicht tragen.";
		Snackable kindOfCandy = modelSettings.getSnackFactory().valueOf(stringInput);
		if(player.getCash() < kindOfCandy.getStaticPrice() * integerInput)
			return "Soviel Geld hast du nicht dabei, musst du erst besorgen...";
		player.addSnack(kindOfCandy, player.getCandies(), integerInput);
		player.reduceCash(kindOfCandy.getStaticPrice() * integerInput);
		return "Gekauft!";
	}

	@Override
	public Answer processMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput) {
		throw new UnsupportedOperationException("The class " + this.getClass().getCanonicalName() + " don't support this operation.");
	}
}