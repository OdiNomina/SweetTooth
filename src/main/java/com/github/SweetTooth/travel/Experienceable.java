package com.github.SweetTooth.travel;

import com.github.SweetTooth.characters.Playable;

public interface Experienceable {
	public static Experienceable getRandomExperience() {
		return Experience.randomExperience();
	}
	
	String process(Playable player);
}
