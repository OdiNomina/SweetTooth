package com.github.SweetTooth.controller.comboBoxListeners;

import com.googlecode.lanterna.gui2.ComboBox;

public class SellSelectionListener extends ComboBoxListener implements ComboBox.Listener{
	@Override
	public void onSelectionChanged(int selectedIndex, int previousSelection, boolean changedByUserInteraction) {
		if(changedByUserInteraction) {
			getPanelContent().getComboBoxes().get("sellSelection").setEnabled(false);
			getPanelContent().getTextBoxesIT().get("sellQuantity").setEnabled(true).takeFocus();
		}
	}
}
