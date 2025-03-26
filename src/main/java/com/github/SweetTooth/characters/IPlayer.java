package com.github.SweetTooth.characters;

import java.util.ArrayList;
import com.github.SweetTooth.locations.Location;
import com.github.SweetTooth.snacks.Candy;
import com.github.SweetTooth.snacks.Snackable;

public interface IPlayer {
	public static IPlayer getInstance(String name) {
		return new Player(name);
	}
	
	public static int getMaxSnacks() {
		return Player.MAX_SNACKS;
	}
	
	public static double getTravelCosts() {
		return Player.TRAVEL_COSTS;
	}

	<E extends Snackable> void addAllSnacks(ArrayList<E> snacks, ArrayList<E> list);
	void addCash(double amount);
	<E extends Snackable> void addSnack(E snack, ArrayList<E> list, int quantity);
	ArrayList<Candy> getCandies();
	ArrayList<Candy> getCandyStash();
	double getCash();
	Location getHometown();
	Location getLocation();
	String getName();
	void reduceCash(double amount);
	void removeAllSnacks(ArrayList<? extends Snackable> candiesToRemove, ArrayList<? extends Snackable> fromList);
	void removeSnack(Snackable candy, ArrayList<? extends Snackable> list, int quantity);
	void setCash(double cash);
	void setLocation(Location cityName);
}
