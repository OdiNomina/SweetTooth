package com.github.SweetTooth.model.experiences;

import com.github.SweetTooth.model.characters.Player;

final class NoopEvent extends Experience {
	@Override
	public String process(Player player) {
		return "Nix los heut...";
	}	
}
