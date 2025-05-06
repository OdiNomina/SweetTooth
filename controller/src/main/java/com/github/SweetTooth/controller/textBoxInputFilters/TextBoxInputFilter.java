package com.github.SweetTooth.controller.textBoxInputFilters;

import com.github.SweetTooth.model.events.Event;
import com.github.SweetTooth.model.games.Game;

import com.googlecode.lanterna.gui2.InputFilter;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;

abstract class TextBoxInputFilter implements InputFilter {
	Interactable nextInFocus;
	Game game;
	Event event;
	Label answerBox;
	
	TextBoxInputFilter(Interactable nextInFocus, Game game, Event event, Label answerBox) {
		this.nextInFocus = nextInFocus;
		this.game = game;
		this.event = event;
		this.answerBox = answerBox;
	}
}
