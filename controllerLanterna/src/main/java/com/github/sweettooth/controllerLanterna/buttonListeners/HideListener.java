package com.github.sweettooth.controllerLanterna.buttonListeners;

import com.github.sweettooth.model.api.IGameRound;
import com.github.sweettooth.model.api.gameEvents.Processable;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;

public class HideListener extends ButtonListener {
	IGameRound gameData;
	Processable event;
	Label[] answerBox;
	
	public HideListener(Interactable nextInFocus, IGameRound gameData, Processable event, Label... answerBox) {
		super(nextInFocus);
		this.gameData = gameData;
		this.event = event;
		this.answerBox = answerBox;
	}
	
	@Override
	public void onTriggered(Button button) {
		String answer = event.process(null, null, null);
		gameData.notifyObservers();
		answerBox[0].setText(answer);
		nextInFocus.takeFocus();
	}
}
