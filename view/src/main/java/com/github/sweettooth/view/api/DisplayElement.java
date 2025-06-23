package com.github.sweettooth.view.api;

import com.github.sweettooth.controller.api.ControllerInterface;
import com.github.sweettooth.model.api.GameModelInterface;
import com.github.sweettooth.model.api.Settings;

@SuppressWarnings("exports")
public interface DisplayElement extends Runnable{
	void initialize(GameModelInterface gameModel, ControllerInterface controller, Settings settings) throws NullPointerException;
	void startGuiThread();
}
