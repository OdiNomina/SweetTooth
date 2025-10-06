package com.github.sweettooth.model.api;

import java.io.IOException;
import java.util.List;

import com.github.sweettooth.model.api.viewAPI.IMoneyDealer;
import com.github.sweettooth.model.api.viewAPI.IPlayer;
import com.github.sweettooth.model.api.viewAPI.Observer;
import com.github.sweettooth.model.api.viewAPI.ScoreProvider;
import com.github.sweettooth.model.api.viewAPI.ScoreProvider.ScoreData;
import com.github.sweettooth.model.games.GameData;

public interface IGameData {
	// --- launcher
	
	static IGameData createGameData() {
		return new GameData();
	}
	
	IGameData initialize(GameSettings modelSettings, String namePlayer) throws NullPointerException;
	
	// --- controller
	
	void increaseDayOfGame(int numberOfDays) throws IOException;
	void notifyObservers();
	void setExitButtonClicked(boolean exitButtonClicked);
	
	// --- view
	
	public default IMoneyDealer bank() {
		return (IMoneyDealer) ((GameData)this).getBank();
	}

	public default IMoneyDealer loanShark() {
		return (IMoneyDealer) ((GameData)this).getLoanShark();
	}
	
	public default IPlayer player() {
		return (IPlayer) ((GameData)this).getPlayer();
	}
	
	int getDayOfGame();
	List<ScoreData> getScores();
	ScoreProvider getScoreProvider();
	boolean isExitButtonClicked();
	void registerObserver(Observer o);
	
	// --- controller and view
	
	boolean isGameOver();
}
