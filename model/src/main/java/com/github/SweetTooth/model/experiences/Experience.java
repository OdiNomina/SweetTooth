package com.github.SweetTooth.model.experiences;

import com.github.SweetTooth.model.characters.Player;

public abstract sealed class Experience permits 
	Eating, Gift, MuggingCandies, MuggingCash, NoopEvent
{	
	Experience(){}
	
	public static Experience randomExperience(){
		double random = Math.random();
		if(random < 0.1) return new MuggingCash();
		if(random < 0.2) return new MuggingCandies();
		if(random < 0.4) return new Eating();
		if(random < 0.6) return new Gift(); 
		return new NoopEvent();
	}
		
	public abstract String process(Player player);
}
