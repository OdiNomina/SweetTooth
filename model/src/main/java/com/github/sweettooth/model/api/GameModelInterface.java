package com.github.sweettooth.model.api;

import java.io.IOException;

import com.github.sweettooth.model.api.viewAPI.IMoneyDealer;
import com.github.sweettooth.model.api.viewAPI.IPlayer;
import com.github.sweettooth.model.api.viewAPI.Observer;
import com.github.sweettooth.model.games.GameData;

public interface GameModelInterface {
	// --- launcher
	static GameModelInterface createGameModel() {
		return new GameData();
	}
	GameModelInterface initialize(ModelSettings modelSettings, String namePlayer) throws NullPointerException;
	
	// --- controller
	void notifyObservers();
	void increaseDayOfGame(int numberOfDays) throws IOException;
	void setExitButtonClicked(boolean exitButtonClicked);
	void setGameOver(boolean gameOver);
	
	// --- view
	void registerObserver(Observer o);
	void removeObserver(Observer o);
	IMoneyDealer getBank();
	IMoneyDealer getLoanShark();
	IPlayer getPlayer();
	int getDayOfGame();
	boolean isExitButtonClicked();
	boolean isGameOver();
}
