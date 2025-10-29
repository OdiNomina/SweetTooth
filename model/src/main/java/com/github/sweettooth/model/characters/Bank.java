package com.github.sweettooth.model.characters;

import java.util.Currency;
import java.util.Locale;

import com.github.sweettooth.model.commons.GlobalSettings;
import com.github.sweettooth.model.commons.InternSettings;

non-sealed public class Bank extends MoneyDealer {
	
	public Bank(GlobalSettings globalSettings) {
		super(InternSettings.HOMETOWN, globalSettings);
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
		Locale locale = globalSettings.getLocale();
		return String.format(locale, "Kredit-Rahmen: %.2f %s (mehr gibts nicht)", InternSettings.BANK_MIN_BALANCE, Currency.getInstance(locale).getSymbol());
	}
	
	@Override
	public String getCreditInterestHint() {
		return String.format(globalSettings.getLocale(), "Guthaben Zinsen:  +%.1f %% pro Tag", InternSettings.BANK_INTEREST_CREDIT_PERCENT);
	}

	@Override
	public String getDebitInterestHint() {
		return String.format(globalSettings.getLocale(), "Kredit Zinsen: -%.1f %% pro Tag", InternSettings.BANK_INTEREST_DEBT_PERCENT);
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