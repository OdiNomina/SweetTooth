package com.github.sweettooth.controllerSwing.buttonListener;

import java.awt.event.ActionEvent;

import javax.swing.JComponent;
import javax.swing.JLabel;

import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.controllerAPI.Processable;

public class HideListener extends ButtonListener {
	IGameData gameData;
	Processable event;
	JLabel[] answerBox;
	
	public HideListener(JComponent nextInFocus, IGameData gameData, Processable event, JLabel... answerBox) {
		super(nextInFocus);
		this.gameData = gameData;
		this.event = event;
		this.answerBox = answerBox;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String answer = event.process(null, null, null);
		gameData.notifyObservers();
		answerBox[0].setText(answer);
		nextInFocus.requestFocusInWindow();
	}
}
