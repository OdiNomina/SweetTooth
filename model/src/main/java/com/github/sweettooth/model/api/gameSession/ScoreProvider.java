package com.github.sweettooth.model.api.gameSession;

import java.util.List;

import com.github.sweettooth.model.api.settings.IGlobalSettings;
import com.github.sweettooth.model.gameSession.ScoreManager;;

public interface ScoreProvider {
	
	public static ScoreProvider create(IGlobalSettings s) {
		return new ScoreManager(s);
	}
	
	void addScore(String namePlayer, Double score);
	
	/**
	 * Returns a list of ScoreData objects that are used as DTOs, since the data type used internally is not exported by the module.
	 * @return {@code List<ScoreData>}
	 */
	List<ScoreData> getScores();
	
	void readPersistentScore();
	
	void writePersistentScore();
	
	
	/**
	 * This nested record is used as a DTO because the data type used internally is not exported by the module.
	 */
	public record ScoreData(String name, Double score) {}
}
