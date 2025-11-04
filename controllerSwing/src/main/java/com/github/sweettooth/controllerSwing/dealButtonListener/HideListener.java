package com.github.sweettooth.controllerSwing.dealButtonListener;

import java.awt.event.ActionEvent;

import javax.swing.JComponent;
import javax.swing.JLabel;

import com.github.sweettooth.model.api.IGameRound;
import com.github.sweettooth.model.api.gameEvents.Processable;

public class HideListener extends ButtonListener {
	Processable event;
	JLabel[] answerBox;
	
	public HideListener(JComponent nextInFocus, IGameRound gameData, Processable event, JLabel... answerBox) {
		super(gameData, nextInFocus);
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
