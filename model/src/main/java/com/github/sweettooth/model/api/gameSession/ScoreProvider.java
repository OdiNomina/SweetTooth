package com.github.sweettooth.model.api.gameSession;

import java.util.List;

import com.github.sweettooth.model.api.settings.IGlobalSettings;
import com.github.sweettooth.model.gameSession.ScoreManager;

public interface ScoreProvider {
	
	/**
	 * This record is used as a DTO (data transfer object) because the data type used internally is not exported by the module.
	 */
	public record ScoreData(String name, String score) {}
	
	public static ScoreProvider createScoreProvider(IGlobalSettings globalSettings) {
		return new ScoreManager(globalSettings);
	}
	
	void addScore(String namePlayer, Double score);
	
	/**
	 * Returns a list of ScoreData objects containing {@code name<String>} and {@code score<String>}.
	 * @return a list of {@code ScoreData<String,String>}
	 */
	List<ScoreData> getScores();
	
	void readScoresFromFile();
	void writeScoresToFile();
}
