package com.github.sweettooth.model.experiences;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.github.sweettooth.model.characters.Player;
import com.github.sweettooth.model.settings.GameSettings;
import com.github.sweettooth.model.snacks.Snack;

final class Eating extends Experience {
	
	Eating(GameSettings modelSettings) {
		super(modelSettings);
	}
	
	@Override
	public String process(Player player) {
		ArrayList<? extends Snack> snacks = player.getSnacksFromPockets();
		int size = snacks.size();
		if(size == 0)
			return "Du hast Hunger, aber leider nix zu Essen.";
		
		ThreadLocalRandom random = ThreadLocalRandom.current();
		Snack randomCandy = snacks.get(random.nextInt(0, size));
		int randomQuantity = random.nextInt(1, randomCandy.getQuantity() + 1);
		player.removeSnack(randomCandy, snacks, randomQuantity);
		return "Du hast Hunger und isst einige deiner Süßigkeiten.";
	}
}
