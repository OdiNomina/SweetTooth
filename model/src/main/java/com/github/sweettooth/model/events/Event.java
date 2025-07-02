package com.github.sweettooth.model.events;

import com.github.sweettooth.model.api.ModelSettings;
import com.github.sweettooth.model.api.controllerAPI.Processable;
import com.github.sweettooth.model.characters.MoneyDealer;
import com.github.sweettooth.model.characters.Player;
import com.github.sweettooth.model.games.GameData;

public abstract sealed class Event implements Processable permits 
	ApplyInterest, Buy, Deposit, Exit, GiveMoneyBack, Hide, Lend, Seek, Sell, Travel, Withdraw //Update factory!
{
	ModelSettings modelSettings;
	Player player;
	MoneyDealer bank;
	MoneyDealer loanShark;
	String notAtHometown;
	
	public Event(GameData gameData) {
		modelSettings = gameData.getSettings();
		player = (Player)gameData.getPlayer();
		bank = (MoneyDealer)gameData.getBank();
		loanShark = (MoneyDealer)gameData.getLoanShark();
		
		notAtHometown = "Du bist nicht in deiner Heimatstadt.";
	}
	
	boolean isAtHometown() {
		return player.getLocation() == player.getHometown();
	}

	String splitStringInput(String input) {
		int separatorIdx = input.indexOf("-");
		if(separatorIdx > -1)
			return input.substring(0, separatorIdx).strip();
		else {
			separatorIdx = input.indexOf("|");
			if(separatorIdx > -1)
				return input.substring(separatorIdx + 1, input.length()).strip();
			else return input;
		}
	}
}