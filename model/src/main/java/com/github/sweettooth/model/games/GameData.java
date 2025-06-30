package com.github.sweettooth.model.games;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import com.github.sweettooth.model.api.GameModelInterface;
import com.github.sweettooth.model.api.Interrogable;
import com.github.sweettooth.model.api.Observer;
import com.github.sweettooth.model.api.Playable;
import com.github.sweettooth.model.api.Settings;
import com.github.sweettooth.model.characters.Bank;
import com.github.sweettooth.model.characters.LoanShark;
import com.github.sweettooth.model.characters.MoneyDealer;
import com.github.sweettooth.model.characters.Player;
import com.github.sweettooth.model.commons.InternSettings;

public class GameData implements GameModelInterface {
	private ArrayList<Observer> observers;
	private int dayOfGame;
	private boolean gameOver;
	private boolean exitButtonClicked;
	
	private Playable player;
	private MoneyDealer bank;
	private MoneyDealer loanShark;
	private Settings settings;
	
	public GameData() {
		observers = new ArrayList<>();
		dayOfGame = Integer.valueOf(1);
	}
	
	@Override
	public GameModelInterface initialize(Settings settings, String namePlayer) throws NullPointerException {
		this.settings = Objects.requireNonNull(settings);
		player = new Player(namePlayer);
		bank = new Bank(settings);
		loanShark = new LoanShark(settings);
		return this;
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
	
	public Settings getSettings() {
		return settings;
	}
	
	@Override
	public Playable getPlayer() {
		return player;
	}

	@Override
	public void increaseDayOfGame(int numberOfDays) throws IOException {
		if(dayOfGame < InternSettings.GAME_DURATION_DAYS)
			dayOfGame += numberOfDays;
		else
			gameOver = true;
	}
	
	@Override
	public boolean isExitButtonClicked() {
		return exitButtonClicked;
	}
	
	@Override
	public boolean isGameOver() {
		return gameOver;
	}

	@Override
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
	public void setExitButtonClicked(boolean exitButtonClicked) {
		this.exitButtonClicked = exitButtonClicked;
	}
	
	@Override
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
}