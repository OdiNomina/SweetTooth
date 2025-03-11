package com.github.SweetTooth.characters;

import java.util.ArrayList;
import java.util.Objects;

import com.github.SweetTooth.locations.Location;

abstract sealed class MoneyDealer extends Character implements IMoneyDealer 
	permits Bank, LoanShark
{
	public final static String CURRENCY = "€";
	final ArrayList<Client> clients = new ArrayList<Client>();
	
	MoneyDealer(Location hometown){
		super(hometown);
	}
	
	static MoneyDealer create(String character) {
		switch(character) {	
			case "Bank": return Bank.getInstance();
			case "LoanShark": return LoanShark.getInstance();
			default: return null;
		}
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
}
