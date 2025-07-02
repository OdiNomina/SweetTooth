package com.github.sweettooth.model.api.viewAPI;

public interface IMoneyDealer {
	// --- view
	
	public abstract String getInterestHint();
	public abstract String getDispoHint();
	
	// --- model and view
	
	public abstract double getClientsBalance(IPlayer player);
}
