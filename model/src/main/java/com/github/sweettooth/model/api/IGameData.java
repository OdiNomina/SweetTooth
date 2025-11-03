package com.github.sweettooth.model.api;

import java.io.IOException;

import com.github.sweettooth.model.api.characters.IMoneyDealer;
import com.github.sweettooth.model.api.settings.IGlobalSettings;
import com.github.sweettooth.model.games.GameData;
import com.github.sweettooth.shared.api.util.Observer;

public interface IGameData {
	// --- launcher
	
	static IGameData createGameData(IGlobalSettings globalSettings) {
		return new GameData(globalSettings);
	}
	
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
	
	int getDayOfGame();
	boolean isExitButtonClicked();
	void registerObserver(Observer o);
	void unregisterObserver(Observer o);
	
	// --- controller and view
	
	boolean isGameOver();
}
