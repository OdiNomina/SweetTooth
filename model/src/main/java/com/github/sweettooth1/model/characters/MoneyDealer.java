package com.github.sweettooth.model.characters;

import java.util.ArrayList;
import java.util.Objects;

import com.github.sweettooth.model.locations.Location;

public abstract sealed class MoneyDealer extends Character permits Bank, LoanShark
{
	public final static String CURRENCY = "€";
	
	public static double getBankMinBalance() {
		return Bank.MIN_BALANCE;
	}

	public static String getCurrency() {
		return CURRENCY;
	}
	
	final ArrayList<Client> clients = new ArrayList<>();
	
	MoneyDealer(Location hometown){
		super(hometown);
	}
	
	Client findClientByIdentity(Player player) {
		for(Client c : clients) {
			if(c.identity == player)
				return c;
		}
		return null;
	}
	
	Client getExistingOrNewClient(Player player) {
		Client client = findClientByIdentity(player);
		if(client == null) {
			client = new Client(player);
			clients.add(client);
		}
		return client;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(clients);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;
		MoneyDealer other = (MoneyDealer) obj;
		return Objects.equals(clients, other.clients);
	}
	
	abstract public Double applyInterestToBalance(Player player);
	abstract public double getBalance(Player player);
	abstract public String getDispoHint();
	abstract public String getInterestHint();
	abstract public void increaseClientsBalance(Player player, double amount);
	abstract public void reduceClientsBalance(Player player, double amount);
}
