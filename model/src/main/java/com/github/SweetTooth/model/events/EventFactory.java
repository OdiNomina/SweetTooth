package com.github.sweettooth.model.events;

import com.github.sweettooth.model.games.Game;

public abstract class EventFactory {
	EventFactory(){}
	
	public static EventFactory getDefaultFactory() {
		return new DefaultEventFactory();
	}
	
	public abstract Event create(String event, Game game);
}
