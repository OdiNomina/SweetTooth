package com.github.sweettooth.controllerSwing.dealButtonListener;

import java.awt.event.ActionEvent;

import javax.swing.JComponent;
import javax.swing.JLabel;

import com.github.sweettooth.model.api.IGameRound;
import com.github.sweettooth.model.api.gameEvents.Processable;

public class HideListener extends ButtonListener {
	Processable event;
	JLabel[] answerRecipient;
	
	public HideListener(JComponent nextInFocus, IGameRound gameRound, Processable event, JLabel... answerRecipient) {
		super(gameRound, nextInFocus);
		this.event = event;
		this.answerRecipient = answerRecipient;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String[] eventAnswer = event.process(null, null, null);
		gameRound.notifyObservers();
		answerRecipient[0].setText(eventAnswer[0]);
		nextInFocus.requestFocusInWindow();
	}
}
