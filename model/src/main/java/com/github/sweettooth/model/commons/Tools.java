package com.github.sweettooth.model.commons;

import java.util.ArrayList;

import com.github.sweettooth.model.characters.Player;
import com.github.sweettooth.model.snacks.Snack;

public class Tools {
	public static boolean isTooMuchToCarry(Player player, int quantity){
		ArrayList<? extends Snack> candies = player.getCandies();
		Integer sumInPockets = candies.stream().reduce(0, (sum, element) -> sum + element.quantity(), Integer::sum);
		
		return sumInPockets + quantity > InternSettings.MAX_SNACKS;
	}
	
	public static double rounded(double amount) {
		return Math.round(amount * 100) / 100.00;
	}
}
