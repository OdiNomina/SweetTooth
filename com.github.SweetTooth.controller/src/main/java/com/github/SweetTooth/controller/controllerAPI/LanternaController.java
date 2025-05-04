package com.github.SweetTooth.controller.controllerAPI;

import com.github.SweetTooth.model.events.EventFactory;
import com.github.SweetTooth.model.games.Game;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.InputFilter;

public class LanternaController {
	private Game game;
	private EventFactory eventFactory = EventFactory.getDefaultFactory();
	private InputFilterFactory inputFilterFactory = new InputFilterFactory(this);
	private ListenerFactory listenerFactory = new ListenerFactory(this);
	
	public LanternaController(Game game) {
		this.game = game;
	}
	
	public Game getGame() {
		return game;
	}
	
	public EventFactory getEventFactory() {
		return eventFactory;
	}
	
	public InputFilter createInputFilter(String textBoxName) {
		return inputFilterFactory.createInputFilter(textBoxName);
	}
	
	public ComboBox.Listener createComboBoxListener(String comboBoxName) {
		return listenerFactory.createComboBoxListener(comboBoxName);
	}
	
	public Button.Listener createButtonListener(String buttonName) {
		return listenerFactory.createButtonListener(buttonName);
	}
}
