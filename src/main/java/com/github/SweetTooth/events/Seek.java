package com.github.SweetTooth.events;

final class Seek extends Event implements IEvent {
	@Override
	public String handleEvent() {
		if(!isAtHometown())
			return notAtHometown;
		return seek();
	}
	
	public String seek() {
		player.addAllCandies(player.getStash(), player.getCandies());
		player.getStash().clear();
		return "Alles eingepackt!";
	}
}
