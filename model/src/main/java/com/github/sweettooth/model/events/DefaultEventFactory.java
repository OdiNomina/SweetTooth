package com.github.sweettooth.model.events;

import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.gameEvents.EventFactory;
import com.github.sweettooth.model.api.gameSession.IGameSession;
import com.github.sweettooth.model.gameRounds.GameRound;
import com.github.sweettooth.model.gameSession.GameSession;

public class DefaultEventFactory extends EventFactory {
	GameSession sessionData;
	
	public DefaultEventFactory(IGameSession sessionData ){
		this.sessionData = (GameSession)sessionData;
	}
	
	@Override
	public Event createEvent(String event, IGameData gameData) {
		GameRound data = (GameRound)gameData;
		Event instance = null; 
		switch (event) {
			case "ApplyInterest" -> instance = new ApplyInterest(sessionData, data);
			case "Buy" -> instance = new Buy(sessionData, data); 
			case "Deposit" -> instance = new Deposit(sessionData, data);
			case "Exit" -> instance = new Exit(sessionData, data);
			case "GiveMoneyBack" -> instance = new GiveMoneyBack(sessionData, data);
			case "Hide" -> instance = new Hide(sessionData, data);
			case "Lend" -> instance = new Lend(sessionData, data);
			case "Sell" -> instance = new Sell(sessionData, data);
			case "Seek" -> instance = new Seek(sessionData, data);
			case "Travel" -> instance = new Travel(sessionData, data);
			case "Withdraw" -> instance = new Withdraw(sessionData, data);
		}
		return instance;
	}
}
