package com.github.sweettooth.model.api.characters;

public interface IMoneyDealer {
	double getClientsBalance(IPlayer player);
	String getHintCreditInterest();
	String getHintDebitInterest();
	String getHintDispo();
}
