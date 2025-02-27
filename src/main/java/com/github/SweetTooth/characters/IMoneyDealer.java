package com.github.SweetTooth.characters;

public sealed interface IMoneyDealer 
	permits Bank, LoanShark, MoneyDealer 
{
	public static IMoneyDealer create(String character) {
		return MoneyDealer.create(character);
	}
	
	public static double getBankMinBalance() {
		return Bank.MIN_BALANCE;
	}

	public static String getCurrency() {
		return MoneyDealer.CURRENCY;
	}
	
	Double applyInterestToBalance(Playable player);
	double getBalance(Playable player);
	String getDispoHint();
	String getInterestHint();
	void increaseClientsBalance(Playable player, double amount);
	void reduceClientsBalance(Playable player, double amount);
}
