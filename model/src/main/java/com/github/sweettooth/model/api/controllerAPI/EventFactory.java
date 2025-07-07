package com.github.sweettooth.model.api.controllerAPI;

import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.events.DefaultEventFactory;
import com.github.sweettooth.model.games.GameData;

public abstract class EventFactory {
	public static EventFactory getDefaultFactory() {
		return new DefaultEventFactory();
	}
	
	public EventFactory(){}
	
	public Processable createEvent(String event, IGameData gameData) {
		return getEvent(event, (GameData)gameData);
	}
	
	@SuppressWarnings("exports")
	public abstract Processable getEvent(String event, GameData gameData);
}
