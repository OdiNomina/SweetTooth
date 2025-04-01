package com.github.SweetTooth.characters;

public sealed interface IMoneyDealer 
	permits Bank, LoanShark, MoneyDealer 
{	
	public static double getBankMinBalance() {
		return Bank.MIN_BALANCE;
	}

	public static String getCurrency() {
		return MoneyDealer.CURRENCY;
	}
	
	Double applyInterestToBalance(IPlayer player);
	double getBalance(IPlayer player);
	String getDispoHint();
	String getInterestHint();
	void increaseClientsBalance(IPlayer player, double amount);
	void reduceClientsBalance(IPlayer player, double amount);
}
