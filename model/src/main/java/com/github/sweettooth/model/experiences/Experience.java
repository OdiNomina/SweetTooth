package com.github.sweettooth.model.experiences;

import com.github.sweettooth.model.api.GameSettings;
import com.github.sweettooth.model.characters.Player;

public abstract sealed class Experience permits 
	Eating, Gift, MuggingCandies, MuggingCash, NoopEvent
{	

	public static Experience randomExperience(GameSettings modelSettings){
		double random = Math.random();
		if(random < 0.1) return new MuggingCash(modelSettings);
		if(random < 0.2) return new MuggingCandies(modelSettings);
		if(random < 0.4) return new Eating(modelSettings);
		if(random < 0.6) return new Gift(modelSettings); 
		return new NoopEvent();
	}
	
	GameSettings modelSettings;
	
	Experience() {}
	
	Experience(GameSettings modelSettings) {
		this.modelSettings = modelSettings;
	}

	public abstract String process(Player player);
}
