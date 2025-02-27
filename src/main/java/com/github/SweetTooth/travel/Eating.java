package com.github.SweetTooth.travel;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.github.SweetTooth.characters.Playable;
import com.github.SweetTooth.snacks.Snackable;

final class Eating extends Experience implements Experienceable {
	@Override
	public String process(Playable player) {
		ArrayList<Snackable> candies = player.getCandies();
		int size = candies.size();
		if(size == 0)
			return "Du hast Hunger, aber leider nix zu Essen.";
		
		ThreadLocalRandom random = ThreadLocalRandom.current();
		Snackable randomCandy = candies.get(random.nextInt(0, size));
		int randomQuantity = random.nextInt(1, randomCandy.getQuantity() + 1);
		player.removeCandy(randomCandy, candies, randomQuantity);
		return "Du hast Hunger und isst einige deiner Süßigkeiten.";
	}
}
