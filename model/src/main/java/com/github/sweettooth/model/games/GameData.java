package com.github.sweettooth.model.games;

import java.io.IOException;
import java.util.ArrayList;

import com.github.sweettooth.model.apiView.FinanciallyInteractable;
import com.github.sweettooth.model.apiView.Observer;
import com.github.sweettooth.model.apiView.Playable;
import com.github.sweettooth.model.apiView.Subject;

public class GameData implements Subject {
	final static double TRAVEL_COSTS = Double.valueOf(10.00);
	final static int GAME_DURATION_DAYS = Integer.valueOf(30);
	
	public static double getTravelCosts() {
		return TRAVEL_COSTS;
	}
	
	private int dayOfGame;
	private ArrayList<Observer> observers;
	private Playable player;
	private FinanciallyInteractable bank;
	private FinanciallyInteractable loanShark;
	private boolean gameOver;
	
	public GameData(Playable player, FinanciallyInteractable bank, FinanciallyInteractable loanShark) {
		dayOfGame = Integer.valueOf(1);
		observers = new ArrayList<>();
		this.player = player;
		this.bank = bank;
		this.loanShark = loanShark;
	}
	
	public FinanciallyInteractable getBank() {
		return bank;
	}
	
	public int getDayOfGame() {
		return dayOfGame;
	}
	
	public FinanciallyInteractable getLoanShark() {
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