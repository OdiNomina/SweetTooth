package com.github.sweettooth.view.api;

import java.io.IOException;

import com.github.sweettooth.controller.api.Controller;
import com.github.sweettooth.view.elements.LanternaGUI;

@SuppressWarnings("exports")
public class DisplayFactory {
	public static DisplayElement create(Controller controller) throws IOException {
		return new LanternaGUI(controller);
	}
}