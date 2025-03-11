package com.github.SweetTooth.travel;

import com.github.SweetTooth.characters.IPlayer;

public interface Experienceable {
	public static Experienceable getRandomExperience() {
		return Experience.randomExperience();
	}
	
	String process(IPlayer player);
}
