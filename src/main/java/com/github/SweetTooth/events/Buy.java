package com.github.SweetTooth.events;

import com.github.SweetTooth.snacks.Candy;
import com.github.SweetTooth.snacks.CandyFactory;

final class Buy extends Event implements IEvent {
	@Override
	public String handleEvent() {
		if(!isAtHometown()) 
			return notAtHometown;
		return buy();
	}
	
	private String buy() {
		if(integerInput < 1)
			return "Nix gekauft";
		if(!hasSpaceInPockets(integerInput))
			return "Soviel kannst du gar nicht tragen.";
		Candy kindOfCandy = new CandyFactory().valueOf(stringInput);
		if(player.getCash() < kindOfCandy.getStaticPrice() * integerInput)
			return "Soviel Geld hast du nicht dabei, musst du erst besorgen...";
		player.addSnack(kindOfCandy, player.getCandies(), integerInput);
		player.reduceCash(kindOfCandy.getStaticPrice() * integerInput);
		return "Gekauft!";
	}
}