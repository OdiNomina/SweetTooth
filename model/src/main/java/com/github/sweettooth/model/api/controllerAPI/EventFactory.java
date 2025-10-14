package com.github.sweettooth.model.api.controllerAPI;

import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.ISessionData;
import com.github.sweettooth.model.events.DefaultEventFactory;
import com.github.sweettooth.model.games.GameData;
import com.github.sweettooth.model.session.SessionData;

public abstract class EventFactory {
	public static EventFactory getDefaultFactory(ISessionData sessionData) {
		return new DefaultEventFactory((SessionData)sessionData);
	}
	
	public EventFactory(){}
	
	public Processable createEvent(String event, IGameData gameData) {
		return getEvent(event, (GameData)gameData);
	}
	
	@SuppressWarnings("exports")
	public abstract Processable getEvent(String event, GameData gameData);
}
