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
	JLabel[] answerBox;
	
	public SeekListener(JComboBox<String> associatedComboBox, JTextField associatedTextBox, JComponent nextInFocus, IGameRound gameData, Processable event, JLabel... answerBox) {
		super(gameData, nextInFocus);
		this.associatedComboBox = associatedComboBox;
		this.associatedTextBox = associatedTextBox;
		this.event = event;
		this.answerBox = answerBox;
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
		String answer = event.process(snackInput, snackQuantity, null);
		gameData.notifyObservers();
		answerBox[0].setText(answer);
		associatedTextBox.setEnabled(true);
		button.setEnabled(false);
		nextInFocus.requestFocusInWindow();
	}
}
