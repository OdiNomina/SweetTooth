package com.github.sweettooth.model.events;

import java.util.logging.Logger;

import com.github.sweettooth.model.api.GameSettings;
import com.github.sweettooth.model.api.controllerAPI.Processable;
import com.github.sweettooth.model.characters.MoneyDealer;
import com.github.sweettooth.model.characters.Player;
import com.github.sweettooth.model.games.GameData;
import com.github.sweettooth.model.session.SessionData;
import com.github.sweettooth.shared.logging.Loggable;

public abstract sealed class Event implements Processable, Loggable permits 
	ApplyInterest, Buy, Deposit, Exit, GiveMoneyBack, Hide, Lend, Seek, Sell, Travel, Withdraw //Update factory!
{
	final Logger logger;
	GameSettings settings;
	Player player;
	MoneyDealer bank;
	MoneyDealer loanShark;
	String notAtHometown;
	
	
	public Event(SessionData sessionData, GameData gameData) {
		logger = Logger.getLogger(Event.class.getName());
		settings = sessionData.getSettings();
		player = sessionData.getPlayer();
		bank = gameData.getBank();
		loanShark = gameData.getLoanShark();
		
		notAtHometown = "Du bist nicht in deiner Heimatstadt.";
	}
	
	@Override
	public Logger getLogger() {
		return logger;
	}
	
	boolean isAtHometown() {
		return player.getLocation() == player.getHometown();
	}
	
	/**
	 * The Lanterna view displays combo box items in combination with their quantities, separated by "-" or "|".
	 * @param input
	 * @return Returns a string whose value is this string, with all leading and trailing white space removed.
	 * If the string contains "-" or "|" then a substring containing the name of the snack will be returned.
	 */
	String clearStringInput(String input) {
		if(input.contains("-") || input.contains("|")) {
			int separatorIdx = input.indexOf("-");
			if(separatorIdx > -1)
				return input.substring(0, separatorIdx).strip();
			else {
				separatorIdx = input.indexOf("|");
				if(separatorIdx > -1)
					return input.substring(separatorIdx + 1, input.length()).strip();
				else return input.strip();
			}
		}
		return input.strip();
	}
}