package com.github.sweettooth.controllerSwing.startWindowListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.github.sweettooth.controllerSwing.controllers.StartController;

public class StartWindowCloseListener extends WindowAdapter {
	private final StartController startController;

	public StartWindowCloseListener(StartController startController) {
		this.startController = startController;
	}
	
	@Override
    public void windowClosing(WindowEvent e) {
		startController.getGameNavigator().exitGame();
    }
}
