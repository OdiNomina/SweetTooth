package com.github.SweetTooth.controller.controllerAPI;

import com.github.SweetTooth.controller.comboBoxListeners.*;
import com.github.SweetTooth.controller.ButtonListeners.*;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;

class ListenerFactory {
	private LanternaController controller;
	
	ListenerFactory(LanternaController controller){
		this.controller = controller;
	}
	
	ComboBox.Listener createComboBoxListener(String comboBoxName) {
		switch(comboBoxName) {
			case "buySelection": return new BuySelectionListener(comboBoxName, controller);
			case "sellSelection": return new SellSelectionListener(comboBoxName, controller);
			case "locationSelection": return new LocationSelectionListener(comboBoxName, controller);
			default: return null;
		}
	}
	
	Button.Listener createButtonListener(String buttonName) {
		switch(buttonName) {
			case "hide": return new HideListener(buttonName, controller);
			case "seek": return new SeekListener(buttonName, controller);
			case "exit": return new ExitListener(buttonName, controller);
			default: return null;
		}
	}
}
