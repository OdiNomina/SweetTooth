package com.github.SweetTooth.travel;

import java.util.ArrayList;

import com.github.SweetTooth.characters.IPlayer;
import com.github.SweetTooth.snacks.Snackable;

abstract sealed class Experience implements Experienceable permits 
	Eating, Gift, MuggingCandies, MuggingCash, NoopEvent
{	
	static Experienceable randomExperience(){
		double random = Math.random();
		if(random < 0.1) return new MuggingCash();
		if(random < 0.2) return new MuggingCandies();
		if(random < 0.4) return new Eating();
		if(random < 0.6) return new Gift(); 
		return new NoopEvent();
	}
		
	boolean hasSpaceInPockets(IPlayer player, int quantity){
		int sumCandies = 0;
		ArrayList<Snackable> candies =  player.getCandies();
		for(int i = 0; i < candies.size(); i++)
			sumCandies += candies.get(i).getQuantity();
		return sumCandies + quantity <= IPlayer.getMaxCandies();
	}
}
