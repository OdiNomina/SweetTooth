package com.github.sweettooth.view.api;

import com.github.sweettooth.controller.api.ControllerInterface;
import com.github.sweettooth.model.api.GameModelInterface;
import com.github.sweettooth.model.api.ModelSettings;

@SuppressWarnings("exports")
public interface DisplayElement extends Runnable{
	DisplayElement initialize(GameModelInterface gameModel, ControllerInterface controller, ModelSettings modelSettings) throws NullPointerException;
	void startGuiThread();
}
