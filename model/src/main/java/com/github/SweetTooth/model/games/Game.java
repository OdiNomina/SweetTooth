package com.github.SweetTooth.model.games;

import java.io.IOException;
import java.util.ArrayList;

import com.github.SweetTooth.model.characters.MoneyDealer;
import com.github.SweetTooth.model.characters.Player;
import com.github.SweetTooth.model.events.Observer;

public class Game {
	final static double TRAVEL_COSTS = Double.valueOf(10.00);
	final static int GAME_DURATION_DAYS = Integer.valueOf(30);
	
	public static double getTravelCosts() {
		return TRAVEL_COSTS;
	}
	
	private ArrayList<Observer> views = new ArrayList<>();
	private int dayOfGame = Integer.valueOf(1);
	private Player player;
	private MoneyDealer bank;
	private MoneyDealer loanShark;
	private boolean isGameOver;
	
	public Game(Player player, MoneyDealer bank, MoneyDealer loanShark) {
		this.player = player;
		this.bank = bank;
		this.loanShark = loanShark;
	}
	
	public MoneyDealer getBank() {
		return bank;
	}
	
	public int getDayOfGame() {
		return dayOfGame;
	}
	
	public MoneyDealer getLoanShark() {
		return loanShark;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public ArrayList<Observer> getViews() {
		return views;
	}
	
	public void increaseDayOfGame(int numberOfDays) throws IOException {
		if(dayOfGame < GAME_DURATION_DAYS)
			dayOfGame += numberOfDays;
		else
			isGameOver = true;
	}

	public boolean isGameOver() {
		return isGameOver;
	}
}