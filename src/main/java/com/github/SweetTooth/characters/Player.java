package com.github.SweetTooth.characters;

import java.util.ArrayList;
import java.util.Objects;

import com.github.SweetTooth.locations.Location;
import com.github.SweetTooth.snacks.Snackable;

class Player extends Character implements IPlayer, PersistentPreference, Logged {
	final static int MAX_CANDIES = Integer.valueOf(100); //Änderung der Konstanten erzwingt keine neue Übersetzung der Klassen.
	final static double TRAVEL_COSTS = Double.valueOf(10.00);
	private final ArrayList<Snackable> candies = new ArrayList<Snackable>();
	private final ArrayList<Snackable> stash = new ArrayList<Snackable>();
	private double cash = 200;

	Player(){
		super(Location.BRONX);
	}

	/**
	 * Adds all the given candy types to the given list and updates each quantity property.
	 * @param candies the types to add.
	 * @param list the list to be added to.
	 * @exception IllegalArgumentException
	 * 				if one of the arguments is null.
	 * @see #addCandy(Snackable, ArrayList, int)
	 */
	@Override
	public void addAllCandies(ArrayList<Snackable> candies, ArrayList<Snackable> list) {
		if(candies == null || list == null)
			throw new IllegalArgumentException("The arguments must not be null.");
		
		for(Snackable newCandy : candies) {
			addCandy(newCandy, list, newCandy.getQuantity());
		}
	}
	
	/**
	 * Adds the given snack to the given list and updates the quantity property. If quantity is 0, nothing will be added.
	 * If an equal snack already exists in the list, the quantity of this object will be increased by the given quantity.
	 * If no equal snack exists, a new instance of the given type will be added. It's quantity will be initialized with the given quantity.
	 * Types are equal if the names of the objects are equal.
	 * @param snack the type to add.
	 * @param list the list to be added to.
	 * @param quantity the quantity property will be increased or initialized by this number.
	 * @exception IllegalArgumentException
	 * 				if the type or the list argument is null.
	 */
	@Override
	public void addCandy(Snackable snack, ArrayList<Snackable> list, int quantity) {
		if(snack == null || list == null)
			throw new IllegalArgumentException("The arguments must not be null.");
		if(quantity == 0) 
			return;
		for(Snackable existingCandy : list) {
			if(existingCandy.getName().equalsIgnoreCase(snack.getName().strip())) {
				existingCandy.increaseQuantity(quantity);
				return;
			}
		}
		Snackable clone = snack.clone();
		clone.setQuantity(quantity);
		list.add(clone);
	}
	
	/**
	 * Adds the given amount rounded to the player's cash.
	 * The argument will be rounded to two decimal places.
	 */
	@Override
	public void addCash(double amount) {
		cash = rounded(cash) + rounded(amount);
	}

	/**
	 * Returns the list reference of player's candies list.
	 */
	@Override
	public ArrayList<Snackable> getCandies() {
		return candies;
	}
	
	/**
	 * Returns player's cash rounded to two decimal places.
	 */
	@Override
	public double getCash() {
		return rounded(cash);
	}
	
	/**
	 * Returns the list reference of player's stash list.
	 */
	@Override
	public ArrayList<Snackable> getStash() {
		return stash;
	}
	
	/**
	 * Reduces player's cash by the given amount.
	 * The argument will be rounded to two decimal places.
	 * @exception IllegalArgumentException
	 * 				if the given amount is greater than player's cash.
	 */
	@Override
	public void reduceCash(double amount) {
		if(rounded(amount) > rounded(cash))
			throw new IllegalArgumentException("Argument is too large.");
		cash = rounded(cash) - rounded(amount);
	}

	/**
	 * Removes all given candy types from the given list.
	 * @param candies the types to remove.
	 * @param list the list to remove from.
	 * @exception IllegalArgumentException
	 * 				if one of the arguments is null.
	 * @see #removeCandy(Snackable, ArrayList, int)
	 */
	@Override
	public void removeAllCandies(ArrayList<Snackable> candies, ArrayList<Snackable> list) {
		if(candies == null || list == null)
			throw new IllegalArgumentException("The arguments must not be null.");
		
		for(Snackable candy : candies) {
			removeCandy(candy, list, candy.getQuantity());
		}
	}

	/**
	 * Updates the quantity property of the corresponding candy type object in the list or removes it.
	 * If the quantity property of an equal type is greater than the given quantity, it will be reduced accordingly.
	 * Otherwise, the given type will be removed.
	 * Types are equal if the names of the objects are equal.
	 * @param candy the type to remove.
	 * @param list the list to remove from.
	 * @param quantity the quantity property will be reduced by this number, if an corresponding type exists.
	 * @exception IllegalArgumentException
	 * 				if the type or the list argument is null.
	 */
	@Override
	public void removeCandy(Snackable candy, ArrayList<Snackable> list, int quantity) {
		if(candy == null || list == null)
			throw new IllegalArgumentException("The arguments must not be null.");
		if(quantity == 0)
			return;

		for(Snackable existingCandy : list) {
			if(existingCandy.getName().equalsIgnoreCase(candy.getName())) {
				if(existingCandy.getQuantity() > quantity)
					existingCandy.reduceQuantity(quantity);
				else
					list.remove(existingCandy);
				
				break;
			}
		}
	}
	
	/**
	 * Sets player's cash rounded to two decimal places.
	 */
	@Override
	public void setCash(double cash) {
		this.cash = rounded(cash);
	}
	
	// Currently for training purposes only
	@Override
	public void store(String key, String value) {
		PersistentPreference.super.store("player." + key, value);
	}

	@Override
	public String toString() {
		StringBuffer builder = new StringBuffer();
		builder.append("Player [candies=").append(candies)
			.append(", stash=").append(stash)
			.append(", cash=").append(cash)
			.append(", hometown=").append(hometown)
			.append(", location=").append(location)
			.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(candies, cash, stash);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if(!super.equals(obj))
			return false;
		Player other = (Player) obj;
		return Objects.equals(candies, other.candies) //ArrayList compares elementData
				&& Double.doubleToLongBits(cash) == Double.doubleToLongBits(other.cash)
				&& Objects.equals(stash, other.stash);
	}
}
