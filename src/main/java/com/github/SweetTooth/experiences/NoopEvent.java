package com.github.SweetTooth.experiences;

import com.github.SweetTooth.characters.IPlayer;

final class NoopEvent extends Experience {
	@Override
	public String process(IPlayer player) {
		return "Nix los heut...";
	}	
}
