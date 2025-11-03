package com.github.sweettooth.model.characters;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

import com.github.sweettooth.model.api.characters.IMoneyDealer;
import com.github.sweettooth.model.api.characters.IPlayer;
import com.github.sweettooth.model.commons.GlobalSettings;
import com.github.sweettooth.model.locations.Location;

public abstract sealed class MoneyDealer implements IMoneyDealer permits Bank, LoanShark
{
	final ArrayList<Client> clients = new ArrayList<>();
	GlobalSettings globalSettings;
	Location location;
	
	MoneyDealer(Location location, GlobalSettings globalSettings){
		this.location = location;
		this.globalSettings = globalSettings;
	}
	
	public abstract Double applyInterestToBalance(Player player);

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;
		MoneyDealer other = (MoneyDealer) obj;
		return Objects.equals(clients, other.clients);
	}

	@Override
	public double getClientsBalance(IPlayer player) {
		return getExistingOrNewClient((Player)player).getBalance();
	}

	@Override
	public abstract String getHintCreditInterest();

	@Override
	public abstract String getHintDebitInterest();

	@Override
	public abstract String getHintDispo();

	Client getExistingOrNewClient(Player player) {
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
	
	public abstract void increaseClientsBalance(Player player, double amount);
	public abstract void reduceClientsBalance(Player player, double amount);
}