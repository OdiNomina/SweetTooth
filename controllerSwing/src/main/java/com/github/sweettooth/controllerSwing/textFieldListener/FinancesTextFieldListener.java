package com.github.sweettooth.controllerSwing.textFieldListener;

import java.awt.event.ActionEvent;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.controllerAPI.Processable;

public class FinancesTextFieldListener extends TextFieldListener {
	FinancesTextFieldListener(IGameData gameData, Processable event, JComponent nextInFocus, JLabel answerBox) {
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
	            Double input = Double.parseDouble(text);
	            if (input > 100_000) {
	                throw new NumberFormatException();
	            }

	            String eventAnswer = event.process(null, null, input);
	            gameData.notifyObservers();

	            answerBox.setText(eventAnswer);
	            textField.setText("");
	            nextInFocus.requestFocusInWindow();
	        }
	        catch (NumberFormatException ex) {
	            textField.setText("");
	            answerBox.setText("Du musst eine Zahl eingeben! (<= 100.000)");
	        }
	    }
	}
}
