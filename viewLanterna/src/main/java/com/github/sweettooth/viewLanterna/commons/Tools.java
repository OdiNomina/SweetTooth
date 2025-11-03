package com.github.sweettooth.viewLanterna.commons;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Currency;
import java.util.IllegalFormatException;
import java.util.Locale;

import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.characters.IPlayer;
import com.github.sweettooth.model.api.gameSession.ISessionData;
import com.github.sweettooth.model.api.settings.IGameSettings;
import com.github.sweettooth.model.api.settings.IGlobalSettings;
import com.github.sweettooth.model.api.snacks.Snackable;

public class Tools {
	public static String formatMoney(IGlobalSettings globalSettings, double money) throws IllegalFormatException, NullPointerException, IllegalArgumentException {
		Locale locale = globalSettings.getLocale();
		return String.format(locale, "%,.2f %s", money, Currency.getInstance(locale).getSymbol());
    }

	/**
     * Converts the list entries to formatted strings.
     * @return A String list with formatted entries or null if an error occurred.
     */
	public static ArrayList<String> formatSnacks(IGlobalSettings globalSettings, ArrayList<? extends Snackable> snacks) throws NullPointerException, java.util.IllegalFormatException {
		snacks.sort(Comparator.comparing(Snackable::getName));
		ArrayList<String> formattedList = new ArrayList<>();
		if(snacks.isEmpty()) {
			formattedList.add("Nix drin!");
			return formattedList;
		}
		Locale locale = globalSettings.getLocale();
		for(Snackable s : snacks)
			formattedList.add(String.format(locale, "%,d | %s", s.getQuantity(), s.getName()));
		return formattedList;
	}
	
	/**
     * Retrieves a list of standard snacks and converts their entries to formatted strings.
     * @return A String list with formatted entries or null if an error occurred.
     */
	public static ArrayList<String> formatDefaultSnacks(IGlobalSettings globalSettings, IGameSettings gameSettings) throws NullPointerException, IllegalArgumentException, java.util.IllegalFormatException {
		ArrayList<? extends Snackable> candies = gameSettings.getSnackFactory().getDefaultSnacks();
	    candies.sort(Comparator.comparing(Snackable::getName)); //String implements Comparable
    	ArrayList<String> formattedList = new ArrayList<>();
    	Locale locale = globalSettings.getLocale();
    	String currency = Currency.getInstance(locale).getSymbol();
    	for(Snackable s : candies)
	    	formattedList.add(String.format(locale, "%s - %.2f %s", s.getName(), s.getPrice(), currency));
	    return formattedList;
	}
	
	/*
	 * %[flags][.precision]conversion
	 * Flag ',': The result will include locale-specific grouping separators.
	 * Conversion 'f': The result is formatted as a decimal number.
	 */
	public static String formatBalanceSheet(ISessionData sessionData, IGameData gameData) {
    	IPlayer player = sessionData.getPlayer();
    	double cash = player.getCash();
		double loan = gameData.loanShark().getClientsBalance(player);
		double balance = gameData.bank().getClientsBalance(player);
		Locale locale = sessionData.getGlobalSettings().getLocale();
		String currency = Currency.getInstance(locale).getSymbol();
		StringBuffer answer = new StringBuffer();
		answer.append(String.format(locale, "Cash: %,.2f %s", cash, currency))
		.append(String.format(locale, "\tKredithai: %,.2f %s", loan, currency))
		.append(String.format(locale, "\tBankkonto: %,.2f %s", balance, currency))
		.append(String.format(locale, "\t=>      SALDO: %,.2f %s", cash + loan + balance, currency));
		return answer.toString();
	}
}
