package com.github.SweetTooth.experiences;

import com.github.SweetTooth.characters.Player;

final class MuggingCash extends Experience {
	@Override
	public String process(Player player) {
		if(player.getCash() == 0)
			return "Jemand will dein Geld klauen, aber du hast eh nichts dabei.";
		player.setCash(0);
		return "Hilfe! Jemand hat dein ganzes Geld geklaut.";
	}
}
