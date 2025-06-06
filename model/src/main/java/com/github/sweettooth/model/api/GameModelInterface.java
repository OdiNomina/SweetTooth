package com.github.sweettooth.model.api;

import java.io.IOException;

import com.github.sweettooth.model.games.GameData;

public interface GameModelInterface extends Subject {
	// --- launcher
	static GameModelInterface createGameModel() {
		return new GameData();
	}
	
	// --- controller
//	void initialize();
//	void start();
//	void stop();
	void gameDataChanged();
	void increaseDayOfGame(int numberOfDays) throws IOException;
	void setGameOver(boolean gameOver);
	
	// --- view
	Interrogable getBank();
	Interrogable getLoanShark();
	Playable getPlayer();
	int getDayOfGame();
	boolean isGameOver();
}
