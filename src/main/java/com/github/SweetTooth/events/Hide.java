package com.github.SweetTooth.events;

final class Hide extends Event implements IEvent {
	@Override
	public String handleEvent() {
		if(!isAtHometown())
			return notAtHometown;
		return hide();
	}
	
	public String hide() {
		player.addAllCandies(player.getCandies(), player.getStash());
		player.getCandies().clear();
		return "Alles versteckt!";
	}
}
