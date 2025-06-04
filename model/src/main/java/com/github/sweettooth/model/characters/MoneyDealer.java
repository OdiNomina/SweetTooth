package com.github.sweettooth.model.characters;

import java.util.ArrayList;
import java.util.Objects;

import com.github.sweettooth.model.apiView.FinanciallyInteractable;
import com.github.sweettooth.model.apiView.Playable;
import com.github.sweettooth.model.locations.Location;

public abstract sealed class MoneyDealer implements FinanciallyInteractable permits Bank, LoanShark
{
	private Location location;
	final ArrayList<Client> clients = new ArrayList<>();
	
	MoneyDealer(Location location){
		this.location = location;
	}
	
	public Location getLocation() {
		return location;
	}
	
	Client findClientByIdentity(Playable player) {
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
	
	public abstract Double applyInterestToBalance(Player player);
	public abstract void increaseClientsBalance(Player player, double amount);
	public abstract void reduceClientsBalance(Player player, double amount);
}