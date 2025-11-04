package com.github.sweettooth.model.experiences;

import com.github.sweettooth.model.characters.Player;
import com.github.sweettooth.model.settings.GameSettings;

public abstract sealed class Experience permits 
	Eating, Gift, MuggingCandies, MuggingCash, NoopEvent
{	

	public static Experience randomExperience(GameSettings modelSettings){
		double random = Math.random()*10;
		System.out.println(random);
		if(random <= 0.4) return new MuggingCash(modelSettings);
		if(random <= 0.8) return new MuggingCandies(modelSettings);
		if(random <= 1.2) return new Eating(modelSettings);
		if(random <= 2.0) return new Gift(modelSettings); 
		return new NoopEvent();
	}
	
	GameSettings modelSettings;
	
	Experience() {}
	
	Experience(GameSettings modelSettings) {
		this.modelSettings = modelSettings;
	}

	public abstract String process(Player player);
}
