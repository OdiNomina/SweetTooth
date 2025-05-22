package com.github.SweetTooth.model.events;

import java.util.ArrayList;

import com.github.SweetTooth.model.characters.Player;
import com.github.SweetTooth.model.games.Game;
import com.github.SweetTooth.model.snacks.Snackable;

public abstract sealed class Event permits 
	ApplyInterest, Buy, Deposit, Exit, GiveMoneyBack, Hide, Lend, Seek, Sell, Travel, Withdraw //Update factory!
{
	//This nested record is used as a response container (... instead of an array).
	public record Answer(String answer1, String answer2, String answer3) {}
	
	Game game;
	String notAtHometown = "Du bist nicht in deiner Heimatstadt.";
	
	Event(){}
	
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

	public abstract String handle(String stringInput, Integer integerInput, Double doubleInput);
	public abstract Answer handleMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput);
}