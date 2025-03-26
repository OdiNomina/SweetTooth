package com.github.SweetTooth.events;

import java.io.IOException;
import java.util.ArrayList;

import com.github.SweetTooth.characters.IPlayer;
import com.github.SweetTooth.gui.GUI;
import com.github.SweetTooth.snacks.Snackable;

abstract sealed class Event implements IEvent permits 
	ApplyInterest, Buy, Deposit, GiveMoneyBack, Hide, Lend, Seek, Sell, Travel, Withdraw
{
	static int dayOfGame = Integer.valueOf(1);
	double doubleInput;
	int integerInput;
	String notAtHometown = "Du bist nicht in deiner Heimatstadt.";
	IPlayer player;
	String stringInput;
	
	static void increaseDayOfGame(int numberOfDays, GUI gui) throws IOException {
		if(dayOfGame < 30)
			dayOfGame += numberOfDays;
		else
			gui.disableComponents();
	}
	
	@Override
	public String handleEvent() {
		throw new UnsupportedOperationException("Subclasses have to override this operation.");
	}
	
	@Override
	public Answer handleEventMultipleAnswers() {
		throw new UnsupportedOperationException("Subclasses have to override this operation.");
	}
	
	boolean hasSpaceInPockets(int quantity){
		int sumCandies = 0;
		ArrayList<? extends Snackable> candies =  player.getCandies();
		for(int i = 0; i < candies.size(); i++)
			sumCandies += candies.get(i).getQuantity();
		return sumCandies + quantity <= IPlayer.getMaxSnacks();
	}
	
	boolean isAtHometown() {
		return player.getLocation() == player.getHometown();
	}

	@Override
	public void setDoubleInput(double input) {
		doubleInput = input;
	}

	@Override
	public void setIntegerInput(int input) {
		integerInput = input;
	}
	
	@Override
	public void setStringInput(String input) {
		int separatorIdx = input.indexOf("-");
		if(separatorIdx > -1)
			stringInput = input.substring(0, separatorIdx).strip();
		else
			stringInput =  input.strip();
	}
}