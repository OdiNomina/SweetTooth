package com.github.sweettooth.controllerSwing.api;

import com.github.sweettooth.model.api.IGameData;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.github.sweettooth.controllerSwing.controllers.SwingController;

@SuppressWarnings("exports")
public interface IController {
	static IController getInstance() {
		return new SwingController();
	}
	
	IController initialize(IGameData gameModel) throws NullPointerException;
	
}
