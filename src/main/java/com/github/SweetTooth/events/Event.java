package com.github.SweetTooth.events;

import java.util.ArrayList;

import com.github.SweetTooth.characters.Player;
import com.github.SweetTooth.game.Game;
import com.github.SweetTooth.snacks.Snackable;

public abstract sealed class Event implements Handleable permits 
	ApplyInterest, Buy, Deposit, GiveMoneyBack, Hide, Lend, Seek, Sell, Travel, Withdraw
{
	double doubleInput;
	int integerInput;
	String stringInput;
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

	public void setDoubleInput(double input) {
		doubleInput = input;
	}

	public void setIntegerInput(int input) {
		integerInput = input;
	}
	
	public void setStringInput(String input) {
		int separatorIdx = input.indexOf("-");
		if(separatorIdx > -1)
			stringInput = input.substring(0, separatorIdx).strip();
		else {
			separatorIdx = input.indexOf("|");
			if(separatorIdx > -1)
				stringInput = input.substring(separatorIdx + 1, input.length()).strip();
			else stringInput = input;
		}
	}
}