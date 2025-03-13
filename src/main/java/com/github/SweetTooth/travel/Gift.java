package com.github.SweetTooth.travel;

import java.util.concurrent.ThreadLocalRandom;

import com.github.SweetTooth.characters.IPlayer;
import com.github.SweetTooth.snacks.CandyFactory;
import com.github.SweetTooth.snacks.Snackable;

final class Gift extends Experience implements Experienceable {
	final static int MAX_GIFTS = Integer.valueOf(3);
	final static int MAX_QUANTITY = Integer.valueOf(6);
	
	@Override
	public String process(IPlayer player) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		Snackable randomCandy = null;
		int randomQuantity = 0;
		int gift = 0;
		for(int i = 0; i <= random.nextInt(0, MAX_GIFTS); i++) {
			randomQuantity = random.nextInt(1, MAX_QUANTITY + 1);
			randomCandy = new CandyFactory().getRandom();
			if(hasSpaceInPockets(player, randomQuantity)) {
				player.addCandy(randomCandy, player.getCandies(), randomQuantity);
				gift++;
			}
		}
		if(gift > 0)
			return "Du Glückspilz! Jemand schenkt dir Süßigkeiten.";
		return "Jemand möchte dir Süßigkeiten schenken, aber deine Taschen sind voll.";
	}
}
