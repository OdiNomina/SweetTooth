package com.github.SweetTooth.characters;

import com.github.SweetTooth.locations.Location;

non-sealed public class Bank extends MoneyDealer {
	final static double INTEREST_CREDIT_PERCENT = Double.valueOf(2);
	final static double INTEREST_DEBT_PERCENT = Double.valueOf(5);
	final static double MIN_BALANCE = Double.valueOf(-100);
	
	public Bank() {
		super(Location.BRONX);
	}
	
	@Override
	public Double applyInterestToBalance(IPlayer player) {
		Client bankClient = findClientByIdentity((Player)player);
		double interest = 0;
		if(bankClient != null) {
			if(bankClient.getBalance() < 0)
				interest = bankClient.getBalance() * INTEREST_DEBT_PERCENT / 100;
			else
				interest = bankClient.getBalance() * INTEREST_CREDIT_PERCENT / 100;
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
	public double getBalance(IPlayer player) {
		Client bankClient = findClientByIdentity((Player) player);
		if(bankClient == null)
			return 0.0;
		return bankClient.getBalance();
	}

	@Override
	public String getDispoHint() {
		return String.format("Kredit-Rahmen: %.2f%s (Mehr gibts nicht.)", MIN_BALANCE, Bank.CURRENCY);
	}
	
	@Override
	public String getInterestHint() {
		return String.format("Kredit Zinsen: -%.1f%% pro Tag.%nGuthaben Zinsen:  +%.1f%% pro Tag.", Bank.INTEREST_DEBT_PERCENT, Bank.INTEREST_CREDIT_PERCENT);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public void increaseClientsBalance(IPlayer player, double amount) {
		getExistingOrNewClient((Player)player).addAmount(amount);
	}
	
	@Override
	public void reduceClientsBalance(IPlayer player, double amount) {
		getExistingOrNewClient((Player)player).removeAmount(amount);
	}

	@Override
	public String toString() {
		StringBuffer builder = new StringBuffer();
		builder.append("Bank [clients=").append(clients)
			.append(", hometown=").append(hometown)
			.append(", location=").append(location)
			.append("]");
		return builder.toString();
	}
}
