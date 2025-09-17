package com.github.sweettooth.controllerSwing.comboBoxListener;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;

import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.ILocation;
import com.github.sweettooth.model.api.controllerAPI.Processable;

public class LocationSelectionListener extends ComboBoxListener {
	JLabel currentLocation;
	IGameData gameData;
	Processable event;
	JLabel[] answerBox;
	Processable applyInterestEvent;
	
	public LocationSelectionListener(JLabel currentLocation, JComponent nextInFocus, IGameData gameData, Processable event, Processable applyInterestEvent, JLabel... answerBox) {
		super(nextInFocus);
		this.currentLocation = currentLocation;
		this.gameData = gameData;
		this.event = event;
		this.answerBox = answerBox;
		this.applyInterestEvent = applyInterestEvent;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox<String> comboBox = (JComboBox<String>)e.getSource();
		
		if(comboBox.getSelectedItem().equals(currentLocation.getText()))
			answerBox[0].setText("Du bist doch schon da!");
		else
			try {
				gameData.increaseDayOfGame(1);
				if(gameData.isGameOver()) {
					gameData.notifyObservers();
					return;
				}
				ILocation location = ILocation.valueOfficialName(comboBox.getSelectedItem().toString());	
				Processable.Answer answer = event.processMultipleAnswers(location.toString(), null, null);
    			
    			gameData.notifyObservers();
    			
            	answerBox[0].setText(answer.answer1());
            	answerBox[1].setText(answer.answer2());
            	answerBox[2].setText(answer.answer3());
            	
            	answerBox[3].setText(applyInterestEvent.process(null, null, null));
			}
			catch(IllegalArgumentException ex) {
				answerBox[0].setText("An exception occurred.");
				comboBox.requestFocusInWindow();
				ex.printStackTrace();
			}
			catch(ArrayIndexOutOfBoundsException ex) {
				gameData.notifyObservers();
				ex.printStackTrace();
			}
			catch(IOException ex) {
				ex.printStackTrace();
			}
		
		nextInFocus.requestFocusInWindow();
	}
}
