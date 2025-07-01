package com.github.sweettooth.model.experiences;

import com.github.sweettooth.model.api.Settings;
import com.github.sweettooth.model.characters.Player;

public abstract sealed class Experience permits 
	Eating, Gift, MuggingCandies, MuggingCash, NoopEvent
{	

	public static Experience randomExperience(Settings gameSettings){
		double random = Math.random();
		if(random < 0.1) return new MuggingCash(gameSettings);
		if(random < 0.2) return new MuggingCandies(gameSettings);
		if(random < 0.4) return new Eating(gameSettings);
		if(random < 0.6) return new Gift(gameSettings); 
		return new NoopEvent();
	}
	
	Settings gameSettings;
	
	Experience() {}
	
	Experience(Settings gameSettings) {
		this.gameSettings = gameSettings;
	}

	public abstract String process(Player player);
}
