package com.github.sweettooth.model.games;

import java.io.IOException;
import java.util.ArrayList;

import com.github.sweettooth.model.api.Interactable;
import com.github.sweettooth.model.api.Observer;
import com.github.sweettooth.model.api.Playable;
import com.github.sweettooth.model.api.Subject;

public class GameData implements Subject {
	final static double TRAVEL_COSTS = Double.valueOf(10.00);
	final static int GAME_DURATION_DAYS = Integer.valueOf(30);
	
	public static double getTravelCosts() {
		return TRAVEL_COSTS;
	}
	
	private int dayOfGame;
	private ArrayList<Observer> observers;
	private Playable player;
	private Interactable bank;
	private Interactable loanShark;
	private boolean gameOver;
	
	public GameData(Playable player, Interactable bank, Interactable loanShark) {
		dayOfGame = Integer.valueOf(1);
		observers = new ArrayList<>();
		this.player = player;
		this.bank = bank;
		this.loanShark = loanShark;
	}
	
	public Interactable getBank() {
		return bank;
	}
	
	public int getDayOfGame() {
		return dayOfGame;
	}
	
	public Interactable getLoanShark() {
		return loanShark;
	}
	
	public Playable getPlayer() {
		return player;
	}
	
	
	public void increaseDayOfGame(int numberOfDays) throws IOException {
		if(dayOfGame < GAME_DURATION_DAYS)
			dayOfGame += numberOfDays;
		else
			gameOver = true;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	public boolean isGameOver() {
		return gameOver;
	}
	
	public void gameDataChanged() {
		notifyObservers();
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
	public void notifyObservers() {
		for(Observer o : observers) {
			o.update();
		}
	}
}