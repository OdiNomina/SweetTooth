package com.github.sweettooth.model.api;

import java.io.IOException;

import com.github.sweettooth.model.api.characters.IMoneyDealer;
import com.github.sweettooth.model.api.settings.IGlobalSettings;
import com.github.sweettooth.model.gameRounds.GameRound;
import com.github.sweettooth.shared.api.util.Observer;

public interface IGameRound {
	// --- launcher
	
	static IGameRound createGameData(IGlobalSettings globalSettings) {
		return new GameRound(globalSettings);
	}
	
	// --- controller
	
	void increaseDayOfGame(int numberOfDays) throws IOException;
	void notifyObservers();
	void setExitButtonClicked(boolean exitButtonClicked);
	
	// --- view
	
	public default IMoneyDealer bank() {
		return (IMoneyDealer) ((GameRound)this).getBank();
	}

	public default IMoneyDealer loanShark() {
		return (IMoneyDealer) ((GameRound)this).getLoanShark();
	}
	
	int getDayOfGame();
	boolean isExitButtonClicked();
	void registerObserver(Observer o);
	void unregisterObserver(Observer o);
	
	// --- controller and view
	
	boolean isGameOver();
}
