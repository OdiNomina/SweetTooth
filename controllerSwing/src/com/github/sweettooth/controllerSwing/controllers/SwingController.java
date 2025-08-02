package com.github.sweettooth.controllerSwing.controllers;

import java.util.Objects;

import com.github.sweettooth.controllerSwing.api.IController;
import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.controllerAPI.EventFactory;

public class SwingController implements IController {
	private IGameData gameModel;
	private EventFactory eventFactory;
	
	public SwingController() {}
	
	@Override
	public IController initialize(IGameData gameModel) throws NullPointerException {
		this.gameModel = Objects.requireNonNull(gameModel);
		eventFactory = EventFactory.getDefaultFactory();

		return this;
	}
	
	public IGameData getGameModel() {
		return gameModel;
	}
	
	public EventFactory getEventFactory() {
		return eventFactory;
	}
	
	
}
