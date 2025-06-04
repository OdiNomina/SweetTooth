package com.github.sweettooth.model.apiView;

public interface FinanciallyInteractable {
	public abstract String getInterestHint();
	public abstract String getDispoHint();
	public abstract double getClientsBalance(Playable player);
}
