package com.github.sweettooth.model.events;

import com.github.sweettooth.model.apiView.Snackable;
import com.github.sweettooth.model.characters.Player;
import com.github.sweettooth.model.games.GameData;

public final class Sell extends Event {
	Sell(GameData gameData){
		super(gameData);
	}
	
	@Override
	public String handle(String stringInput, Integer integerInput, Double doubleInput) {
		stringInput = splitStringInput(stringInput);
		if(integerInput < 1)
			return "Nix verkauft";
		
		Player player = (Player)gameData.getPlayer();
		Snackable playersCandy = Snackable.findSnack(player.getCandies(), stringInput);
		if(playersCandy == null || playersCandy.getQuantity() < integerInput)
			return "Du kannst nur verkaufen, was du hast.";
		if(playersCandy.getQuantity() > integerInput)
			playersCandy.reduceQuantity(integerInput);
		else
			player.getCandies().remove(playersCandy);
		player.addCash(playersCandy.getStaticPrice() * integerInput);			
		return "Verkauft";
	}

	@Override
	public Answer handleMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput) {
		throw new UnsupportedOperationException("The class " + this.getClass().getCanonicalName() + " don't support this operation.");
	}
}
