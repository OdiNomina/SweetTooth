package com.github.sweettooth.model.games;

import java.io.IOException;
import java.util.ArrayList;

import com.github.sweettooth.model.api.GameInterface;

import com.github.sweettooth.model.apiView.Interrogable;
import com.github.sweettooth.model.apiView.Observer;
import com.github.sweettooth.model.apiView.Playable;
import com.github.sweettooth.model.apiView.Settings;
import com.github.sweettooth.model.characters.Bank;
import com.github.sweettooth.model.characters.LoanShark;
import com.github.sweettooth.model.characters.Player;

public class GameData implements GameInterface {
	private ArrayList<Observer> observers;
	private int dayOfGame;
	private Playable player;
	private Interrogable bank;
	private Interrogable loanShark;
	private boolean gameOver;
	
	public GameData() {
		observers = new ArrayList<>();
		dayOfGame = Integer.valueOf(1);
		player = new Player(null);
		bank = new Bank();
		loanShark = new LoanShark();
	}
	
	public void gameDataChanged() {
		notifyObservers();
	}
	
	@Override
	public Interrogable getBank() {
		return bank;
	}
	
	@Override
	public int getDayOfGame() {
		return dayOfGame;
	}
	
	@Override
	public Interrogable getLoanShark() {
		return loanShark;
	}
	
	@Override
	public Playable getPlayer() {
		return player;
	}

	@Override
	public void increaseDayOfGame(int numberOfDays) throws IOException {
		if(dayOfGame < Settings.GAME_DURATION_DAYS)
			dayOfGame += numberOfDays;
		else
			gameOver = true;
	}
	
	@Override
	public boolean isGameOver() {
		return gameOver;
	}

	public void notifyObservers() {
		for(Observer o : observers) {
			o.update();
		}
	}

	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}
	
	@Override
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	
}