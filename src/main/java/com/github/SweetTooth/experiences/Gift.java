package com.github.SweetTooth.experiences;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.github.SweetTooth.characters.Player;
import com.github.SweetTooth.snacks.Candy;
import com.github.SweetTooth.snacks.CandyFactory;
import com.github.SweetTooth.snacks.Snackable;

final class Gift extends Experience {
	final static int MAX_GIFTS = Integer.valueOf(3);
	final static int MAX_QUANTITY = Integer.valueOf(6);
	
	@Override
	public String process(Player player) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		Candy randomCandy = null;
		int randomQuantity = 0;
		int gift = 0;
		for(int i = 0; i <= random.nextInt(0, MAX_GIFTS); i++) {
			randomQuantity = random.nextInt(1, MAX_QUANTITY + 1);
			randomCandy = new CandyFactory().getRandom();
			if(isNotTooMuch(player, randomQuantity)) {
				player.addSnack(randomCandy, player.getCandies(), randomQuantity);
				gift++;
			}
		}
		if(gift > 0)
			return "Du Glückspilz! Jemand schenkt dir Süßigkeiten.";
		return "Jemand möchte dir Süßigkeiten schenken, aber deine Taschen sind voll.";
	}
	
	boolean isNotTooMuch(Player player, int quantity){
		int sumCandies = 0;
		ArrayList<? extends Snackable> candies =  player.getCandies();
		for(int i = 0; i < candies.size(); i++)
			sumCandies += candies.get(i).getQuantity();
		return sumCandies + quantity <= Player.getMaxSnacks();
	}
}
