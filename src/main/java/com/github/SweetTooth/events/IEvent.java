package com.github.SweetTooth.events;

public sealed interface IEvent permits 
	ApplyInterest, Buy, Deposit, Event, GiveMoneyBack, Hide, Lend, Seek, Sell, Travel, Withdraw
{	
	String handleEvent();
	Answer handleEventMultipleAnswers();
	void setDoubleInput(double input);
	void setIntegerInput(int input);
	void setStringInput(String input);
	
	public record Answer(String first, String second, String third) {}
}
