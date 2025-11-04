package com.github.sweettooth.controllerSwing.dealTextFieldListener;

import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JLabel;

import com.github.sweettooth.model.api.IGameRound;
import com.github.sweettooth.model.api.gameEvents.Processable;

public abstract class TextFieldListener implements ActionListener {
	JComponent nextInFocus;
	IGameRound gameRound;
	Processable event;
	JLabel answerRecipient;
	
	TextFieldListener(JComponent nextInFocus, IGameRound gameData, Processable event, JLabel answerBox) {
		this.nextInFocus = nextInFocus;
		this.gameRound = gameData;
		this.event = event;
		this.answerRecipient = answerBox;
	}
}
