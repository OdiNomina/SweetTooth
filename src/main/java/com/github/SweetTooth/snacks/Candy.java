package com.github.SweetTooth.snacks;

import java.util.Objects;

abstract sealed class Candy implements Cloneable, Snackable permits
	Bonbon, BubbleGum, ChewyCandy, ChocolateBar, GummyBears, Lollipop
{
	private final String name;
	private int quantity = 1;
	
	Candy(String name) {
		this.name = name;
	}
	
	@Override
	public Candy clone() {
		try {
			return (Candy) super.clone();
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
	
	double rounded(double amount) {
		return Math.round(amount * 100) / 100.00;
	}
	
	@Override
	public void setQuantity(int quantity) {
		this.quantity = quantity > 0 ? quantity : 0;
	}
	
	@Override
	public abstract void setRandomStaticPrice();
}
