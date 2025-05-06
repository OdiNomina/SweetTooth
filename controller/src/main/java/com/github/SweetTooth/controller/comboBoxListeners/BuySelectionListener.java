package com.github.SweetTooth.controller.comboBoxListeners;

import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Interactable;


public class BuySelectionListener extends ComboBoxListener {
	public BuySelectionListener(ComboBox<String> thisComboBox, Interactable nextInFocus) {
		super(thisComboBox, nextInFocus);
	}
	
	@Override
	public void onSelectionChanged(int selectedIndex, int previousSelection, boolean changedByUserInteraction) {
		if(changedByUserInteraction) {
			thisComboBox.setEnabled(false);
			nextInFocus.setEnabled(true).takeFocus();
		}
	}
}
