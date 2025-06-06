package com.github.sweettooth.model.events;

import com.github.sweettooth.model.api.GameModelInterface;
import com.github.sweettooth.model.characters.Player;
import com.github.sweettooth.model.snacks.Candy;
import com.github.sweettooth.model.snacks.CandyFactory;

public final class Seek extends Event {
	Seek(GameModelInterface gameData){
		super(gameData);
	}
	
	@Override
	public String process(String stringInput, Integer integerInput, Double doubleInput) {
		if(integerInput == 0)
			return "";
		stringInput = splitStringInput(stringInput);
		if(!isAtHometown())
			return notAtHometown;

		if(isTooMuchToCarry(integerInput))
			return "Soviel kannst du nicht tragen.";
		Candy snack = new CandyFactory().valueOf(stringInput);
		Player player = (Player)gameData.getPlayer();
		player.addSnack(snack, player.getCandies(), integerInput);
		player.removeSnack(snack, player.getCandyStash(), integerInput);
		return "Eingepackt";
	}

	@Override
	public Answer processMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput) {
		throw new UnsupportedOperationException("The class " + this.getClass().getCanonicalName() + " don't support this operation.");
	}
}
