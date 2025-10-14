package com.github.sweettooth.model.api.viewAPI;

import java.util.List;

import com.github.sweettooth.model.session.ScoreManager;

public interface ScoreProvider {
	
	public record ScoreData(String name, Double score) {}
	
	public static ScoreProvider createScoreProvider() {
		return new ScoreManager();
	}
	
	void addScore(String name, Double score);
	List<ScoreData> getScores();
	void readScoresFromFile();
}
