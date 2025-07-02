package com.github.sweettooth.model.experiences;

import java.util.concurrent.ThreadLocalRandom;

import com.github.sweettooth.model.api.ModelSettings;
import com.github.sweettooth.model.api.viewAPI.Snackable;
import com.github.sweettooth.model.characters.Player;
import com.github.sweettooth.model.commons.InternSettings;
import com.github.sweettooth.model.commons.Tools;

final class Gift extends Experience {
	Gift(ModelSettings modelSettings) {
		super(modelSettings);
	}
	
	@Override
	public String process(Player player) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		Snackable randomCandy = null;
		int randomQuantity = 0;
		int gift = 0;
		for(int i = 0; i <= random.nextInt(0, InternSettings.MAX_GIFT_TYPES); i++) {
			randomQuantity = random.nextInt(1,InternSettings.MAX_GIFT_QUANTITY + 1);
			randomCandy = modelSettings.getSnackFactory().getRandom();
			if(!Tools.isTooMuchToCarry(player, randomQuantity)) {
				player.addSnack(randomCandy, player.getCandies(), randomQuantity);
				gift++;
			}
		}
		if(gift > 0)
			return "Du Glückspilz! Jemand schenkt dir Süßigkeiten.";
		return "Jemand möchte dir Süßigkeiten schenken, aber deine Taschen sind voll.";
	}
}