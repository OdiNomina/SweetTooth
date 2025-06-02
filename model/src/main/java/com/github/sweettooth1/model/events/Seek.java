package com.github.sweettooth.model.events;

import com.github.sweettooth.model.characters.Player;
import com.github.sweettooth.model.snacks.Candy;
import com.github.sweettooth.model.snacks.CandyFactory;

final class Seek extends Event {
	@Override
	public String handle(String stringInput, Integer integerInput, Double doubleInput) {
		if(integerInput == 0)
			return "";
		stringInput = splitStringInput(stringInput);
		if(!isAtHometown())
			return notAtHometown;

		if(isTooMuchToCarry(integerInput))
			return "Soviel kannst du nicht tragen.";
		Candy snack = new CandyFactory().valueOf(stringInput);
		Player player = game.getPlayer();
		player.addSnack(snack, player.getCandies(), integerInput);
		player.removeSnack(snack, player.getCandyStash(), integerInput);
		return "Eingepackt";
	}

	@Override
	public Answer handleMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput) {
		throw new UnsupportedOperationException("The class " + this.getClass().getCanonicalName() + " don't support this operation.");
	}
}
