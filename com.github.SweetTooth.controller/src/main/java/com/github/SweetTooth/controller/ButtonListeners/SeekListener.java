package com.github.SweetTooth.controller.ButtonListeners;

import com.github.SweetTooth.controller.controllerAPI.LanternaController;
import com.googlecode.lanterna.gui2.Button;

public class SeekListener extends ButtonListener {
	public SeekListener(String buttonName, LanternaController controller) {
		super(buttonName, controller);
	}
	
	@Override
	public void onTriggered(Button button) {
		String snackInput = getPanelContent().getComboBoxes().get("stash").getSelectedItem();
		Integer snackQuantity = Integer.parseInt(getPanelContent().getTextBoxes().get("seekQuantity").getText());
		String answer = getEventFactory().create("Seek", getGame()).handle(snackInput, snackQuantity, null);
		getPanelContent().updateContent();
		getPanelContent().getLabels().get("hideSeekInfo").setText(answer);
		getPanelContent().getComboBoxes().get("stash").takeFocus();
		getPanelContent().getTextBoxes().get("seekQuantity").setEnabled(true);
		button.setEnabled(false);
	}
}
