package com.github.sweettooth.model.api.viewAPI;

import com.github.sweettooth.model.characters.MoneyDealer;
import com.github.sweettooth.model.characters.Player;

public interface IMoneyDealer {
	public default double clientsBalance(IPlayer player) {
		return ((MoneyDealer)this).getClientsBalance((Player)player);
	}
	
	public abstract String getInterestHint();
	public abstract String getDispoHint();
}
