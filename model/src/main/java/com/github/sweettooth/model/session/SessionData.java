package com.github.sweettooth.model.session;

import java.util.ArrayList;
import java.util.List;

import com.github.sweettooth.model.api.GameSettings;
import com.github.sweettooth.model.api.ISessionData;
import com.github.sweettooth.model.api.viewAPI.Observer;
import com.github.sweettooth.model.api.viewAPI.ScoreProvider.ScoreData;
import com.github.sweettooth.model.characters.Player;

public class SessionData implements ISessionData {
	private final List<Observer> observers;
	private final ScoreManager scoreManager;
    private final GameSettings settings;
    private final Player player;

    public SessionData(ScoreManager scoreManager, GameSettings settings, String playerName) {
    	observers = new ArrayList<>();
    	
    	this.scoreManager = scoreManager;
        this.settings = settings;
        player = new Player(playerName);
    }

    public Player getPlayer() {
	    return player;
	}

	@Override
    public ScoreManager getScoreProvider() {
        return scoreManager;
    }
    
    @Override
	public List<ScoreData> getScores() {
        return scoreManager.getScores();
    }

    @Override
    public GameSettings getSettings() {
        return settings;
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
	public void setNamePlayer(String name) {
		player.setName(name);
	}
	
	@Override
	public void unregisterObserver(Observer o) {
		observers.remove(o);
	}
}