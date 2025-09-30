package com.github.sweettooth.model.games;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.viewAPI.Observer;
import com.github.sweettooth.model.api.viewAPI.ScoreProvider.ScoreData;
import com.github.sweettooth.model.api.GameSettings;
import com.github.sweettooth.model.characters.Bank;
import com.github.sweettooth.model.characters.LoanShark;
import com.github.sweettooth.model.characters.MoneyDealer;
import com.github.sweettooth.model.characters.Player;
import com.github.sweettooth.model.commons.InternSettings;

public class GameData implements IGameData {
	private ArrayList<Observer> observers;
	private ScoreManager scoreManager;
	private int dayOfGame;
	private boolean gameOver;
	private boolean exitButtonClicked;
	
	private Player player;
	private MoneyDealer bank;
	private MoneyDealer loanShark;
	private GameSettings modelSettings;
	
	public GameData() {
		observers = new ArrayList<>();
		scoreManager = new ScoreManager();
		dayOfGame = Integer.valueOf(1);
	}
	
	public void addScore(Double score) {
		scoreManager.addScore(player.getName(), score);
	}
	
	@Override
	public IGameData initialize(GameSettings modelSettings, String namePlayer) throws NullPointerException {
		this.modelSettings = Objects.requireNonNull(modelSettings);
		player = new Player(namePlayer);
		bank = new Bank(modelSettings);
		loanShark = new LoanShark(modelSettings);
		return this;
	}
	
	public MoneyDealer getBank() {
		return bank;
	}
	
	@Override
	public int getDayOfGame() {
		return dayOfGame;
	}
	
	public MoneyDealer getLoanShark() {
		return loanShark;
	}
	
	
	public Player getPlayer() {
		return player;
	}
	
	public List<ScoreData> getScores() {
        return scoreManager.getScores();
    }

	public GameSettings getSettings() {
		return modelSettings;
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
		observers.forEach(Observer::update);
	}

	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
	}
	
	@Override
	public void setExitButtonClicked(boolean exitButtonClicked) {
		this.exitButtonClicked = exitButtonClicked;
	}
}