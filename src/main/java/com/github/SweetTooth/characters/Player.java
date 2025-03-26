package com.github.SweetTooth.characters;

import java.util.ArrayList;
import java.util.Objects;

import com.github.SweetTooth.locations.Location;
import com.github.SweetTooth.snacks.Candy;
import com.github.SweetTooth.snacks.Snackable;

class Player extends Character implements IPlayer, PersistentPreference, Logged {
	final static int MAX_SNACKS = Integer.valueOf(100); //Änderung der Konstanten erzwingt keine neue Übersetzung von abhängigen Klassen (s. Java Insel - 6.6.4 Eincompilierte Belegungen der Klassenvariablen).
	final static double TRAVEL_COSTS = Double.valueOf(10.00);
	private final ArrayList<Candy> candies = new ArrayList<>();
	private final ArrayList<Candy> candyStash = new ArrayList<>();
	private double cash = 200;
	private String name;

	Player(String name){
		super(Location.BRONX);
		this.name = Objects.requireNonNullElse(name, "Anonymer Spieler");
	}

	/**
	 * Adds all snacks to the given list and updates each quantity property.
	 * @param snacks the snacks to add.
	 * @param list the list to be added to.
	 * @exception IllegalArgumentException
	 * 				if one of the arguments is null.
	 * @see #addSnack(Snackable, ArrayList, int)
	 */
	@Override
	public void addAllSnacks(ArrayList<? extends Snackable> snacks, ArrayList<? extends Snackable> list) {
		if(snacks == null || list == null)
			throw new IllegalArgumentException("The arguments must not be null.");
		
		for(Snackable s : snacks) {
			addSnack(s, list, s.getQuantity());
		}
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
	 * Adds the given snack to the given list and updates the quantity property. If quantity is 0, nothing will be added.
	 * If this kind of snack already exists in the list, the quantity of this object will be increased by the given quantity.
	 * Otherwise, a new instance of the given snack type will be added. It's quantity will be initialized with the given quantity.
	 * @param snack the snack to add.
	 * @param list the list to be added to.
	 * @param quantity the quantity property will be increased or initialized by this number.
	 * @exception IllegalArgumentException
	 * 				if the type or the list argument is null.
	 */
	@Override
	public void addSnack(Snackable snack, ArrayList<? extends Snackable> list, int quantity) {
		if(snack == null || list == null)
			throw new IllegalArgumentException("The arguments must not be null.");
		if(quantity == 0) 
			return;
		for(Snackable s : list) {
			if(s.getName().equalsIgnoreCase(snack.getName().strip())) {
				s.increaseQuantity(quantity);
				return;
			}
		}
		Snackable clone = snack.clone();
		clone.setQuantity(quantity);
		if(list == candies)
			candies.add((Candy)clone);
		else  
			if(list == candyStash)
				candyStash.add((Candy)clone);
	}

	/**
	* Determines whether or not two players are equal.
	* Two instances of {@code Player} are equal if the values of their member fields are the same.
	* @param obj object to be compared with this {@code Player}
	* @return {@code true} if the object to be compared is an instance of {@code Player} and has the same values; {@code false} otherwise.
	*/
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
		long thisCash = cash == 0.0 ? 0L : Double.doubleToLongBits(cash);
		long otherCash = other.cash == 0.0 ? 0L : Double.doubleToLongBits(other.cash);
		return Objects.equals(candies, other.candies) //ArrayList compares elementData
				&& Objects.equals(candyStash, other.candyStash) 
				&& thisCash == otherCash
				&& name.equalsIgnoreCase(other.name);
	}
	
	/**
	 * Returns the list reference of player's candies list.
	 */
	@Override
	public ArrayList<Candy> getCandies() {
		return candies;
	}
	
	/**
	 * Returns the list reference of player's candyStash list.
	 */
	@Override
	public ArrayList<Candy> getCandyStash() {
		return candyStash;
	}
	
	/**
	 * Returns player's cash rounded to two decimal places.
	 */
	@Override
	public double getCash() {
		return rounded(cash);
	}
	
	@Override
	public String getName() {
		return name;
	}

	/**
	* Returns a hash code value for this {@code Player} object.
	* @return A hash code value for this object.
	* @see java.lang.Object#equals(java.lang.Object)
	* @see java.util.HashMap
	*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(candies, cash, name, candyStash);
		return result;
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
	 * Removes all snacks from the given list.
	 * @param snacks the snacks to remove.
	 * @param list the list to remove from.
	 * @exception IllegalArgumentException
	 * 				if one of the arguments is null.
	 * @see #removeSnack(Snackable, ArrayList, int)
	 */
	@Override
	public void removeAllSnacks(ArrayList<? extends Snackable> snacks, ArrayList<? extends Snackable> list) {
		if(snacks == null || list == null)
			throw new IllegalArgumentException("The arguments must not be null.");
		
		for(Snackable s : snacks) {
			removeSnack(s, list, s.getQuantity());
		}
	}
	
	/**
	 * Updates the quantity property of the corresponding snack type in the list or removes this kind of snack.
	 * @param snack the snack to remove.
	 * @param list the list to remove from.
	 * @param quantity the quantity property will be reduced by this number, if an corresponding snack exists.
	 * @exception IllegalArgumentException
	 * 				if the type or the list argument is null.
	 */
	@Override
	public void removeSnack(Snackable snack, ArrayList<? extends Snackable> list, int quantity) {
		if(snack == null || list == null)
			throw new IllegalArgumentException("The arguments must not be null.");
		if(quantity == 0)
			return;
		for(Snackable s : list) {
			if(s.getName().equalsIgnoreCase(snack.getName())) {
				if(s.getQuantity() > quantity)
					s.reduceQuantity(quantity);
				else
					list.remove(s);
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
			.append(", candyStash=").append(candyStash)
			.append(", cash=").append(cash)
			.append(", name=").append(name)
			.append(", hometown=").append(hometown)
			.append(", location=").append(location)
			.append("]");
		return builder.toString();
	}
}
