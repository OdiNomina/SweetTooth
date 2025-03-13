package com.github.SweetTooth.experiences;

import com.github.SweetTooth.characters.IPlayer;

public abstract sealed class Experience permits 
	Eating, Gift, MuggingCandies, MuggingCash, NoopEvent
{	
	public static Experience randomExperience(){
		double random = Math.random();
		if(random < 0.1) return new MuggingCash();
		if(random < 0.2) return new MuggingCandies();
		if(random < 0.4) return new Eating();
		if(random < 0.6) return new Gift(); 
		return new NoopEvent();
	}
		
	public abstract String process(IPlayer player);
}
