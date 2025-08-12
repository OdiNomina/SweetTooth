package com.github.sweettooth.controllerSwing.controllers;

import java.util.Objects;

import com.github.sweettooth.controllerSwing.api.IController;
import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.controllerAPI.EventFactory;

public class SwingController implements IController {
	private IGameData gameData;
	private EventFactory eventFactory;
	
	@Override
	public IController initialize(IGameData gameData) throws NullPointerException {
		this.gameData = Objects.requireNonNull(gameData);
		eventFactory = EventFactory.getDefaultFactory();
		return this;
	}

}
