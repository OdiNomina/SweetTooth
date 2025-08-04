package com.github.sweettooth.controllerLanterna.textBoxInputFilters;

import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.controllerAPI.Processable;
import com.googlecode.lanterna.gui2.InputFilter;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;

abstract class TextBoxInputFilter implements InputFilter {
	Interactable nextInFocus;
	IGameData gameData;
	Processable event;
	Label answerBox;
	
	TextBoxInputFilter(Interactable nextInFocus, IGameData gameData, Processable event, Label answerBox) {
		this.nextInFocus = nextInFocus;
		this.gameData = gameData;
		this.event = event;
		this.answerBox = answerBox;
	}
}
