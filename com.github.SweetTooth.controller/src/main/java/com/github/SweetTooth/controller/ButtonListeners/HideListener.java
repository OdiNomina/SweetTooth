package com.github.SweetTooth.controller.ButtonListeners;

import com.github.SweetTooth.controller.controllerAPI.LanternaController;
import com.googlecode.lanterna.gui2.Button;

public class HideListener extends ButtonListener {
	public HideListener(String buttonName, LanternaController controller) {
		super(buttonName, controller);
	}
	
	@Override
	public void onTriggered(Button button) {
		String answer = getEventFactory().create("Hide", getGame()).handle(null, null, null);
		getPanelContent().updateContent();
		getPanelContent().getLabels().get("hideSeekInfo").setText(answer);
	}
}
