package com.github.SweetTooth.controller.comboBoxListeners;

import com.github.SweetTooth.controller.controllerAPI.LanternaController;
import com.googlecode.lanterna.gui2.ComboBox;

public class SellSelectionListener extends ComboBoxListener {
	public SellSelectionListener(String comboBoxName, LanternaController controller) {
		super(comboBoxName, controller);
	}
	
	@Override
	public void onSelectionChanged(int selectedIndex, int previousSelection, boolean changedByUserInteraction) {
		if(changedByUserInteraction) {
			getPanelContent().getComboBoxes().get("sellSelection").setEnabled(false);
			getPanelContent().getTextBoxesIT().get("sellQuantity").setEnabled(true).takeFocus();
		}
	}
}
