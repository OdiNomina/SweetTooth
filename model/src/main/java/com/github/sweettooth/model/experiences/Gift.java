package com.github.sweettooth.model.experiences;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.github.sweettooth.model.api.Settings;
import com.github.sweettooth.model.api.Snackable;
import com.github.sweettooth.model.characters.Player;

final class Gift extends Experience {
	final static int MAX_GIFTS = Integer.valueOf(3);
	final static int MAX_QUANTITY = Integer.valueOf(6);
	
	Gift(Settings gameSettings) {
		super(gameSettings);
	}
	
	@Override
	public String process(Player player) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		Snackable randomCandy = null;
		int randomQuantity = 0;
		int gift = 0;
		for(int i = 0; i <= random.nextInt(0, MAX_GIFTS); i++) {
			randomQuantity = random.nextInt(1, MAX_QUANTITY + 1);
			randomCandy = gameSettings.getSnackFactory().getRandom();
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