package com.github.sweettooth.controllerSwing.api;

import java.awt.event.ActionListener;

import javax.swing.JTextField;

import com.github.sweettooth.controllerSwing.controllers.StartController;
import com.github.sweettooth.model.api.ISessionData;
import com.github.sweettooth.shared.api.FrameNavigator;

@SuppressWarnings("exports")
public interface IStartController {
	static IStartController getInstance(FrameNavigator frameNavigator) {
		return new StartController(frameNavigator);
	}
	
	ActionListener createButtonListener(ISessionData sessionData, JTextField nameField);
	ActionListener createTextFieldListener(ISessionData sessionData);
}
