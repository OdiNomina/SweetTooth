package com.github.SweetTooth.events;

import com.github.SweetTooth.characters.IPlayer;

public abstract class EventFactory {
	public static EventFactory getDefaultFactory() {
		return new DefaultEventFactory();
	}
	
	public abstract Event create(String event, IPlayer player);
}
