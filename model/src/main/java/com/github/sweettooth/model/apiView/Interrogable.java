package com.github.sweettooth.model.apiView;

public interface Interrogable {
	public abstract String getInterestHint();
	public abstract String getDispoHint();
	public abstract double getClientsBalance(Playable player);
}
