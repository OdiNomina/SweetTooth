package com.github.sweettooth.model.characters;

import com.github.sweettooth.model.api.ModelSettings;
import com.github.sweettooth.model.commons.InternSettings;

non-sealed public class LoanShark extends MoneyDealer {
	public LoanShark(ModelSettings modelSettings) {
		super(InternSettings.HOMETOWN, modelSettings);
	}
	
	@Override
	public Double applyInterestToBalance(Player player) {
		Client client = getExistingOrNewClient(player);
		double interest = client.getBalance() * InternSettings.LOANSHARK_INTEREST_DEBT_PERCENT / 100;
		client.addAmount(interest);
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
		return getExistingOrNewClient(player).getBalance();
	}

	@Override
	public String getDispoHint() {
		return "";
	}

	@Override
	public String getInterestHint() {
		return String.format(modelSettings.getLocale(), "Ich will %.1f %% pro Tag!", InternSettings.LOANSHARK_INTEREST_DEBT_PERCENT);
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
		builder.append("LoanShark [clients=").append(clients)
			.append(", location=").append(location)
			.append("]");
		return builder.toString();
	}
}