package com.github.sweettooth.view.commons;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Currency;
import java.util.IllegalFormatException;
import java.util.Locale;

import com.github.sweettooth.model.api.GameSettings;
import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.viewAPI.IPlayer;
import com.github.sweettooth.model.api.viewAPI.Snackable;

public class Tools {
	public static String formatMoney(GameSettings gameSettings, double money) throws IllegalFormatException, NullPointerException, IllegalArgumentException {
		Locale locale = gameSettings.getLocale();
		return String.format(locale, "%,.2f %s", money, Currency.getInstance(locale).getSymbol());
    }

	/**
     * Converts the list entries to formatted strings.
     * @return A String list with formatted entries or null if an error occurred.
     */
	public static ArrayList<String> formatSnacks(GameSettings gameSettings, ArrayList<? extends Snackable> snacks) throws NullPointerException, java.util.IllegalFormatException {
		snacks.sort(Comparator.comparing(Snackable::name));
		ArrayList<String> formattedList = new ArrayList<>();
		if(snacks.isEmpty()) {
			formattedList.add("Nix drin!");
			return formattedList;
		}
		Locale locale = gameSettings.getLocale();
		for(Snackable s : snacks)
			formattedList.add(String.format(locale, "%,d | %s", s.quantity(), s.name()));
		return formattedList;
	}
	
	/**
     * Retrieves a list of standard snacks and converts their entries to formatted strings.
     * @return A String list with formatted entries or null if an error occurred.
     */
	public static ArrayList<String> formatDefaultSnacks(GameSettings gameSettings) throws NullPointerException, IllegalArgumentException, java.util.IllegalFormatException {
		ArrayList<? extends Snackable> candies = gameSettings.getSnackFactory().defaultSnacks();
	    candies.sort(Comparator.comparing(Snackable::name)); //String implements Comparable
    	ArrayList<String> formattedList = new ArrayList<>();
    	Locale locale = gameSettings.getLocale();
    	String currency = Currency.getInstance(locale).getSymbol();
    	for(Snackable s : candies)
	    	formattedList.add(String.format(locale, "%s - %.2f %s", s.name(), s.staticPrice(), currency));
	    return formattedList;
	}
	
	/*
	 * %[flags][.precision]conversion
	 * Flag ',': The result will include locale-specific grouping separators.
	 * Conversion 'f': The result is formatted as a decimal number.
	 */
	public static String formatBalanceSheet(GameSettings gameSettings, IGameData gameData) {
    	IPlayer player = gameData.player();
    	double cash = player.cash();
		double loan = gameData.loanShark().clientsBalance(player);
		double balance = gameData.bank().clientsBalance(player);
		Locale locale = gameSettings.getLocale();
		String currency = Currency.getInstance(locale).getSymbol();
		StringBuffer answer = new StringBuffer();
		answer.append(String.format(locale, "Cash: %,.2f %s", cash, currency))
		.append(String.format(locale, " | Kredithai: %,.2f %s", loan, currency))
		.append(String.format(locale, " | Bankkonto: %,.2f %s", balance, currency))
		.append(String.format(locale, "\nSaldo: %,.2f %s", cash + loan + balance, currency));
		return answer.toString();
	}
}
