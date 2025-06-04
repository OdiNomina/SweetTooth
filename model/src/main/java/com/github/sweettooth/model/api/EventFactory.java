package com.github.sweettooth.model.api;

import com.github.sweettooth.model.events.DefaultEventFactory;
import com.github.sweettooth.model.games.GameData;

public abstract class EventFactory {
	public static EventFactory getDefaultFactory() {
		return new DefaultEventFactory();
	}
	
	public EventFactory(){}
	
	public abstract Processable create(String event, GameData game);
}
