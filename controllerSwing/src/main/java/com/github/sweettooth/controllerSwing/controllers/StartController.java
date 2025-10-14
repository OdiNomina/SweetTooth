package com.github.sweettooth.controllerSwing.controllers;

import java.awt.event.ActionListener;

import com.github.sweettooth.controllerSwing.api.IStartController;
import com.github.sweettooth.controllerSwing.startButtonListener.PlayListener;
import com.github.sweettooth.shared.api.FrameNavigator;

public class StartController implements IStartController {
	private final FrameNavigator frameNavigator;
	
	public StartController(FrameNavigator frameNavigator) {
		this.frameNavigator = frameNavigator;
	}
	
	@Override
	public ActionListener createButtonListener() {
		return new PlayListener(frameNavigator);
	}
}
