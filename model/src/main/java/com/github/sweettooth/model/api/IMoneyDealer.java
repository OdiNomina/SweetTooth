package com.github.sweettooth.model.api;

public interface IMoneyDealer {
	// --- view
	
	public abstract String getInterestHint();
	public abstract String getDispoHint();
	public abstract double getClientsBalance(IPlayer player);
}
