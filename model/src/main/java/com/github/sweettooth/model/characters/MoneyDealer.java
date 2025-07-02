package com.github.sweettooth.model.characters;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

import com.github.sweettooth.model.api.ModelSettings;
import com.github.sweettooth.model.api.viewAPI.IMoneyDealer;
import com.github.sweettooth.model.api.viewAPI.IPlayer;
import com.github.sweettooth.model.locations.Location;

public abstract sealed class MoneyDealer implements IMoneyDealer permits Bank, LoanShark
{
	ModelSettings modelSettings;
	Location location;
	final ArrayList<Client> clients = new ArrayList<>();
	
	MoneyDealer(Location location, ModelSettings modelSettings){
		this.location = location;
		this.modelSettings = modelSettings;
	}
	
	Client getExistingOrNewClient(IPlayer player) {
		Client client = null;
		try {
			client = clients.stream().filter( element -> element.identity.equals(player) ).findFirst().get();
		}
		catch(NoSuchElementException e) {
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