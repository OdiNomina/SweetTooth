package com.github.sweettooth.model.api;

import com.github.sweettooth.model.events.DefaultEventFactory;

public abstract class EventFactory {
	// --- controller
	
	public static EventFactory getDefaultFactory() {
		return new DefaultEventFactory();
	}
	
	public EventFactory(){}
	
	public abstract Processable create(String event, GameModelInterface gameData);
}
