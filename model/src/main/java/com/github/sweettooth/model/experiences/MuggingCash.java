package com.github.sweettooth.model.experiences;

import java.util.concurrent.ThreadLocalRandom;

import com.github.sweettooth.model.characters.Player;
import com.github.sweettooth.model.settings.GameSettings;

final class MuggingCash extends Experience {
	
	MuggingCash(GameSettings modelSettings) {
		super(modelSettings);
	}
	
	@Override
	public String process(Player player) {
		if(player.getCash() == 0)
			return "Jemand will dein Geld klauen, aber du hast eh nichts dabei.";
		
		ThreadLocalRandom random = ThreadLocalRandom.current();
		double money = random.nextDouble(0, player.getCash());
		player.reduceCash(money);
		
		return "Hilfe! Jemand hat dir Geld geklaut!";
	}
}
