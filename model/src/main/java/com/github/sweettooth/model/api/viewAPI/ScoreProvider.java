package com.github.sweettooth.model.api.viewAPI;

import java.util.List;

public interface ScoreProvider {
	
	public record ScoreData(String name, Double score) {}
	
	List<ScoreData> getScores();
	public void readScores();
}
