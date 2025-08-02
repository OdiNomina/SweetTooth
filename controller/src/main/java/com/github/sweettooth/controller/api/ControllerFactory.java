package com.github.sweettooth.controller.api;

import com.github.sweettooth.controller.controlUnits.LanternaController;

public class ControllerFactory {
	public enum ControlUnit {
		LANTERNA, SWING
	}
	
	public static ControllerInterface createController(ControlUnit controlUnit) {
		return switch(controlUnit) {
				case LANTERNA -> new LanternaController();
				case SWING -> null;
			};
	}
	
	public ControllerFactory(){}
}
