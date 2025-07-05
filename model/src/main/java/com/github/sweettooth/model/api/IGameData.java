package com.github.sweettooth.model.api;

import java.io.IOException;

import com.github.sweettooth.model.api.viewAPI.IMoneyDealer;
import com.github.sweettooth.model.api.viewAPI.IPlayer;
import com.github.sweettooth.model.api.viewAPI.Observer;
import com.github.sweettooth.model.games.GameData;

public interface IGameData {
	// --- launcher
	
	static IGameData createGameData() {
		return new GameData();
	}
	
	IGameData initialize(GameSettings modelSettings, String namePlayer) throws NullPointerException;
	
	// --- controller
	
	void notifyObservers();
	void increaseDayOfGame(int numberOfDays) throws IOException;
	void setExitButtonClicked(boolean exitButtonClicked);
	
	// --- view
	
	public default IPlayer player() {
		return (IPlayer) ((GameData)this).getPlayer();
	}
	
	public default IMoneyDealer bank() {
		return (IMoneyDealer) ((GameData)this).getBank();
	}
	
	public default IMoneyDealer loanShark() {
		return (IMoneyDealer) ((GameData)this).getLoanShark();
	}
	
	void registerObserver(Observer o);
	int getDayOfGame();
	boolean isExitButtonClicked();
	
	// --- controller and view
	
	boolean isGameOver();
}
