package com.github.sweettooth.viewSwing.commons;

import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Currency;
import java.util.IllegalFormatException;
import java.util.Locale;

import javax.swing.AbstractButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.github.sweettooth.model.api.IGameRound;
import com.github.sweettooth.model.api.gameSession.IGameSession;
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
	public static ArrayList<String> formatDefaultSnacks(IGlobalSettings globalSettings, IGameSettings gamesettings) throws NullPointerException, IllegalArgumentException, java.util.IllegalFormatException {
		ArrayList<? extends Snackable> candies = gamesettings.getSnackFactory().getDefaultSnacks();
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
	public static String formatBalanceSheet(IGameSession sessionData, IGameRound gameData) {
    	double cash = sessionData.getPlayer().getCash();
		double loan = gameData.getLoanShark().getClientsBalance(sessionData.getPlayer());
		double balance = gameData.getBank().getClientsBalance(sessionData.getPlayer());
		Locale locale = sessionData.getGlobalSettings().getLocale();
		String currency = Currency.getInstance(locale).getSymbol();
		StringBuffer answer = new StringBuffer();
		answer.append(String.format(locale, "Cash: %,.2f %s", cash, currency))
		.append(String.format(locale, "\tKredithai: %,.2f %s", loan, currency))
		.append(String.format(locale, "\tBankkonto: %,.2f %s", balance, currency))
		.append(String.format(locale, "\t=>      SALDO: %,.2f %s", cash + loan + balance, currency));
		return answer.toString();
	}
	
	public static void removeAllActionListeners(AbstractButton button) {
		for (ActionListener al : button.getActionListeners())
	        button.removeActionListener(al);
	}

	public static void removeAllActionListeners(JComboBox<?> comboBox) {
		for (ActionListener al : comboBox.getActionListeners())
	        comboBox.removeActionListener(al);
	}

	public static void removeAllActionListeners(JTextField textField) {
		for (ActionListener al : textField.getActionListeners())
	        textField.removeActionListener(al);
	}
	
	public static void runOnEDT(Runnable r) {
        if (SwingUtilities.isEventDispatchThread()) {
            r.run();
        } else {
            SwingUtilities.invokeLater(r);
        }
    }
	
	public static void runAndWaitOnEDT(Runnable r) {
	    if (SwingUtilities.isEventDispatchThread()) {
	        r.run();
	    } else {
	        try {
	            SwingUtilities.invokeAndWait(r);
	        } catch (InterruptedException | InvocationTargetException ex) {
	            ex.printStackTrace();
	        }
	    }
	}
}
