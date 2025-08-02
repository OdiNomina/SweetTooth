package com.github.sweettooth.view.api;

import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.view.elements.LanternaGUI;
import com.github.sweettooth.view.swingGUI.SwingGUI;

public class DisplayFactory {
	public enum DisplayStyle {
		LANTERNA, SWING
	}
	
	public DisplayFactory(){}
	
	@SuppressWarnings("exports")
	public static DisplayElement createDisplay(DisplayStyle displayStyle, IGameData gameData) {
		return switch(displayStyle) {
			case LANTERNA -> new LanternaGUI(gameData);
			case SWING -> new SwingGUI(gameData);
		};
	}
}