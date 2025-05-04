package com.github.SweetTooth.controller.comboBoxListeners;

import com.github.SweetTooth.controller.controllerAPI.LanternaController;
import com.googlecode.lanterna.gui2.ComboBox;

public class BuySelectionListener extends ComboBoxListener {
	public BuySelectionListener(String comboBoxName, LanternaController controller) {
		super(comboBoxName, controller);
	}
	
	@Override
	public void onSelectionChanged(int selectedIndex, int previousSelection, boolean changedByUserInteraction) {
		if(changedByUserInteraction) {
			getPanelContent().getComboBoxes().get("buySelection").setEnabled(false);
			getPanelContent().getTextBoxesIT().get("buyQuantity").setEnabled(true).takeFocus();
		}
	}
}
