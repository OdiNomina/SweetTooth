package com.github.sweettooth.model.api;

import java.io.IOException;

import com.github.sweettooth.model.apiView.Interrogable;
import com.github.sweettooth.model.apiView.Observer;
import com.github.sweettooth.model.apiView.Playable;

public interface GameInterface {
	// controller
//	void initialize();
//	void start();
//	void stop();
	void increaseDayOfGame(int numberOfDays) throws IOException;
	void setGameOver(boolean gameOver);
	
	// view
	Interrogable getBank();
	Interrogable getLoanShark();
	Playable getPlayer();
	int getDayOfGame();
	boolean isGameOver();
	
	void registerObserver(Observer o);
	void removeObserver(Observer o);
}
