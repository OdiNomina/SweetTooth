package com.github.sweettooth.model.api;

import java.util.List;

import com.github.sweettooth.model.api.viewAPI.IPlayer;
import com.github.sweettooth.model.api.viewAPI.Observer;
import com.github.sweettooth.model.api.viewAPI.ScoreProvider;
import com.github.sweettooth.model.api.viewAPI.ScoreProvider.ScoreData;
import com.github.sweettooth.model.session.ScoreManager;
import com.github.sweettooth.model.session.SessionData;

public interface ISessionData {
	
	public static ISessionData createSessionData(ScoreProvider scoreProvider, GameSettings settings, String playerName) {
		return new SessionData((ScoreManager)scoreProvider, settings, playerName);
	}
	
	IPlayer getPlayer();
	List<ScoreData> getScores();
	ScoreProvider getScoreProvider();
	GameSettings getSettings();
	
	// --- controller
	
	void notifyObservers();
	
	// --- view
	
	void registerObserver(Observer o);
	void unregisterObserver(Observer o);
}
