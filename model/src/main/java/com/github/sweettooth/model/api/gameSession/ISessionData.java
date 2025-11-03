package com.github.sweettooth.model.api.gameSession;

import com.github.sweettooth.model.api.characters.IPlayer;
import com.github.sweettooth.model.api.settings.IGameSettings;
import com.github.sweettooth.model.api.settings.IGlobalSettings;
import com.github.sweettooth.model.gameSession.SessionData;
import com.github.sweettooth.shared.api.util.Observer;

public interface ISessionData {
	
	public static ISessionData createSessionData(ScoreProvider scoreProvider, IGlobalSettings globalSettings, IGameSettings gameSettings, String playerName) {
		return new SessionData(scoreProvider, globalSettings, gameSettings, playerName);
	}
	
	IGameSettings getGameSettings();
	IGlobalSettings getGlobalSettings();
	IPlayer getPlayer();
	ScoreProvider getScoreProvider();
	void notifyObservers();
	void registerObserver(Observer o);
	void setNamePlayer(String name);
	void unregisterObserver(Observer o);
}
