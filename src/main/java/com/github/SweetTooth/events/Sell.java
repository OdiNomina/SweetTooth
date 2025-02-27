package com.github.SweetTooth.events;

import com.github.SweetTooth.snacks.Snackable;

final class Sell extends Event implements IEvent {
	@Override
	public String handleEvent() {
		if(!isAtHometown())
			return notAtHometown;
		return sell();
	}
	
	private String sell() {
		if(integerInput < 1)
			return "Nix verkauft";
		Snackable playersCandy = Snackable.valueOf(player.getCandies(), stringInput);
		if(playersCandy == null || playersCandy.getQuantity() < integerInput)
			return "Du kannst nur verkaufen, was du hast.";
		if(playersCandy.getQuantity() > integerInput)
			playersCandy.reduceQuantity(integerInput);
		else
			player.getCandies().remove(playersCandy);
		player.addCash(playersCandy.getStaticPrice() * integerInput);			
		return "Verkauft";
	}
}
