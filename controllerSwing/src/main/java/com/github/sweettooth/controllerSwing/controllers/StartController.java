package com.github.sweettooth.controllerSwing.controllers;

import java.awt.event.ActionListener;
import javax.swing.JFrame;

import com.github.sweettooth.controllerSwing.api.IStartController;
import com.github.sweettooth.controllerSwing.startButtonListener.PlayListener;

public class StartController implements IStartController {
	@Override
	public ActionListener createButtonListener(JFrame startFrame, JFrame dealFrame) {
		return new PlayListener(startFrame, dealFrame);
	}
}
