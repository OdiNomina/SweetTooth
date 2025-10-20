package com.github.sweettooth.controllerSwing.startButtonListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import com.github.sweettooth.model.api.ISessionData;
import com.github.sweettooth.shared.api.FrameNavigator;

public class PlayListener implements ActionListener  {
	private final FrameNavigator frameNavigator;
    private final ISessionData sessionData;
    private final JTextField nameField;


	
    public PlayListener(FrameNavigator frameNavigator, ISessionData sessionData, JTextField nameField) {
        this.frameNavigator = frameNavigator;
        this.sessionData = sessionData;
        this.nameField = nameField;
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
        sessionData.setNamePlayer(nameField.getText().trim());
        nameField.setEnabled(false);
		
		frameNavigator.hideStartFrame();
		frameNavigator.startNewGameRound();
	}
}
