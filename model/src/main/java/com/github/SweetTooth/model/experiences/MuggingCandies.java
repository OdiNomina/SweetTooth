package com.github.SweetTooth.model.experiences;

import com.github.SweetTooth.model.characters.Player;

final class MuggingCandies extends Experience {
	@Override
	public String process(Player player) {
		if(player.getCandies().size() < 1)
			return "Jemand will dir Süßigkeiten klauen, aber du hast nichts.";
		player.getCandies().clear();
		return "Hilfe! Jemand klaut dir alle Süßigkeiten.";
	}
}
