package com.github.SweetTooth.events;

import com.github.SweetTooth.characters.Player;
import com.github.SweetTooth.snacks.Candy;
import com.github.SweetTooth.snacks.CandyFactory;

final class Buy extends Event {
	@Override
	public String handle(String stringInput, Integer integerInput, Double doubleInput) {
		stringInput = splitStringInput(stringInput);
		if(integerInput < 1)
			return "Nix gekauft";
		if(isTooMuchToCarry(integerInput))
			return "Soviel kannst du gar nicht tragen.";
		Candy kindOfCandy = new CandyFactory().valueOf(stringInput);
		Player player = game.getPlayer();
		if(player.getCash() < kindOfCandy.getStaticPrice() * integerInput)
			return "Soviel Geld hast du nicht dabei, musst du erst besorgen...";
		player.addSnack(kindOfCandy, player.getCandies(), integerInput);
		player.reduceCash(kindOfCandy.getStaticPrice() * integerInput);
		return "Gekauft!";
	}

	@Override
	public Answer handleMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput) {
		throw new UnsupportedOperationException("The class " + this.getClass().getCanonicalName() + " don't support this operation.");
	}
}