package com.github.SweetTooth.controller.ButtonListeners;

import com.github.SweetTooth.controller.controllerAPI.LanternaController;
import com.github.SweetTooth.model.events.Event;
import com.github.SweetTooth.model.events.EventFactory;
import com.github.SweetTooth.model.game.Game;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Label;

abstract class ButtonListener implements Button.Listener {
	EventFactory eventFactory;
	Game game;
	Event event;
	Label answerBox;
	
	ButtonListener(String buttonName, LanternaController controller) {
		eventFactory = controller.getEventFactory();
		game = controller.getGame();
		switchEvent(buttonName);
		switchAnswerBox(buttonName);
	}
	
	void switchEvent(String buttonName) {
		switch(buttonName) {
			case "hide" -> event = eventFactory.create("Hide", game);
			case "seek" -> event = eventFactory.create("Seek", game);
		}
	}
	
	void switchAnswerBox(String buttonName) {
		switch(buttonName) {
			case "hide", "seek" -> answerBox = getPanelContent().getLabels().get("hideSeekInfo");
		}
	}
}
