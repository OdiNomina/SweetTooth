package com.github.sweettooth.model.characters;

import com.github.sweettooth.model.api.Playable;
import com.github.sweettooth.model.api.Settings;
import com.github.sweettooth.model.commons.InternSettings;
import com.github.sweettooth.model.locations.Location;

non-sealed public class Bank extends MoneyDealer {
	public Bank(Settings settings) {
		super(Location.BRONX, settings);
	}
	
	@Override
	public Double applyInterestToBalance(Player player) {
		Client bankClient = findClientByIdentity(player);
		double interest = 0;
		if(bankClient != null) {
			if(bankClient.getBalance() < 0)
				interest = bankClient.getBalance() * InternSettings.INTEREST_DEBT_PERCENT / 100;
			else
				interest = bankClient.getBalance() * InternSettings.INTEREST_CREDIT_PERCENT / 100;
			bankClient.addAmount(interest);
		}
		return interest;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if (!super.equals(obj))
			return false;
		return true;
	}
	
	@Override
	public double getClientsBalance(Playable player) {
		Client bankClient = findClientByIdentity(player);
		if(bankClient == null)
			return 0.0;
		return bankClient.getBalance();
	}

	@Override
	public String getDispoHint() {
		return String.format(settings.getLocale(), "Kredit-Rahmen: %.2f %s (mehr gibts nicht)", InternSettings.BANK_MIN_BALANCE, settings.getCurrency());
	}
	
	@Override
	public String getInterestHint() {
		return String.format(settings.getLocale(), "Kredit Zinsen: -%.1f %% pro Tag%nGuthaben Zinsen:  +%.1f %% pro Tag", InternSettings.INTEREST_DEBT_PERCENT, InternSettings.INTEREST_CREDIT_PERCENT);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public void increaseClientsBalance(Player player, double amount) {
		getExistingOrNewClient(player).addAmount(amount);
	}
	
	@Override
	public void reduceClientsBalance(Player player, double amount) {
		getExistingOrNewClient(player).removeAmount(amount);
	}

	@Override
	public String toString() {
		StringBuffer builder = new StringBuffer();
		builder.append("Bank [clients=").append(clients)
			.append(", location=").append(location)
			.append("]");
		return builder.toString();
	}
}