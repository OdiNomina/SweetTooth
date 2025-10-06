package com.github.sweettooth.controllerSwing.startButtonListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class PlayListener implements ActionListener  {
	JFrame startFrame;
	JFrame dealFrame;
	
	public PlayListener(JFrame startFrame, JFrame dealFrame){
		this.startFrame = startFrame;
		this.dealFrame = dealFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		dealFrame.setVisible(true);
		startFrame.setVisible(false);
	}
}
