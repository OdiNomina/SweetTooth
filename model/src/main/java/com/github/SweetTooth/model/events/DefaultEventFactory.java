package com.github.sweettooth.model.events;

import com.github.sweettooth.model.games.Game;

public class DefaultEventFactory extends EventFactory {
	public DefaultEventFactory(){}
	
	@Override
	public Event create(String event, Game game) {
		Event instance = null; 
		switch (event) {
			case "ApplyInterest" -> instance = new ApplyInterest();
			case "Buy" -> instance = new Buy(); 
			case "Deposit" -> instance = new Deposit();
			case "Exit" -> instance = new Exit();
			case "GiveMoneyBack" -> instance = new GiveMoneyBack();
			case "Hide" -> instance = new Hide();
			case "Lend" -> instance = new Lend();
			case "Sell" -> instance = new Sell();
			case "Seek" -> instance = new Seek();
			case "Travel" -> instance = new Travel();
			case "Withdraw" -> instance = new Withdraw();
		}
		instance.game = game;
		return instance;
	}
}
