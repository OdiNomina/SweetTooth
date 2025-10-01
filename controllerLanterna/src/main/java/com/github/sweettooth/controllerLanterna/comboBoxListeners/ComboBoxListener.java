package com.github.sweettooth.controllerLanterna.comboBoxListeners;

import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Interactable;

public abstract class ComboBoxListener implements ComboBox.Listener {
	ComboBox<String> thisComboBox;
	Interactable nextInFocus;
	
	ComboBoxListener(ComboBox<String> thisComboBox, Interactable nextInFocus) {
		this.thisComboBox = thisComboBox;
		this.nextInFocus = nextInFocus;
	}
}
