package com.github.sweettooth.model.characters;

import com.github.sweettooth.model.api.GameSettings;
import com.github.sweettooth.model.commons.InternSettings;

non-sealed public class Bank extends MoneyDealer {
	
	public Bank(GameSettings modelSettings) {
		super(InternSettings.HOMETOWN, modelSettings);
	}
	
	@Override
	public Double applyInterestToBalance(Player player) {
		Client bankClient = getExistingOrNewClient(player);
		double interest = 0;
		
		if(bankClient.getBalance() < 0)
			interest = bankClient.getBalance() * InternSettings.BANK_INTEREST_DEBT_PERCENT / 100;
		else
			interest = bankClient.getBalance() * InternSettings.BANK_INTEREST_CREDIT_PERCENT / 100;
		
		bankClient.addAmount(interest);
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
	public double getClientsBalance(Player player) {
		Client bankClient = getExistingOrNewClient(player);
		return bankClient.getBalance();
	}

	@Override
	public String getDispoHint() {
		return String.format(modelSettings.getLocale(), "Kredit-Rahmen: %.2f %s (mehr gibts nicht)", InternSettings.BANK_MIN_BALANCE, modelSettings.getCurrency());
	}
	
	@Override
	public String getInterestHint() {
		return String.format(modelSettings.getLocale(), "Kredit Zinsen: -%.1f %% pro Tag%nGuthaben Zinsen:  +%.1f %% pro Tag", InternSettings.BANK_INTEREST_DEBT_PERCENT, InternSettings.BANK_INTEREST_CREDIT_PERCENT);
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