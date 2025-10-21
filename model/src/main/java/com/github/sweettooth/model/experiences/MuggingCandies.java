package com.github.sweettooth.model.experiences;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.github.sweettooth.model.api.GameSettings;
import com.github.sweettooth.model.characters.Player;
import com.github.sweettooth.model.snacks.Snack;

final class MuggingCandies extends Experience {
	
	MuggingCandies(GameSettings modelSettings) {
		super(modelSettings);
	}
	
	@Override
	public String process(Player player) {
		ArrayList<? extends Snack> snacks = player.getSnacksInPockets();
		int size = snacks.size();
		
		if(size < 1)
			return "Jemand versucht dir Süßigkeiten zu klauen, aber du hast eh nichts.";
		
		ThreadLocalRandom random = ThreadLocalRandom.current();
		Snack randomSnack = snacks.get(random.nextInt(0, size));
		int randomQuantity = random.nextInt(1, randomSnack.getQuantity() + 1);
		player.removeSnack(randomSnack, snacks, randomQuantity);
		
		return "Hilfe! Jemand klaut dir Süßigkeiten!";
	}
}
