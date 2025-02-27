package com.github.SweetTooth.events;

import java.io.IOException;

import com.github.SweetTooth.gui.GUI;

public sealed interface IEvent permits 
	ApplyInterest, Buy, Deposit, Event, GiveMoneyBack, Hide, Lend, Seek, Sell, Travel, Withdraw
{	
	public static int getDayOfGame() {
		return Event.dayOfGame;
	}
	
	static void increaseDayOfGame(int numberOfDays, GUI gui) throws IOException {
		Event.increaseDayOfGame(numberOfDays, gui);
	}
	
	String handleEvent();
	Answer handleEventMultipleAnswers();
	void setDoubleInput(double input);
	void setIntegerInput(int input);
	void setStringInput(String input);
	
	public record Answer(String first, String second, String third) {}
}
