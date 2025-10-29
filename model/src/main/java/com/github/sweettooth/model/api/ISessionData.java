package com.github.sweettooth.model.api;

import java.util.List;

import com.github.sweettooth.model.api.settings.IGameSettings;
import com.github.sweettooth.model.api.settings.IGlobalSettings;
import com.github.sweettooth.model.api.viewAPI.IPlayer;
import com.github.sweettooth.model.api.viewAPI.Observer;
import com.github.sweettooth.model.api.viewAPI.ScoreProvider;
import com.github.sweettooth.model.api.viewAPI.ScoreProvider.ScoreData;
import com.github.sweettooth.model.session.SessionData;

public interface ISessionData {
	
	public static ISessionData createSessionData(ScoreProvider scoreProvider, IGlobalSettings globalSettings, IGameSettings gameSettings, String playerName) {
		return new SessionData(scoreProvider, globalSettings, gameSettings, playerName);
	}
	
	IPlayer getPlayer();
	List<ScoreData> getScores();
	ScoreProvider getScoreProvider();
	IGameSettings getGameSettings();
	IGlobalSettings getGlobalSettings();
	
	// --- controller
	
	void addScore(Double score);
	void notifyObservers();
	void setNamePlayer(String name);
	
	// --- view
	
	void registerObserver(Observer o);
	void unregisterObserver(Observer o);
}
