package com.github.SweetTooth.controller.comboBoxListeners;

import java.io.IOException;

import com.github.SweetTooth.controller.controllerAPI.LanternaController;
import com.github.SweetTooth.model.events.Event;
import com.github.SweetTooth.model.locations.Location;
import com.googlecode.lanterna.gui2.ComboBox;

public class LocationSelectionListener extends ComboBoxListener {
	public LocationSelectionListener(String comboBoxName, LanternaController controller) {
		super(comboBoxName, controller);
	}
	
	@Override
	public void onSelectionChanged(int selectedIndex, int previousSelection, boolean changedByUserInteraction) {
		if(changedByUserInteraction) {
			if(selectedIndex == previousSelection)
				getPanelContent().getLabels().get("travelEventInfo1").setText("Du bist doch schon da!");
			else
				try {
					getPanelContent().updateContent();
					Location location = Location.valueOfficialName(getPanelContent().getComboBoxes().get("locationSelection").getItem(selectedIndex));
	    			String input = location.toString();
	            	Event.Answer answer = getEventFactory().create("Travel", getGame()).handleMultipleAnswers(input, null, null);
	            	String applyInterestAnswer = getEventFactory().create("ApplyInterest", getGame()).handle(null, null, null);
	            	getPanelContent().getLabels().get("travelEventInfo1").setText(answer.answer()[0]);
	            	getPanelContent().getLabels().get("travelEventInfo2").setText(answer.answer()[1]);
	            	getPanelContent().getLabels().get("travelEventInfo3").setText(answer.answer()[2]);
	            	getPanelContent().getLabels().get("travelInterestInfo").setText(applyInterestAnswer);
	        	    getGame().increaseDayOfGame(1, getPanelContent());
				}
				catch(IllegalArgumentException e) { e.printStackTrace(); }
				catch (IOException e) {	e.printStackTrace(); }
		}
	}
}
