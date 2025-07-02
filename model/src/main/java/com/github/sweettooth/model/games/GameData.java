package com.github.sweettooth.model.games;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import com.github.sweettooth.model.api.GameModelInterface;
import com.github.sweettooth.model.api.IMoneyDealer;
import com.github.sweettooth.model.api.Observer;
import com.github.sweettooth.model.api.IPlayer;
import com.github.sweettooth.model.api.ModelSettings;
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
	
	private IPlayer player;
	private MoneyDealer bank;
	private MoneyDealer loanShark;
	private ModelSettings modelSettings;
	
	public GameData() {
		observers = new ArrayList<>();
		dayOfGame = Integer.valueOf(1);
	}
	
	@Override
	public GameModelInterface initialize(ModelSettings modelSettings, String namePlayer) throws NullPointerException {
		this.modelSettings = Objects.requireNonNull(modelSettings);
		player = new Player(namePlayer);
		bank = new Bank(modelSettings);
		loanShark = new LoanShark(modelSettings);
		return this;
	}
	
	@Override
	public IMoneyDealer getBank() {
		return bank;
	}
	
	@Override
	public int getDayOfGame() {
		return dayOfGame;
	}
	
	@Override
	public IMoneyDealer getLoanShark() {
		return loanShark;
	}
	
	public ModelSettings getSettings() {
		return modelSettings;
	}
	
	@Override
	public IPlayer getPlayer() {
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