package com.github.sweettooth.view.api;

import com.github.sweettooth.view.elements.LanternaGUI;

public class DisplayFactory {
	public DisplayFactory(){}
	
	public static DisplayElement create(){
		return new LanternaGUI();
	}
}