package com.github.sweettooth.model.events;

import com.github.sweettooth.model.api.controllerAPI.EventFactory;
import com.github.sweettooth.model.games.GameData;

public class DefaultEventFactory extends EventFactory {
	public DefaultEventFactory(){}
	
	@Override
	public Event getEvent(String event, GameData gameData) {
		Event instance = null; 
		switch (event) {
			case "ApplyInterest" -> instance = new ApplyInterest(gameData);
			case "Buy" -> instance = new Buy(gameData); 
			case "Deposit" -> instance = new Deposit(gameData);
			case "Exit" -> instance = new Exit(gameData);
			case "GiveMoneyBack" -> instance = new GiveMoneyBack(gameData);
			case "Hide" -> instance = new Hide(gameData);
			case "Lend" -> instance = new Lend(gameData);
			case "Sell" -> instance = new Sell(gameData);
			case "Seek" -> instance = new Seek(gameData);
			case "Travel" -> instance = new Travel(gameData);
			case "Withdraw" -> instance = new Withdraw(gameData);
		}
		return instance;
	}
}
