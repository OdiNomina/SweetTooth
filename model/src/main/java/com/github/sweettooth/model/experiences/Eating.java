package com.github.sweettooth.model.experiences;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.github.sweettooth.model.api.Settings;
import com.github.sweettooth.model.api.Snackable;
import com.github.sweettooth.model.characters.Player;

final class Eating extends Experience {
	
	Eating(Settings gameSettings) {
		super(gameSettings);
	}
	
	@Override
	public String process(Player player) {
		ArrayList<? extends Snackable> candies = player.getCandies();
		int size = candies.size();
		if(size == 0)
			return "Du hast Hunger, aber leider nix zu Essen.";
		
		ThreadLocalRandom random = ThreadLocalRandom.current();
		Snackable randomCandy = candies.get(random.nextInt(0, size));
		int randomQuantity = random.nextInt(1, randomCandy.getQuantity() + 1);
		player.removeSnack(randomCandy, candies, randomQuantity);
		return "Du hast Hunger und isst einige deiner Süßigkeiten.";
	}
}
