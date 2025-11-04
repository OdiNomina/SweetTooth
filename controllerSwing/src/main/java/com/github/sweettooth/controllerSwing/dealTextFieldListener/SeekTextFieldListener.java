package com.github.sweettooth.controllerSwing.dealTextFieldListener;

import java.awt.event.ActionEvent;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.github.sweettooth.model.api.IGameRound;
import com.github.sweettooth.model.api.gameEvents.Processable;

public class SeekTextFieldListener extends TextFieldListener {
	SeekTextFieldListener(IGameRound gameData, Processable event, JComponent nextInFocus, JLabel answerBox) {
		super(nextInFocus, gameData, event, answerBox);
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
	            nextInFocus.setEnabled(true);
	            nextInFocus.requestFocusInWindow();
	            textField.setEnabled(false);

	        } catch (NumberFormatException ex) {
	            textField.setText("");
	            answerRecipient.setText("Du musst eine Zahl eingeben! (<= 100)");
	        }
	    }
	}
}
