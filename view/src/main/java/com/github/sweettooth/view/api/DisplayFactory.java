package com.github.sweettooth.view.api;

import java.io.IOException;

import com.github.sweettooth.controller.api.ControllerInterface;
import com.github.sweettooth.model.api.GameModelInterface;
import com.github.sweettooth.view.elements.LanternaGUI;

@SuppressWarnings("exports")
public class DisplayFactory {
	public DisplayFactory(){}
	
	public static DisplayElement create(GameModelInterface model, ControllerInterface controller) throws IOException {
		return new LanternaGUI(model, controller);
	}
}