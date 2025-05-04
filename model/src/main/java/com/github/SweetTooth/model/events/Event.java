package com.github.SweetTooth.model.events;

import java.util.ArrayList;

import com.github.SweetTooth.model.characters.Player;
import com.github.SweetTooth.model.games.Game;
import com.github.SweetTooth.model.snacks.Snackable;

public abstract sealed class Event implements Handleable permits 
	ApplyInterest, Buy, Deposit, GiveMoneyBack, Hide, Lend, Seek, Sell, Travel, Withdraw
{
	Game game;
	String notAtHometown = "Du bist nicht in deiner Heimatstadt.";
	
	boolean isTooMuchToCarry(int quantity){
		int sumInPockets = 0;
		ArrayList<? extends Snackable> candies = game.getPlayer().getCandies();
		for(int i = 0; i < candies.size(); i++)
			sumInPockets += candies.get(i).getQuantity();
		return sumInPockets + quantity > Player.getMaxSnacks();
	}
	
	boolean isAtHometown() {
		return game.getPlayer().getLocation() == game.getPlayer().getHometown();
	}

	public String splitStringInput(String input) {
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