package com.github.SweetTooth.characters;

import com.github.SweetTooth.locations.Location;

non-sealed class LoanShark extends MoneyDealer implements IMoneyDealer {
	final static double INTEREST_DEBT_PERCENT = Double.valueOf(10);
	private static LoanShark uniqueInstance;
	
	private LoanShark() {
		super(Location.BRONX);
	}
	
	static LoanShark getInstance() {
		if(uniqueInstance == null)
			uniqueInstance = new LoanShark();
		return uniqueInstance;
	}
	
	@Override
	public Double applyInterestToBalance(Playable player) {
		Client client = findClientByIdentity((Player)player);
		if(client != null) {
			double interest = client.getBalance() * INTEREST_DEBT_PERCENT / 100;
			client.addAmount(interest);
			return interest;
		}
		return 0.0;
	}

	@Override
	public String getInterestHint() {
		return String.format("Ich will %.1f%% am Tag!", INTEREST_DEBT_PERCENT);
	}

	@Override
	public double getBalance(Playable player) {
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
	public void increaseClientsBalance(Playable player, double amount) {
		Client client = findClientByIdentity((Player)player);
		if(client != null)
			client.addAmount(amount);
	}

	@Override
	public void reduceClientsBalance(Playable player, double amount) {
		getExistingOrNewClient((Player)player).removeAmount(amount);
	}
}
