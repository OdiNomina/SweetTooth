package com.github.SweetTooth.controller.controllerAPI;

import java.util.ArrayList;
import java.util.Comparator;

import com.github.SweetTooth.controller.ButtonListeners.ButtonListenerFactory;
import com.github.SweetTooth.controller.TextBoxInputFilters.InputFilterFactory;
import com.github.SweetTooth.controller.comboBoxListeners.ComboBoxListenerFactory;

import com.github.SweetTooth.model.characters.Player;
import com.github.SweetTooth.model.characters.MoneyDealer;
import com.github.SweetTooth.model.events.DefaultEventFactory;
import com.github.SweetTooth.model.events.EventFactory;
import com.github.SweetTooth.model.games.Game;
import com.github.SweetTooth.model.snacks.Snackable;
import com.github.SweetTooth.model.snacks.CandyFactory;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.InputFilter;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.TextBox;

public class LanternaController {
	private Game game;
	private Player player;
	private MoneyDealer bank;
	private MoneyDealer loanShark;
	private EventFactory eventFactory = new DefaultEventFactory();
	private InputFilterFactory inputFilterFactory = new InputFilterFactory(this);
	private ButtonListenerFactory buttonListenerFactory = new ButtonListenerFactory(this);
	private ComboBoxListenerFactory comboBoxListenerFactory = new ComboBoxListenerFactory(this);
	
	public LanternaController(Game game) {
		this.game = game;
		this.player = game.getPlayer();
		this.bank = game.getBank();
		this.loanShark = game.getLoanShark();
	}
	
	public Game getGame() {
		return game;
	}
	
	public EventFactory getEventFactory() {
		return eventFactory;
	}
	
	/**
	 * Creates the matching input filter for the event.
	 * @param eventName the name of the corresponding event.
	 * @param associatedComboBox the content of this combo box can be used.
	 * @param nextInFocus the Interactable that gets the focus after a valid input.
	 * @param answerBox the Label that shows the answer.
	 * @return lanterna.gui2.InputFilter
	 */
	public InputFilter createInputFilter(String eventName, ComboBox<String> associatedComboBox, Interactable nextInFocus, Label answerBox) {
		return inputFilterFactory.createInputFilter(eventName, associatedComboBox, nextInFocus, answerBox);
	}
	
	/**
	 * Creates the matching combo box listener for the event. 
	 * @param thisComboBox the combo box to which the listener is added.
	 * @param eventName the name of the corresponding event.
	 * @param nextInFocus the Interactable that gets the focus after the selection has been changed.
	 * @param answerBox the Label that shows the answer.
	 * @return ComboBox.Listener
	 */
	public ComboBox.Listener createComboBoxListener(ComboBox<String> thisComboBox, String eventName, Interactable nextInFocus, Label... answerBox) {
		return comboBoxListenerFactory.createComboBoxListener(thisComboBox, eventName, nextInFocus, answerBox);
	}
	
	/**
	 * Creates the matching button listener for the event.
	 * @param eventName the name of the corresponding event.
	 * @param associatedComboBox the content of this combo box can be used.
	 * @param associatedTextBox the content of this text box can be used.
	 * @param nextInFocus the Interactable that gets the focus after the button has been triggered.
	 * @param answerBox the Label that shows the answer.
	 * @return Button.Listener
	 */
	public Button.Listener createButtonListener(String eventName, ComboBox<String> associatedComboBox, TextBox associatedTextBox, Interactable nextInFocus, Label... answerBox) {
		return buttonListenerFactory.createButtonListener(eventName, associatedComboBox, associatedTextBox, nextInFocus, answerBox);
	}
	
	public String formatBalanceSheet() {
		double cash = player.getCash();
		double loan = loanShark.getBalance(player);
		double balance = bank.getBalance(player);
		StringBuffer answer = new StringBuffer();
		answer.append(String.format("Cash: %,.2f", cash))
			.append(String.format(" | Kredithai: %,.2f", loan))
			.append(String.format(" | Bankkonto: %,.2f", balance))
			.append(String.format("\nSaldo: %,.2f", cash + loan + balance));
		return answer.toString();
	}
	
	public ArrayList<String> formatDefaultCandies() {
	    ArrayList<? extends Snackable> candies = new CandyFactory().getDefaultSnacks();
	    candies.sort(Comparator.comparing(Snackable::getName)); //String implements Comparable
    	ArrayList<String> formattedList = new ArrayList<>();
	    for(Snackable s : candies)
	    	formattedList.add(String.format("%s - %.2f %s", s.getName(), s.getStaticPrice(), MoneyDealer.getCurrency()));
	    return formattedList;
	}
	
	public String formatMoney(double money) {
	   return String.format("%,.2f %s", money, MoneyDealer.getCurrency());
    }

	public ArrayList<String> formatSnacks(ArrayList<? extends Snackable> snacks){
		snacks.sort(Comparator.comparing(Snackable::getName));
		ArrayList<String> formattedList = new ArrayList<>();
		if(snacks.isEmpty()) {
			formattedList.add("Nix drin!");
			return formattedList;
		}
		for(Snackable s : snacks)
			formattedList.add(String.format("%d | %s", s.getQuantity(), s.getName()));
		return formattedList;
	}
}
