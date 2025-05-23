package com.github.sweettooth.model.experiences;

import com.github.sweettooth.model.characters.Player;

final class NoopEvent extends Experience {
	@Override
	public String process(Player player) {
		return "Nix los heut...";
	}	
}
