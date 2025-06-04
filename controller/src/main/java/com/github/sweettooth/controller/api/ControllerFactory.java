package com.github.sweettooth.controller.api;

import com.github.sweettooth.controller.controlUnits.LanternaController;
import com.github.sweettooth.model.games.GameData;

@SuppressWarnings("exports")
public class ControllerFactory {
	public static Controller create(GameData gameData) {
		return new LanternaController(gameData);
	}
}
