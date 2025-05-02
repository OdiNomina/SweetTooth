package com.github.SweetTooth.controller.ButtonListeners;

import java.io.IOException;

import com.googlecode.lanterna.gui2.Button;

public class ExitListener extends ButtonListener implements Button.Listener{
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
