package com.github.sweettooth.model.commons;

import java.util.ArrayList;

import com.github.sweettooth.model.api.Playable;
import com.github.sweettooth.model.api.Snackable;

public class Tools {
	public static boolean isTooMuchToCarry(Playable player, int quantity){
		ArrayList<? extends Snackable> candies = player.getCandies();
		Integer sumInPockets = candies.stream().reduce(0, (sum, element) -> sum + element.getQuantity(), Integer::sum);
		
		return sumInPockets + quantity > InternSettings.MAX_SNACKS;
	}
	
	public static double rounded(double amount) {
		return Math.round(amount * 100) / 100.00;
	}
}
