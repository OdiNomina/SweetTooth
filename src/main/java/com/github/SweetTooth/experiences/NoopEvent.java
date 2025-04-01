package com.github.SweetTooth.experiences;

import com.github.SweetTooth.characters.Player;

final class NoopEvent extends Experience {
	@Override
	public String process(Player player) {
		return "Nix los heut...";
	}	
}
