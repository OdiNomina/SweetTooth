package com.github.SweetTooth.controller.ButtonListeners;

import java.io.IOException;

import com.github.SweetTooth.controller.controllerAPI.LanternaController;
import com.googlecode.lanterna.gui2.Button;

public class ExitListener extends ButtonListener {
	public ExitListener(String buttonName, LanternaController controller) {
		super(buttonName, controller);
	}
	
	@Override
	public void onTriggered(Button button) {
		try {
			guiManager.stop();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
