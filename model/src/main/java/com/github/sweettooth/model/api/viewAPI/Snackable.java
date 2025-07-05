package com.github.sweettooth.model.api.viewAPI;

import com.github.sweettooth.model.snacks.Snack;

public interface Snackable {
	
	public default String name() {
		return ((Snack)this).getName();
	}
	
	public default int quantity() {
		return ((Snack)this).getQuantity();
	}
	
	public default double staticPrice() {
		return ((Snack)this).getStaticPrice();
	}
}