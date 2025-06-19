package com.github.sweettooth.controller.api;

import com.github.sweettooth.controller.controlUnits.LanternaController;

public class ControllerFactory {
	public static ControllerInterface create() {
		return new LanternaController();
	}
	
	public ControllerFactory(){}
}
