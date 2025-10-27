package com.github.sweettooth.controllerSwing.controllers;

import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.JTextField;

import com.github.sweettooth.controllerSwing.api.IStartController;
import com.github.sweettooth.controllerSwing.startWindowListener.StartWindowCloseListener;
import com.github.sweettooth.controllerSwing.startButtonListener.PlayListener;
import com.github.sweettooth.controllerSwing.startTextFieldListener.PlayerTextFieldListener;
import com.github.sweettooth.model.api.ISessionData;
import com.github.sweettooth.shared.api.GameNavigator;
import com.github.sweettooth.shared.api.WindowNavigator;

public class StartController implements IStartController {
	private final GameNavigator gameNavigator;
	private final WindowNavigator windowNavigator;
	
	public StartController(GameNavigator gameNavigator, WindowNavigator windowNavigator) {
		this.gameNavigator = gameNavigator;
		this.windowNavigator = windowNavigator;
	}
	
	public GameNavigator getGameNavigator() {
		return gameNavigator;
	}

	public WindowNavigator getWindowNavigator() {
		return windowNavigator;
	}

	@Override
	public ActionListener createButtonListener(ISessionData sessionData, JTextField nameField) {
		return new PlayListener(this, sessionData, nameField);
	}

	@Override
	public ActionListener createTextFieldListener(ISessionData sessionData) {
		return new PlayerTextFieldListener(sessionData);
	}
	
	@Override
	public WindowListener createWindowCloseListener() {
		return new StartWindowCloseListener(this);
	}
}
