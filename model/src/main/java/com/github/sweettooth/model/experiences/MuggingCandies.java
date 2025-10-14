package com.github.sweettooth.model.experiences;

import com.github.sweettooth.model.api.GameSettings;
import com.github.sweettooth.model.characters.Player;

final class MuggingCandies extends Experience {
	
	MuggingCandies(GameSettings modelSettings) {
		super(modelSettings);
	}
	
	@Override
	public String process(Player player) {
		if(player.getSnacksInPockets().size() < 1)
			return "Jemand will dir Süßigkeiten klauen, aber du hast nichts.";
		player.getSnacksInPockets().clear();
		return "Hilfe! Jemand klaut dir alle Süßigkeiten.";
	}
}
