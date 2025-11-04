package com.github.sweettooth.controllerSwing.dealComboBoxListener;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;

import com.github.sweettooth.model.api.IGameRound;
import com.github.sweettooth.model.api.ILocation;
import com.github.sweettooth.model.api.gameEvents.Processable;
import com.github.sweettooth.shared.api.util.UpdateGuard;

public class LocationSelectionListener extends ComboBoxListener {
	JLabel currentLocation;
	Processable event;
	JLabel[] answerRecipient;
	Processable applyInterestEvent;
	
	public LocationSelectionListener(UpdateGuard guard, JLabel currentLocation, JComponent nextInFocus, IGameRound gameRound, Processable event, Processable applyInterestEvent, JLabel... answerRecipient) {
		super(gameRound, guard, nextInFocus);
		this.currentLocation = currentLocation;
		this.gameRound = gameRound;
		this.event = event;
		this.answerRecipient = answerRecipient;
		this.applyInterestEvent = applyInterestEvent;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(guard.isUpdating()) return; // programmatisches Event ignorieren
		
		@SuppressWarnings("unchecked")
		JComboBox<String> comboBox = (JComboBox<String>)e.getSource();
		
		if(comboBox.getSelectedItem().equals(currentLocation.getText()))
			answerRecipient[0].setText("Du bist doch schon da!");
		else {
			try {
				gameRound.increaseDayOfGame(1);
				if(gameRound.isGameOver()) {
					gameRound.notifyObservers();
					return;
				}
				
				ILocation location = ILocation.valueOfficialName(comboBox.getSelectedItem().toString());	
				String[] eventAnswer = event.process(location.toString(), null, null);
    			
    			gameRound.notifyObservers();
    			
            	answerRecipient[0].setText(eventAnswer[0]);
            	answerRecipient[1].setText(eventAnswer[1]);
            	answerRecipient[2].setText(eventAnswer[2]);
            	
            	answerRecipient[3].setText(applyInterestEvent.process(null, null, null)[0]);
			}
			catch(IllegalArgumentException ex) {
				answerRecipient[0].setText("An exception occurred.");
				comboBox.requestFocusInWindow();
				ex.printStackTrace();
			}
			catch(ArrayIndexOutOfBoundsException ex) {
				gameRound.notifyObservers();
				ex.printStackTrace();
			}
			catch(IOException ex) {
				ex.printStackTrace();
			}
		}
		nextInFocus.requestFocusInWindow();
	}
}
