package com.github.sweettooth.controllerSwing.startButtonListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.github.sweettooth.shared.api.FrameNavigator;

public class PlayListener implements ActionListener  {
	FrameNavigator frameNavigator;
	
	public PlayListener(FrameNavigator frameNavigator){
		this.frameNavigator = frameNavigator;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		frameNavigator.hideStartFrame();
		frameNavigator.showDealFrameNewGameRound();
	}
}
