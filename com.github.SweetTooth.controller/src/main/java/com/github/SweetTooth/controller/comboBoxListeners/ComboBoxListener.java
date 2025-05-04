package com.github.SweetTooth.controller.comboBoxListeners;

import com.github.SweetTooth.controller.controllerAPI.LanternaController;
import com.github.SweetTooth.model.events.Event;
import com.github.SweetTooth.model.events.EventFactory;
import com.github.SweetTooth.model.game.Game;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Label;

public abstract class ComboBoxListener implements ComboBox.Listener {
	EventFactory eventFactory;
	Game game;
	Event event;
	Label answerBox;
	
	ComboBoxListener(String comboBoxName, LanternaController controller) {
		eventFactory = controller.getEventFactory();
		game = controller.getGame();
		switchEvent(comboBoxName);
		switchAnswerBox(comboBoxName);
	}
	
	void switchEvent(String comboBoxName) {
		switch(comboBoxName) {
			case "locationSelection" -> event = eventFactory.create("Travel", game);
		}
	}
	
	void switchAnswerBox(String comboBoxName) {
		switch(comboBoxName) {
			case "locationSelection" -> answerBox = getPanelContent().getLabels().get("travelEventInfo1");
		}
	}
}
