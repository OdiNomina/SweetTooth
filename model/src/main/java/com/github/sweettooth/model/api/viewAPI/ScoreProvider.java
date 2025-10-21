package com.github.sweettooth.model.api.viewAPI;

import java.util.List;
import java.util.Locale;

import com.github.sweettooth.model.session.ScoreManager;

public interface ScoreProvider {
	
	public record ScoreData(String name, String score) {}
	
	public static ScoreProvider createScoreProvider() {
		return new ScoreManager();
	}
	
	void addScore(String name, Double score, Locale locale);
	List<ScoreData> getScores(Locale locale);
	void readScoresFromFile(Locale locale);
}
