package com.github.SweetTooth.events;

import com.github.SweetTooth.characters.IPlayer;

final class Hide extends Event implements IEvent {
	@Override
	public String handleEvent() {
		if(!isAtHometown())
			return notAtHometown;

		IPlayer player = game.getPlayer();
		player.addAllSnacks(player.getCandies(), player.getCandyStash());
		player.getCandies().clear();
		return "Alles versteckt!";
	}
}
