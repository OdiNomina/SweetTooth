package com.github.sweettooth.model.characters;

import com.github.sweettooth.model.api.Playable;
import com.github.sweettooth.model.api.Settings;
import com.github.sweettooth.model.locations.Location;

non-sealed public class Bank extends MoneyDealer {
	public Bank() {
		super(Location.BRONX);
	}
	
	@Override
	public Double applyInterestToBalance(Player player) {
		Client bankClient = findClientByIdentity(player);
		double interest = 0;
		if(bankClient != null) {
			if(bankClient.getBalance() < 0)
				interest = bankClient.getBalance() * Settings.INTEREST_DEBT_PERCENT / 100;
			else
				interest = bankClient.getBalance() *Settings.INTEREST_CREDIT_PERCENT / 100;
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
		return String.format("Kredit-Rahmen: %.2f%s (Mehr gibts nicht.)", Settings.BANK_MIN_BALANCE, Settings.CURRENCY);
	}
	
	@Override
	public String getInterestHint() {
		return String.format("Kredit Zinsen: -%.1f%% pro Tag.%nGuthaben Zinsen:  +%.1f%% pro Tag.", Settings.INTEREST_DEBT_PERCENT, Settings.INTEREST_CREDIT_PERCENT);
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
			.append(", location=").append(this.getLocation())
			.append("]");
		return builder.toString();
	}
}