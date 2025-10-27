package com.github.sweettooth.model.api.gameEvents;

import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.ISessionData;
import com.github.sweettooth.model.events.DefaultEventFactory;

public abstract class EventFactory {
	public static EventFactory getDefaultFactory(ISessionData sessionData) {
		return new DefaultEventFactory(sessionData);
	}
	
	public abstract Processable createEvent(String event, IGameData gameData);
}
