package com.github.sweettooth.controllerSwing.api;

import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.JTextField;

import com.github.sweettooth.controllerSwing.controllers.StartController;
import com.github.sweettooth.model.api.gameSession.IGameSession;
import com.github.sweettooth.shared.api.gameControl.GameNavigator;
import com.github.sweettooth.shared.api.gameControl.WindowNavigator;

@SuppressWarnings("exports")
public interface IStartController {
	static IStartController getInstance(GameNavigator gameNavigator, WindowNavigator windowNavigator) {
		return new StartController(gameNavigator, windowNavigator);
	}
	
	ActionListener createButtonListener(IGameSession sessionData, JTextField nameField);
	ActionListener createTextFieldListener(IGameSession sessionData);
	WindowListener createWindowCloseListener();
}
