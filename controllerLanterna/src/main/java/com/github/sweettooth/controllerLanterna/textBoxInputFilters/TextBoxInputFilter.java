package com.github.sweettooth.controllerLanterna.textBoxInputFilters;

import com.github.sweettooth.model.api.IGameRound;
import com.github.sweettooth.model.api.gameEvents.Processable;
import com.googlecode.lanterna.gui2.InputFilter;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;

abstract class TextBoxInputFilter implements InputFilter {
	Interactable nextInFocus;
	IGameRound gameRound;
	Processable event;
	Label answerRecipient;
	
	TextBoxInputFilter(Interactable nextInFocus, IGameRound gameData, Processable event, Label answerBox) {
		this.nextInFocus = nextInFocus;
		this.gameRound = gameData;
		this.event = event;
		this.answerRecipient = answerBox;
	}
}
