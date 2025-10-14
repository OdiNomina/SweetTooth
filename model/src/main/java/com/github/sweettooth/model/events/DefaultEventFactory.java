package com.github.sweettooth.model.events;

import com.github.sweettooth.model.api.controllerAPI.EventFactory;
import com.github.sweettooth.model.games.GameData;
import com.github.sweettooth.model.session.SessionData;

public class DefaultEventFactory extends EventFactory {
	SessionData sessionData;
	
	public DefaultEventFactory(SessionData sessionData ){
		this.sessionData = sessionData;
	}
	
	@Override
	public Event getEvent(String event, GameData gameData) {
		Event instance = null; 
		switch (event) {
			case "ApplyInterest" -> instance = new ApplyInterest(sessionData, gameData);
			case "Buy" -> instance = new Buy(sessionData, gameData); 
			case "Deposit" -> instance = new Deposit(sessionData, gameData);
			case "Exit" -> instance = new Exit(sessionData, gameData);
			case "GiveMoneyBack" -> instance = new GiveMoneyBack(sessionData, gameData);
			case "Hide" -> instance = new Hide(sessionData, gameData);
			case "Lend" -> instance = new Lend(sessionData, gameData);
			case "Sell" -> instance = new Sell(sessionData, gameData);
			case "Seek" -> instance = new Seek(sessionData, gameData);
			case "Travel" -> instance = new Travel(sessionData, gameData);
			case "Withdraw" -> instance = new Withdraw(sessionData, gameData);
		}
		return instance;
	}
}
