package com.github.sweettooth.controller.buttonListeners;

import com.github.sweettooth.model.api.Processable;
import com.github.sweettooth.model.games.GameData;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;

public class HideListener extends ButtonListener {
	GameData gameData;
	Processable event;
	Label[] answerBox;
	
	public HideListener(Interactable nextInFocus, GameData gameData, Processable event, Label... answerBox) {
		super(nextInFocus);
		this.gameData = gameData;
		this.event = event;
		this.answerBox = answerBox;
	}
	
	@Override
	public void onTriggered(Button button) {
		String answer = event.handle(null, null, null);
		gameData.gameDataChanged();
		answerBox[0].setText(answer);
		nextInFocus.takeFocus();
	}
}
