package com.github.sweettooth.controllerSwing.dealTextFieldListener;

import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JLabel;

import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.controllerAPI.Processable;

public abstract class TextFieldListener implements ActionListener {
	JComponent nextInFocus;
	IGameData gameData;
	Processable event;
	JLabel answerBox;
	
	TextFieldListener(JComponent nextInFocus, IGameData gameData, Processable event, JLabel answerBox) {
		this.nextInFocus = nextInFocus;
		this.gameData = gameData;
		this.event = event;
		this.answerBox = answerBox;
	}
}
