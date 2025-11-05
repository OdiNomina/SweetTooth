package com.github.sweettooth.model.api;

import java.io.IOException;

import com.github.sweettooth.model.api.characters.IMoneyDealer;
import com.github.sweettooth.model.api.settings.IGlobalSettings;
import com.github.sweettooth.model.gameRounds.GameRound;
import com.github.sweettooth.shared.api.util.Observer;

public interface IGameRound {
	
	static IGameRound create(IGlobalSettings s) {
		return new GameRound(s);
	}
	
	IMoneyDealer getBank();
	int getDayOfGame();
	IMoneyDealer getLoanShark();
	void increaseDayOfGame(int d) throws IOException;
	boolean isExitButtonClicked();
	boolean isGameOver();
	void notifyObservers();
	void registerObserver(Observer o);
	void setExitButtonClicked(boolean b);
	void unregisterObserver(Observer o);
}
