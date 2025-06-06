package com.github.sweettooth.controller.textBoxInputFilters;

import com.github.sweettooth.model.api.GameModelInterface;
import com.github.sweettooth.model.api.Processable;

import com.googlecode.lanterna.gui2.InputFilter;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;

abstract class TextBoxInputFilter implements InputFilter {
	Interactable nextInFocus;
	GameModelInterface gameData;
	Processable event;
	Label answerBox;
	
	TextBoxInputFilter(Interactable nextInFocus, GameModelInterface gameData, Processable event, Label answerBox) {
		this.nextInFocus = nextInFocus;
		this.gameData = gameData;
		this.event = event;
		this.answerBox = answerBox;
	}
}
