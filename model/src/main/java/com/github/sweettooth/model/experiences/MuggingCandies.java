package com.github.sweettooth.model.experiences;

import com.github.sweettooth.model.api.ModelSettings;
import com.github.sweettooth.model.characters.Player;

final class MuggingCandies extends Experience {
	
	MuggingCandies(ModelSettings modelSettings) {
		super(modelSettings);
	}
	
	@Override
	public String process(Player player) {
		if(player.getCandies().size() < 1)
			return "Jemand will dir Süßigkeiten klauen, aber du hast nichts.";
		player.getCandies().clear();
		return "Hilfe! Jemand klaut dir alle Süßigkeiten.";
	}
}
