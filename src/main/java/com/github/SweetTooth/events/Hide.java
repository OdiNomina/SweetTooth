package com.github.SweetTooth.events;

final class Hide extends Event implements IEvent {
	@Override
	public String handleEvent() {
		if(!isAtHometown())
			return notAtHometown;
		return hide();
	}
	
	public String hide() {
		player.addAllSnacks(player.getCandies(), player.getCandyStash());
		player.getCandies().clear();
		return "Alles versteckt!";
	}
}
