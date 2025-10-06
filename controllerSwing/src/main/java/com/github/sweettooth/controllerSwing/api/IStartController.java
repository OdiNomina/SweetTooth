package com.github.sweettooth.controllerSwing.api;

import java.awt.event.ActionListener;
import javax.swing.JFrame;

import com.github.sweettooth.controllerSwing.controllers.StartController;

@SuppressWarnings("exports")
public interface IStartController {
	static IStartController getInstance() {
		return new StartController();
	}
	
	/**
	 * 
	 * @param startFrame
	 * @param dealFrame
	 * @return java.awt.event.ActionListener
	 */
	ActionListener createButtonListener(JFrame startFrame, JFrame dealFrame);
}
