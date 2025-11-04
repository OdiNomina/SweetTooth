package com.github.sweettooth.model.gameEvents;

import java.util.ArrayList;

import com.github.sweettooth.model.commons.Tools;
import com.github.sweettooth.model.gameRounds.GameRound;
import com.github.sweettooth.model.gameSession.GameSession;
import com.github.sweettooth.model.snacks.Snack;

public final class Seek extends Event {
	Seek(GameSession sessionData, GameRound gameData){
		super(sessionData, gameData);
	}
	
	@Override
	public String[] process(String stringInput, Integer integerInput, Double doubleInput) {
		String[] returnArray = new String[1];
		try {
			if(integerInput == 0) {
				returnArray[0] = "";
				return returnArray;
			}
				
			if(!isAtHometown()) {
				returnArray[0] = notAtHometown;
				return returnArray;
			}

			ArrayList<? extends Snack> snackStash = player.getSnacksFromStash();
			if(snackStash.isEmpty()) {
				returnArray[0] = "Hä...?!";
				return returnArray;
			}

			final String snackInput = clearStringInput(stringInput);
			Snack selectedSnack = snackStash.stream()
					.filter(e -> e.getName().equalsIgnoreCase(snackInput))
					.findFirst()
					.orElseThrow(() -> new IllegalArgumentException("Snack nicht gefunden: " + stringInput));
			
			if(integerInput > selectedSnack.getQuantity()) {
				returnArray[0] = "Denkste, so viel hast du gar nicht versteckt.";
				return returnArray;
			}
			
			if(Tools.isTooMuchToCarry(player, integerInput)) {
				returnArray[0] = "Soviel kannst du nicht tragen.";
				return returnArray;
			}
			
			player.addSnack(selectedSnack, player.getSnacksFromPockets(), integerInput);
			player.removeSnack(selectedSnack, player.getSnacksFromStash(), integerInput);
			returnArray[0] = "Eingepackt";
			return returnArray;
		}
		catch(IllegalArgumentException ex) {
			this.warn("Error when Seek", ex);
			returnArray[0] = "";
			return returnArray;
		}
	}
}
