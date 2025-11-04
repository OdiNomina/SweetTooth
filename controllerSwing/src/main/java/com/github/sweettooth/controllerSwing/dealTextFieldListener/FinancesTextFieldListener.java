package com.github.sweettooth.controllerSwing.dealTextFieldListener;

import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.github.sweettooth.model.api.IGameRound;
import com.github.sweettooth.model.api.gameEvents.Processable;

public class FinancesTextFieldListener extends TextFieldListener {
	Locale locale;
	
	FinancesTextFieldListener(Locale locale, IGameRound gameRound, Processable event, JComponent nextInFocus, JLabel answerRecipient) {
		super(nextInFocus, gameRound, event, answerRecipient);
		this.locale = locale;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JTextField textField = (JTextField) e.getSource();

	    String text = textField.getText().strip();

	    if (text.isBlank())
	        textField.setText("");
	    else {
	        try {
	        	NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
	            Double input = numberFormat.parse(text).doubleValue();
	            if (input > 100_000) {
	                throw new NumberFormatException();
	            }

	            String[] eventAnswer = event.process(null, null, input);
	            gameRound.notifyObservers();

	            answerRecipient.setText(eventAnswer[0]);
	            textField.setText("");
	            nextInFocus.requestFocusInWindow();
	        }
	        catch(ParseException ex) {
	        	ex.printStackTrace();
	        }
	        catch (NumberFormatException ex) {
	            textField.setText("");
	            answerRecipient.setText("Du musst eine Zahl eingeben! (<= 100 000)");
	        }
	    }
	}
}
