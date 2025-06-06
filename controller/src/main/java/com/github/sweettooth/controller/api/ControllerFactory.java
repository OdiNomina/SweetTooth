package com.github.sweettooth.controller.api;

import com.github.sweettooth.controller.controlUnits.LanternaController;
import com.github.sweettooth.model.api.GameModelInterface;

@SuppressWarnings("exports")
public class ControllerFactory {
	public ControllerFactory(){}
	
	public static ControllerInterface create(GameModelInterface gameData) {
		return new LanternaController(gameData);
	}
}
