package com.github.sweettooth.view.api;

import com.github.sweettooth.view.elements.LanternaGUI;
import com.github.sweettooth.view.swingGUI.SwingGUI;

public class DisplayFactory {
	public enum DisplayStyle {
		LANTERNA, SWING
	}
	
	public DisplayFactory(){}
	
	public static DisplayElement create(DisplayStyle display) {
		return switch(display) {
			case LANTERNA -> new LanternaGUI();
			case SWING -> new SwingGUI();
		};
	}
}