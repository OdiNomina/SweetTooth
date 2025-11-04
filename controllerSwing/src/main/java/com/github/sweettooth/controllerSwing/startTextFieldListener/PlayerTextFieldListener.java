package com.github.sweettooth.controllerSwing.startTextFieldListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import com.github.sweettooth.model.api.gameSession.IGameSession;

public class PlayerTextFieldListener implements ActionListener {
	IGameSession sessionData;
	
	public PlayerTextFieldListener(IGameSession sessionData) {
		this.sessionData = sessionData;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JTextField textField = (JTextField) e.getSource();
		sessionData.setNamePlayer(textField.getText());
		textField.setEnabled(false);
	}
}
