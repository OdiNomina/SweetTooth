package com.github.sweettooth.controllerSwing.controllers;

import java.awt.event.ActionListener;

import javax.swing.JTextField;

import com.github.sweettooth.controllerSwing.api.IStartController;
import com.github.sweettooth.controllerSwing.startButtonListener.PlayListener;
import com.github.sweettooth.controllerSwing.startTextFieldListener.PlayerTextFieldListener;
import com.github.sweettooth.model.api.ISessionData;
import com.github.sweettooth.shared.api.FrameNavigator;

public class StartController implements IStartController {
	private final FrameNavigator frameNavigator;
	
	public StartController(FrameNavigator frameNavigator) {
		this.frameNavigator = frameNavigator;
	}
	
	@Override
	public ActionListener createButtonListener(ISessionData sessionData, JTextField nameField) {
		return new PlayListener(frameNavigator, sessionData, nameField);
	}

	@Override
	public ActionListener createTextFieldListener(ISessionData sessionData) {
		return new PlayerTextFieldListener(sessionData);
	}
}
