package com.github.sweettooth.model.api;

public interface Interrogable {
	// --- view
	
	public abstract String getInterestHint();
	public abstract String getDispoHint();
	public abstract double getClientsBalance(Playable player);
}
