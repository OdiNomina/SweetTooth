package com.github.SweetTooth.model.events;

import com.github.SweetTooth.model.games.Game;

public abstract class EventFactory {
	public static EventFactory getDefaultFactory() {
		return new DefaultEventFactory();
	}
	
	public abstract Event create(String event, Game game);
}
