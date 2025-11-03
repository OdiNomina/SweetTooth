package com.github.sweettooth.model.games;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.settings.IGlobalSettings;
import com.github.sweettooth.model.characters.Bank;
import com.github.sweettooth.model.characters.LoanShark;
import com.github.sweettooth.model.characters.MoneyDealer;
import com.github.sweettooth.model.commons.GlobalSettings;
import com.github.sweettooth.model.commons.InternSettings;
import com.github.sweettooth.shared.api.util.Observer;

public class GameData implements IGameData {
	private final List<Observer> observers;
	private final MoneyDealer bank;
	private final MoneyDealer loanShark;
	
	private int dayOfGame;
	private boolean gameOver;
	private boolean exitButtonClicked;
	
	public GameData(IGlobalSettings globalSettings) {
		GlobalSettings settings = (GlobalSettings)globalSettings;
		observers = new ArrayList<>();
		
		bank = new Bank(settings);
		loanShark = new LoanShark(settings);
		dayOfGame = Integer.valueOf(1);
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
	
	@Override
	public void unregisterObserver(Observer o) {
		observers.remove(o);
	}
}