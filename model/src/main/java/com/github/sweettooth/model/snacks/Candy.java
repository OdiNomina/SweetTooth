package com.github.sweettooth.model.snacks;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import com.github.sweettooth.model.commons.Tools;
import com.github.sweettooth.model.locations.Location;

public abstract sealed class Candy extends Snack implements Cloneable permits
	Bonbon, BubbleGum, ChewyCandy, ChocolateBar, GummyBears, Lollipop
{
	private final double minPrice;
	private final double maxPrice;
	
	Candy(String name, double minPrice, double maxPrice) {
		super(name);
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}
	
	/*
	 * Typ-Inferenz: Aus dem Ergebnistyp (Zuweisung bei Aufruf) leitet der Compiler das Typargument der Rückgabe ab.
	 * (Java Insel - 12.1.7 Generische Methoden/Konstruktoren und Typ-Inferenz | Knappe Fabrikmethoden)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Snack> T cloneSnack() {
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
	public abstract double getPrice();

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
	
	void setRandomStaticPrice(Location location) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		double newPrice = Tools.rounded(random.nextDouble(minPrice, maxPrice));
		newPrice *= location.getPriceFactor();
		setStaticPrice(newPrice);
	}
	
	abstract void setStaticPrice(double rounded);
}
