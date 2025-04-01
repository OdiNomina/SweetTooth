package com.github.SweetTooth.experiences;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.github.SweetTooth.characters.Player;
import com.github.SweetTooth.snacks.Snackable;

final class Eating extends Experience {
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
