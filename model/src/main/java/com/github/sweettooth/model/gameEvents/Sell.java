package com.github.sweettooth.model.gameEvents;

import java.util.NoSuchElementException;

import com.github.sweettooth.model.gameRounds.GameRound;
import com.github.sweettooth.model.gameSession.GameSession;
import com.github.sweettooth.model.snacks.Snack;

public final class Sell extends Event {
	Sell(GameSession sessionData, GameRound gameData){
		super(sessionData, gameData);
	}
	
	@Override
	public String[] process(String stringInput, Integer integerInput, Double doubleInput) {
		String[] returnArray = new String[1];
		try {
			stringInput = clearStringInput(stringInput);
			if(integerInput < 1) {
				returnArray[0] = "Nix verkauft";
				return returnArray;
			}
			
			Snack snack = Snack.findSnack(player.getSnacksFromPockets(), stringInput);
			if(snack.getQuantity() < integerInput) {
				returnArray[0] = "Kannst du nicht zählen?";
				return returnArray;
			}
			
			if(snack.getQuantity() > integerInput)
				snack.reduceQuantity(integerInput);
			else
				player.getSnacksFromPockets().remove(snack);
			
			player.addCash(snack.getPrice() * integerInput);			
			returnArray[0] = "Verkauft";
			return returnArray;
		}
		catch(NoSuchElementException ex) {
			returnArray[0] = "Lass sehen... das hast du doch gar nicht!";
			return returnArray;
		}
	}
}
