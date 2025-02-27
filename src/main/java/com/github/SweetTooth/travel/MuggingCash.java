package com.github.SweetTooth.travel;

import com.github.SweetTooth.characters.Playable;

final class MuggingCash extends Experience implements Experienceable {
	@Override
	public String process(Playable player) {
		if(player.getCash() == 0)
			return "Jemand will dein Geld klauen, aber du hast eh nichts dabei.";
		player.setCash(0);
		return "Hilfe! Jemand hat dein ganzes Geld geklaut.";
	}
}
