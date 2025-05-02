package com.github.SweetTooth.model.characters;

import com.github.SweetTooth.model.locations.Location;

non-sealed public class LoanShark extends MoneyDealer {
	final static double INTEREST_DEBT_PERCENT = Double.valueOf(10);
	
	public LoanShark() {
		super(Location.BRONX);
	}
	
	@Override
	public Double applyInterestToBalance(Player player) {
		Client client = findClientByIdentity((Player)player);
		if(client != null) {
			double interest = client.getBalance() * INTEREST_DEBT_PERCENT / 100;
			client.addAmount(interest);
			return interest;
		}
		return 0.0;
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
	public double getBalance(Player player) {
		Client loanSharkClient = findClientByIdentity((Player)player);
		if(loanSharkClient == null)
			return 0.0;
		return loanSharkClient.getBalance();
	}

	@Override
	public String getDispoHint() {
		return "";
	}

	@Override
	public String getInterestHint() {
		return String.format("Ich will %.1f%% am Tag!", INTEREST_DEBT_PERCENT);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public void increaseClientsBalance(Player player, double amount) {
		Client client = findClientByIdentity((Player)player);
		if(client != null)
			client.addAmount(amount);
	}

	@Override
	public void reduceClientsBalance(Player player, double amount) {
		getExistingOrNewClient((Player)player).removeAmount(amount);
	}

	@Override
	public String toString() {
		StringBuffer builder = new StringBuffer();
		builder.append("LoanShark [clients=").append(clients)
			.append(", hometown=").append(hometown)
			.append(", location=").append(location)
			.append("]");
		return builder.toString();
	}
}
