package com.github.SweetTooth.travel;

import com.github.SweetTooth.characters.IPlayer;

final class MuggingCandies extends Experience implements Experienceable {
	@Override
	public String process(IPlayer player) {
		if(player.getCandies().size() < 1)
			return "Jemand will dir Süßigkeiten klauen, aber du hast nichts.";
		player.getCandies().clear();
		return "Hilfe! Jemand klaut dir alle Süßigkeiten.";
	}
}
