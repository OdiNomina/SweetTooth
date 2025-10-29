package com.github.sweettooth.model.session;

import java.util.ArrayList;
import java.util.List;

import com.github.sweettooth.model.api.ISessionData;
import com.github.sweettooth.model.api.settings.IGameSettings;
import com.github.sweettooth.model.api.settings.IGlobalSettings;
import com.github.sweettooth.model.api.viewAPI.Observer;
import com.github.sweettooth.model.api.viewAPI.ScoreProvider;
import com.github.sweettooth.model.api.viewAPI.ScoreProvider.ScoreData;
import com.github.sweettooth.model.characters.Player;
import com.github.sweettooth.model.commons.GameSettings;
import com.github.sweettooth.model.commons.GlobalSettings;

public class SessionData implements ISessionData {
	private final List<Observer> observers;
	private final ScoreManager scoreManager;
    private final GlobalSettings globalSettings;
    private final GameSettings gameSettings;
    private final Player player;

    public SessionData(ScoreProvider scoreProvider, IGlobalSettings globalSettings, IGameSettings gameSettings, String playerName) {
    	observers = new ArrayList<>();
    	
    	this.scoreManager = (ScoreManager)scoreProvider;
        this.globalSettings = (GlobalSettings)globalSettings;
        this.gameSettings = (GameSettings)gameSettings;
        player = new Player(playerName);
    }

    @Override
    public void addScore(Double score) {
    	scoreManager.addScore(player.getName(), score, globalSettings.getLocale());
    }
    
    @Override
	public GameSettings getGameSettings() {
	    return gameSettings;
	}
    
    @Override
	public GlobalSettings getGlobalSettings() {
	    return globalSettings;
	}

	@Override
    public Player getPlayer() {
	    return player;
	}

	@Override
    public ScoreManager getScoreProvider() {
        return scoreManager;
    }
    
    @Override
	public List<ScoreData> getScores() {
        return scoreManager.getScores(globalSettings.getLocale());
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