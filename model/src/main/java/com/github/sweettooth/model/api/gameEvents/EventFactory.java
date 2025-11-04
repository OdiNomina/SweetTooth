package com.github.sweettooth.model.api.gameEvents;

import com.github.sweettooth.model.api.IGameRound;
import com.github.sweettooth.model.api.gameSession.IGameSession;
import com.github.sweettooth.model.gameEvents.DefaultEventFactory;

public abstract class EventFactory {
	public static EventFactory getDefaultFactory(IGameSession sessionData) {
		return new DefaultEventFactory(sessionData);
	}
	
	public abstract Processable createEvent(String event, IGameRound gameData);
}
