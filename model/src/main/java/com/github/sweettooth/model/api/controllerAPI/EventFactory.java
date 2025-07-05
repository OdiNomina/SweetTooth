package com.github.sweettooth.model.api.controllerAPI;

import com.github.sweettooth.model.api.GameModelInterface;
import com.github.sweettooth.model.events.DefaultEventFactory;

public abstract class EventFactory {
	public static EventFactory getDefaultFactory() {
		return new DefaultEventFactory();
	}
	
	public EventFactory(){}
	
	public abstract Processable createEvent(String event, GameModelInterface gameData);
}
