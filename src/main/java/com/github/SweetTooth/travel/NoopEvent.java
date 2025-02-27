package com.github.SweetTooth.travel;

import com.github.SweetTooth.characters.Playable;

final class NoopEvent extends Experience implements Experienceable {
	@Override
	public String process(Playable player) {
		return "Nix los heut...";
	}	
}
