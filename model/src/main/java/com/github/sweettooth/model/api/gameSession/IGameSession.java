package com.github.sweettooth.model.api.gameSession;

import com.github.sweettooth.model.api.characters.IPlayer;
import com.github.sweettooth.model.api.settings.IGameSettings;
import com.github.sweettooth.model.api.settings.IGlobalSettings;
import com.github.sweettooth.model.gameSession.GameSession;
import com.github.sweettooth.shared.api.util.Observer;

public interface IGameSession {
	
	public static IGameSession create(ScoreProvider scoreProvider, IGlobalSettings globalSettings, IGameSettings gameSettings, String namePlayer) {
		return new GameSession(scoreProvider, globalSettings, gameSettings, namePlayer);
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
