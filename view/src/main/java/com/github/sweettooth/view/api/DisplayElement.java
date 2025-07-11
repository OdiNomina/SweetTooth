package com.github.sweettooth.view.api;

import com.github.sweettooth.controller.api.ControllerInterface;
import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.GameSettings;

@SuppressWarnings("exports")
public interface DisplayElement extends Runnable {
	
	DisplayElement initialize(IGameData gameModel, ControllerInterface controller, GameSettings modelSettings) throws NullPointerException;
	void run();
}
