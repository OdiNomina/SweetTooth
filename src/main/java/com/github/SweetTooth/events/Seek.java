package com.github.SweetTooth.events;

final class Seek extends Event implements IEvent {
	@Override
	public String handleEvent() {
		if(!isAtHometown())
			return notAtHometown;
		return seek();
	}
	
	public String seek() {
		player.addAllSnacks(player.getCandyStash(), player.getCandies());
		player.getCandyStash().clear();
		return "Alles eingepackt!";
	}
}
