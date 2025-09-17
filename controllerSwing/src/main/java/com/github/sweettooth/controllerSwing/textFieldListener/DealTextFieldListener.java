package com.github.sweettooth.controllerSwing.textFieldListener;

import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.controllerAPI.Processable;

public class DealTextFieldListener extends TextFieldListener {
	JComboBox<String> associatedComboBox;
	
	DealTextFieldListener(IGameData gameData, Processable event, JComboBox<String> associatedComboBox, JComponent nextInFocus, JLabel answerBox) {
		super(nextInFocus, gameData, event, answerBox);
		this.associatedComboBox = associatedComboBox;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JTextField textField = (JTextField) e.getSource();

	    String text = textField.getText().strip();

	    if (text.isBlank()) {
	        textField.setText("");
	    } else {
	        try {
	            Integer input = Integer.parseInt(text);
	            if (input > 100) {
	                throw new NumberFormatException();
	            }

	            String eventAnswer = event.process((String)associatedComboBox.getSelectedItem(), input, null);
	            gameData.notifyObservers();

	            answerBox.setText(eventAnswer);
	            textField.setText("");
	            textField.setEnabled(false);
	            associatedComboBox.setEnabled(true);
	            associatedComboBox.requestFocusInWindow();

	        } catch (NumberFormatException ex) {
	            textField.setText("");
	            answerBox.setText("Du musst eine Zahl eingeben! (<= 100)");
	        }
	    }
	}
}
