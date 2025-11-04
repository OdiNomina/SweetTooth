package com.github.sweettooth.model.gameEvents;

import com.github.sweettooth.model.commons.Tools;
import com.github.sweettooth.model.gameRounds.GameRound;
import com.github.sweettooth.model.gameSession.GameSession;
import com.github.sweettooth.model.snacks.Snack;
import com.github.sweettooth.model.snacks.SnackFactory;

public final class Buy extends Event {
	Buy(GameSession sessionData, GameRound gameData){
		super(sessionData, gameData);
	}
	
	@Override
	public String[] process(String stringInput, Integer integerInput, Double doubleInput) {
		String[] returnArray = new String[1];
		stringInput = clearStringInput(stringInput);
		
		if(integerInput < 1) {
			returnArray[0] = "Nix gekauft";
			return returnArray;
		}

		if(Tools.isTooMuchToCarry(player, integerInput)) {
			returnArray[0] = "Soviel kannst du gar nicht tragen.";
			return returnArray;
		}

		SnackFactory snackFactory = (SnackFactory)gameSettings.getSnackFactory();
		Snack kindOfCandy = snackFactory.valueOf(stringInput);
		
		if(player.getCash() < kindOfCandy.getPrice() * integerInput) {
			returnArray[0] = "Soviel Geld hast du nicht dabei, musst du erst besorgen...";
			return returnArray;
		}

		player.addSnack(kindOfCandy, player.getSnacksFromPockets(), integerInput);
		player.reduceCash(kindOfCandy.getPrice() * integerInput);
		returnArray[0] = "Gekauft!";
		return returnArray;
	}
}