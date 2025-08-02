package com.github.sweettooth.controllerSwing.api;

import com.github.sweettooth.controllerSwing.controllers.SwingController;
import com.github.sweettooth.model.api.IGameData;

@SuppressWarnings("exports")
public interface IController {
	public static IController getInstance() {
		return new SwingController();
	}
	
	IController initialize(IGameData gameModel) throws NullPointerException;
	
	
}
