package com.github.sweettooth.controllerSwing.startButtonListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

import com.github.sweettooth.controllerSwing.controllers.StartController;
import com.github.sweettooth.model.api.gameSession.IGameSession;

public class PlayListener implements ActionListener  {
	private final StartController startController;
    private final IGameSession sessionData;
    private final JTextField nameField;
	
    public PlayListener(StartController startController, IGameSession sessionData, JTextField nameField) {
        this.startController = startController;
        this.sessionData = sessionData;
        this.nameField = nameField;
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
        sessionData.setNamePlayer(nameField.getText().trim());
        nameField.setEnabled(false);
		
        startController.getGameNavigator().newDealGame();
        startController.getWindowNavigator().hideStartWindow();
        startController.getWindowNavigator().showDealWindow();
	}
}
