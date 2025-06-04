package com.github.sweettooth.model.snacks;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import com.github.sweettooth.model.apiView.Snackable;
import com.github.sweettooth.model.locations.Location;

public abstract sealed class Candy implements Cloneable, Snackable permits
	Bonbon, BubbleGum, ChewyCandy, ChocolateBar, GummyBears, Lollipop
{
	static double rounded(double amount) {
		return Math.round(amount * 100) / 100.00;
	}
	
	private final String name;
	private final double minPrice;
	private final double maxPrice;
	private int quantity = 1;
	
	Candy(String name, double minPrice, double maxPrice) {
		this.name = name;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}
	
	/*
	 * Typ-Inferenz: Aus dem Ergebnistyp (Zuweisung bei Aufruf) leitet der Compiler das Typargument der Rückgabe ab.
	 * (Java Insel - 12.1.7 Generische Methoden/Konstruktoren und Typ-Inferenz | Knappe Fabrikmethoden)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Snackable> T cloneSnack() {
		try {
			return (T) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(); //Kann eigentlich nicht auftreten, da Cloneable implementiert wird.
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		Candy other = (Candy) obj;
		return Objects.equals(name, other.name) && quantity == other.quantity;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public int getQuantity() {
		return quantity;
	}
	
	@Override
	public abstract double getStaticPrice();
	
	@Override
	public int hashCode() {
		return Objects.hash(name, quantity);
	}
	
	@Override
	public void increaseQuantity(int number) {
		quantity += number;
	}
	
	@Override
	public void reduceQuantity(int number) {
		quantity -= number;
	}
	
	@Override
	public void setQuantity(int quantity) {
		this.quantity = quantity > 0 ? quantity : 0;
	}
	
	public void setRandomStaticPrice(Location location) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		double newPrice = rounded(random.nextDouble(minPrice, maxPrice));
		newPrice *= location.getPriceFactor();
		setStaticPrice(newPrice);
	}
	
	abstract void setStaticPrice(double rounded);
}
