package com.github.sweettooth.controllerLanterna.buttonListeners;

import com.github.sweettooth.model.api.IGameRound;
import com.github.sweettooth.model.api.gameEvents.Processable;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;

public class HideListener extends ButtonListener {
	IGameRound gameRound;
	Processable event;
	Label[] answerRecipient;
	
	public HideListener(Interactable nextInFocus, IGameRound gameRound, Processable event, Label... answerRecipient) {
		super(nextInFocus);
		this.gameRound = gameRound;
		this.event = event;
		this.answerRecipient = answerRecipient;
	}
	
	@Override
	public void onTriggered(Button button) {
		String[] eventAnswer = event.process(null, null, null);
		gameRound.notifyObservers();
		answerRecipient[0].setText(eventAnswer[0]);
		nextInFocus.takeFocus();
	}
}
