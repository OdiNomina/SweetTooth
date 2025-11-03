package com.github.sweettooth.model.api.gameSession;

import java.util.List;

import com.github.sweettooth.model.api.settings.IGlobalSettings;
import com.github.sweettooth.model.gameSession.ScoreManager;

public interface ScoreProvider {
	
	public record ScoreData(String name, String score) {}
	
	public static ScoreProvider createScoreProvider(IGlobalSettings globalSettings) {
		return new ScoreManager(globalSettings);
	}
	
	void addScore(String namePlayer, Double score);
	List<ScoreData> getScores();
	void readScoresFromFile();
	void writeScoresToFile();
}
