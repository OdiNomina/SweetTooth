package com.github.sweettooth.controllerSwing.dealButtonListener;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.github.sweettooth.model.api.IGameRound;
import com.github.sweettooth.model.api.gameEvents.Processable;

public class SeekListener extends ButtonListener {
	JComboBox<String> associatedComboBox;
	JTextField associatedTextBox;
	Processable event;
	JLabel[] answerRecipient;
	
	public SeekListener(JComboBox<String> associatedComboBox, JTextField associatedTextBox, JComponent nextInFocus, IGameRound gameRound, Processable event, JLabel... answerRecipient) {
		super(gameRound, nextInFocus);
		this.associatedComboBox = associatedComboBox;
		this.associatedTextBox = associatedTextBox;
		this.event = event;
		this.answerRecipient = answerRecipient;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		String snackInput = associatedComboBox.getSelectedItem().toString();
		Integer snackQuantity;
		try {
			snackQuantity = Integer.parseInt(associatedTextBox.getText());
		}
		catch(NumberFormatException ex) {
			ex.printStackTrace();
			snackQuantity = 0;
		}
		String[] eventAnswer = event.process(snackInput, snackQuantity, null);
		gameRound.notifyObservers();
		answerRecipient[0].setText(eventAnswer[0]);
		associatedTextBox.setEnabled(true);
		button.setEnabled(false);
		nextInFocus.requestFocusInWindow();
	}
}
