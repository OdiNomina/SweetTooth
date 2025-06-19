package com.github.sweettooth.model.api;

import java.io.IOException;

import com.github.sweettooth.model.games.GameData;

public interface GameModelInterface {
	// --- launcher
	static GameModelInterface createGameModel() {
		return new GameData();
	}
	void initialize(Settings settings, String namePlayer);
	
	// --- controller
//	void start();
//	void stop();
	void notifyObservers();
	void increaseDayOfGame(int numberOfDays) throws IOException;
	void setGameOver(boolean gameOver);
	
	// --- view
	void registerObserver(Observer o);
	void removeObserver(Observer o);
	Interrogable getBank();
	Interrogable getLoanShark();
	Playable getPlayer();
	int getDayOfGame();
	boolean isGameOver();
}
