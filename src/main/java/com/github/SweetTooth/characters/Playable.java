package com.github.SweetTooth.characters;

import java.util.ArrayList;

import com.github.SweetTooth.locations.Location;
import com.github.SweetTooth.snacks.Snackable;

public interface Playable {
	public static Playable getInstance() {
		return new Player();
	}
	
	public static int getMaxCandies() {
		return Player.MAX_CANDIES;
	}
	
	public static double getTravelCosts() {
		return Player.TRAVEL_COSTS;
	}

	void addAllCandies(ArrayList<Snackable> candies, ArrayList<Snackable> list);
	void addCandy(Snackable candy, ArrayList<Snackable> list, int quantity);
	void addCash(double amount);
	ArrayList<Snackable> getCandies();
	double getCash();
	Location getHometown();
	Location getLocation();
	ArrayList<Snackable> getStash();
	void reduceCash(double amount);
	void removeAllCandies(ArrayList<Snackable> candiesToRemove, ArrayList<Snackable> fromList);
	void removeCandy(Snackable candy, ArrayList<Snackable> list, int quantity);
	void setCash(double cash);
	void setLocation(Location cityName);
}
