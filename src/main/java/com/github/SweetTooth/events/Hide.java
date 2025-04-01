package com.github.SweetTooth.events;

import com.github.SweetTooth.characters.Player;

final class Hide extends Event implements IEvent {
	@Override
	public String handleEvent() {
		if(!isAtHometown())
			return notAtHometown;

		Player player = game.getPlayer();
		player.addAllSnacks(player.getCandies(), player.getCandyStash());
		player.getCandies().clear();
		return "Alles versteckt!";
	}
}
