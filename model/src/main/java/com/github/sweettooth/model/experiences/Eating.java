package com.github.sweettooth.model.experiences;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.github.sweettooth.model.api.ModelSettings;
import com.github.sweettooth.model.characters.Player;
import com.github.sweettooth.model.snacks.Snack;

final class Eating extends Experience {
	
	Eating(ModelSettings modelSettings) {
		super(modelSettings);
	}
	
	@Override
	public String process(Player player) {
		ArrayList<? extends Snack> candies = player.getCandies();
		int size = candies.size();
		if(size == 0)
			return "Du hast Hunger, aber leider nix zu Essen.";
		
		ThreadLocalRandom random = ThreadLocalRandom.current();
		Snack randomCandy = candies.get(random.nextInt(0, size));
		int randomQuantity = random.nextInt(1, randomCandy.getQuantity() + 1);
		player.removeSnack(randomCandy, candies, randomQuantity);
		return "Du hast Hunger und isst einige deiner Süßigkeiten.";
	}
}
