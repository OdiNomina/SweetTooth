package com.github.sweettooth.model.characters;

import java.util.ArrayList;
import java.util.Objects;

import com.github.sweettooth.model.api.characters.IPlayer;
import com.github.sweettooth.model.commons.InternSettings;
import com.github.sweettooth.model.commons.PersistentPreference;
import com.github.sweettooth.model.commons.Tools;
import com.github.sweettooth.model.locations.Location;
import com.github.sweettooth.model.snacks.Snack;

public class Player implements IPlayer, PersistentPreference {
	private String name;
	private final Location hometown;
	private final ArrayList<Snack> snacksInPockets;
	private final ArrayList<Snack> snacksInStash;
	
	private Location location;
	private double cash;
	
	public Player(String name){
		this.name = Objects.requireNonNullElse(name, "Anonymer Spieler");
		hometown = InternSettings.HOMETOWN;
		snacksInPockets = new ArrayList<>();
		snacksInStash = new ArrayList<>();
		initialize();
	}
	
	private void initialize() {
		location = hometown;
		cash = InternSettings.START_CASH;
	}

	public Location getHometown() {
		return hometown;
	}
	
	@Override
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location cityName) {
		this.location = cityName;
	}
	
	/**
	 * Adds all snacks to the given list and updates each quantity property.
	 * @param snacks the snacks to add.
	 * @param list the list to be added to.
	 * @exception IllegalArgumentException
	 * 				if one of the arguments is null.
	 * @see #addSnack(Snack, ArrayList, int)
	 */
	public <E extends Snack> void addAllSnacks(ArrayList<E> snacks, ArrayList<E> list) {
		if(snacks == null || list == null)
			throw new IllegalArgumentException("The arguments must not be null.");
		
		for(E s : snacks) {
			addSnack(s, list, s.getQuantity());
		}
	}
	
	/**
	 * Adds the given amount rounded to the player's cash.
	 * The argument will be rounded to two decimal places.
	 */
	public void addCash(double amount) {
		cash = Tools.rounded(cash) + Tools.rounded(amount);
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
	public <E extends Snack> void addSnack(E snack, ArrayList<E> list, int quantity) {
		if(snack == null || list == null)
			throw new IllegalArgumentException("The arguments must not be null.");
		if(quantity == 0) 
			return;
		for(Snack s : list) {
			if(s.getName().equalsIgnoreCase(snack.getName().strip())) {
				s.increaseQuantity(quantity);
				return;
			}
		}
		E clone = snack.cloneSnack();
		clone.setQuantity(quantity);
		list.add(clone);
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
		return Objects.equals(snacksInPockets, other.snacksInPockets) //ArrayList compares elementData
				&& Objects.equals(snacksInStash, other.snacksInStash) 
				&& thisCash == otherCash
				&& name.equalsIgnoreCase(other.name);
	}
	
	/**
	 * Returns the list reference of player's candies list.
	 */
	public ArrayList<Snack> getSnacksInPockets() {
		return snacksInPockets;
	}
	
	/**
	 * Returns the list reference of player's candyStash list.
	 */
	public ArrayList<Snack> getSnacksInStash() {
		return snacksInStash;
	}
	
	/**
	 * Returns player's cash rounded to two decimal places.
	 */
	@Override
	public double getCash() {
		return Tools.rounded(cash);
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	/**
	* Returns a hash code value for this {@code Player} object.
	* @return A hash code value for this object.
	* @see java.lang.Object#equals(java.lang.Object)
	* @see java.util.HashMap
	*/
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(snacksInPockets, cash, name, snacksInStash);
		return result;
	}

	/**
	 * Reduces player's cash by the given amount.
	 * The argument will be rounded to two decimal places.
	 * @exception IllegalArgumentException
	 * 				if the given amount is greater than player's cash.
	 */
	public void reduceCash(double amount) {
		if(Tools.rounded(amount) > Tools.rounded(cash))
			throw new IllegalArgumentException("Argument is too large.");
		cash = Tools.rounded(cash) - Tools.rounded(amount);
	}
	
	/**
	 * Removes all snacks from the given list.
	 * @param snacks the snacks to remove.
	 * @param list the list to remove from.
	 * @exception IllegalArgumentException
	 * 				if one of the arguments is null.
	 * @see #removeSnack(Snack, ArrayList, int)
	 */
	public void removeAllSnacks(ArrayList<? extends Snack> snacks, ArrayList<? extends Snack> list) {
		if(snacks == null || list == null)
			throw new IllegalArgumentException("The arguments must not be null.");
		
		for(Snack s : snacks) {
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
	public void removeSnack(Snack snack, ArrayList<? extends Snack> list, int quantity) {
		if(snack == null || list == null)
			throw new IllegalArgumentException("The arguments must not be null.");
		if(quantity == 0)
			return;
		for(Snack s : list) {
			if(s.getName().equalsIgnoreCase(snack.getName())) {
				if(s.getQuantity() > quantity)
					s.reduceQuantity(quantity);
				else
					list.remove(s);
				break;
			}
		}
	}
	
	@Override
	public void reset() {
	    snacksInPockets.clear();
	    snacksInStash.clear();
	    initialize();
	}

	/**
	 * Sets player's cash rounded to two decimal places.
	 */
	public void setCash(double cash) {
		this.cash = Tools.rounded(cash);
	}
	
	// Currently for training purposes only
//	@Override
//	public void store(String key, String value) {
//		PersistentPreference.super.store("player." + key, value);
//	}
	
	@Override
	public String toString() {
		StringBuffer builder = new StringBuffer();
		builder.append("Player [candies=").append(snacksInPockets)
			.append(", candyStash=").append(snacksInStash)
			.append(", cash=").append(cash)
			.append(", name=").append(name)
			.append(", hometown=").append(hometown)
			.append(", location=").append(location)
			.append("]");
		return builder.toString();
	}
}
