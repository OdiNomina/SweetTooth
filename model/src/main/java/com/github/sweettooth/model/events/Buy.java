package com.github.sweettooth.model.events;

import com.github.sweettooth.model.api.GameModelInterface;
import com.github.sweettooth.model.characters.Player;
import com.github.sweettooth.model.snacks.Candy;
import com.github.sweettooth.model.snacks.CandyFactory;

public final class Buy extends Event {
	Buy(GameModelInterface gameData){
		super(gameData);
	}
	
	@Override
	public String process(String stringInput, Integer integerInput, Double doubleInput) {
		stringInput = splitStringInput(stringInput);
		if(integerInput < 1)
			return "Nix gekauft";
		if(isTooMuchToCarry(integerInput))
			return "Soviel kannst du gar nicht tragen.";
		Candy kindOfCandy = new CandyFactory().valueOf(stringInput);
		Player player = (Player)gameData.getPlayer();
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