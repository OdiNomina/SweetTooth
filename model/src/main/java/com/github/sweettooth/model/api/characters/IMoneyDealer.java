package com.github.sweettooth.model.api.characters;

import com.github.sweettooth.model.characters.MoneyDealer;
import com.github.sweettooth.model.characters.Player;

public interface IMoneyDealer {
	public default double clientsBalance(IPlayer player) {
		return ((MoneyDealer)this).getClientsBalance((Player)player);
	}
	
	public abstract String getCreditInterestHint();
	public abstract String getDebitInterestHint();
	public abstract String getDispoHint();
}
