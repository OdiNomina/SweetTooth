package com.github.sweettooth.model.events;

import java.util.ArrayList;

import com.github.sweettooth.model.commons.Tools;
import com.github.sweettooth.model.games.GameData;
import com.github.sweettooth.model.session.SessionData;
import com.github.sweettooth.model.snacks.Snack;

public final class Seek extends Event {
	Seek(SessionData sessionData, GameData gameData){
		super(sessionData, gameData);
	}
	
	@Override
	public String process(String stringInput, Integer integerInput, Double doubleInput) {
		try {
			if(integerInput == 0) return "";
			
			if(!isAtHometown()) return notAtHometown;
			
			ArrayList<? extends Snack> snackStash = player.getSnacksInStash();
			if(snackStash.isEmpty()) return "Hä...?!";
			
			final String snackInput = clearStringInput(stringInput);
			Snack selectedSnack = snackStash.stream()
					.filter(e -> e.getName().equalsIgnoreCase(snackInput))
					.findFirst()
					.orElseThrow(() -> new IllegalArgumentException("Snack nicht gefunden: " + stringInput));
			
			if(integerInput > selectedSnack.getQuantity()) return "Denkste, so viel hast du gar nicht versteckt.";
			
			if(Tools.isTooMuchToCarry(player, integerInput)) return "Soviel kannst du nicht tragen.";
			
			player.addSnack(selectedSnack, player.getSnacksInPockets(), integerInput);
			player.removeSnack(selectedSnack, player.getSnacksInStash(), integerInput);
			return "Eingepackt";
		} catch(IllegalArgumentException ex) {
			this.warn("Error when Seek", ex);
			return "";
		}
	}

	@Override
	public Answer processMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput) {
		throw new UnsupportedOperationException("The class " + this.getClass().getCanonicalName() + " don't support this operation.");
	}
}
