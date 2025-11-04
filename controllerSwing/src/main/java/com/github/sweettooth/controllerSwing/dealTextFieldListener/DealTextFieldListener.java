package com.github.sweettooth.controllerSwing.dealTextFieldListener;

import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.github.sweettooth.model.api.IGameRound;
import com.github.sweettooth.model.api.gameEvents.Processable;

public class DealTextFieldListener extends TextFieldListener {
	JComboBox<String> associatedComboBox;
	
	DealTextFieldListener(IGameRound gameRound, Processable event, JComboBox<String> associatedComboBox, JComponent nextInFocus, JLabel answerRecipient) {
		super(nextInFocus, gameRound, event, answerRecipient);
		this.associatedComboBox = associatedComboBox;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JTextField textField = (JTextField) e.getSource();

	    String text = textField.getText().strip();

	    if (text.isBlank())
	        textField.setText("");
	    else {
	        try {
	            Integer input = Integer.parseInt(text);
	            if (input > 100) {
	                throw new NumberFormatException();
	            }
	            String[] eventAnswer = event.process((String)associatedComboBox.getSelectedItem(), input, null);
	            gameRound.notifyObservers();

	            answerRecipient.setText(eventAnswer[0]);
	            textField.setText("");
	            textField.setEnabled(false);
	            associatedComboBox.setEnabled(true);
	            associatedComboBox.requestFocusInWindow();

	        }
	        catch (NumberFormatException ex) {
	            textField.setText("");
	            answerRecipient.setText("Du musst eine Zahl eingeben! (<= 100)");
	        }
	    }
	}
}
