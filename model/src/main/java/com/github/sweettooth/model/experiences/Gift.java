package com.github.sweettooth.model.experiences;

import java.util.concurrent.ThreadLocalRandom;

import com.github.sweettooth.model.characters.Player;
import com.github.sweettooth.model.commons.GameSettings;
import com.github.sweettooth.model.commons.InternSettings;
import com.github.sweettooth.model.commons.Tools;
import com.github.sweettooth.model.snacks.Snack;
import com.github.sweettooth.model.snacks.SnackFactory;

final class Gift extends Experience {
	Gift(GameSettings modelSettings) {
		super(modelSettings);
	}
	
	@Override
	public String process(Player player) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		SnackFactory snackFactory = (SnackFactory)modelSettings.getSnackFactory();
		Snack randomCandy = null;
		int randomQuantity = 0;
		int gift = 0;
		for(int i = 0; i <= random.nextInt(0, InternSettings.MAX_GIFT_TYPES); i++) {
			randomQuantity = random.nextInt(1,InternSettings.MAX_GIFT_QUANTITY + 1);
			randomCandy = snackFactory.getRandom();
			if(!Tools.isTooMuchToCarry(player, randomQuantity)) {
				player.addSnack(randomCandy, player.getSnacksInPockets(), randomQuantity);
				gift++;
			}
		}
		if(gift > 0)
			return "Du Glückspilz! Jemand schenkt dir Süßigkeiten.";
		return "Jemand möchte dir Süßigkeiten schenken, aber deine Taschen sind voll.";
	}
}