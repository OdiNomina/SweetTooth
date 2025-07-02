package com.github.sweettooth.model.events;

import com.github.sweettooth.model.api.Snackable;
import com.github.sweettooth.model.commons.Tools;
import com.github.sweettooth.model.games.GameData;

public final class Seek extends Event {
	Seek(GameData gameData){
		super(gameData);
	}
	
	@Override
	public String process(String stringInput, Integer integerInput, Double doubleInput) {
		if(integerInput == 0)
			return "";
		stringInput = splitStringInput(stringInput);
		if(!isAtHometown())
			return notAtHometown;

		if(Tools.isTooMuchToCarry(player, integerInput))
			return "Soviel kannst du nicht tragen.";
		Snackable snack = modelSettings.getSnackFactory().valueOf(stringInput);
		player.addSnack(snack, player.getCandies(), integerInput);
		player.removeSnack(snack, player.getCandyStash(), integerInput);
		return "Eingepackt";
	}

	@Override
	public Answer processMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput) {
		throw new UnsupportedOperationException("The class " + this.getClass().getCanonicalName() + " don't support this operation.");
	}
}
