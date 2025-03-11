package com.github.SweetTooth.travel;

import com.github.SweetTooth.characters.IPlayer;

final class NoopEvent extends Experience implements Experienceable {
	@Override
	public String process(IPlayer player) {
		return "Nix los heut...";
	}	
}
