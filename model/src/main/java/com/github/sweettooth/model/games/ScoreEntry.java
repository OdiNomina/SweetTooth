package com.github.sweettooth.model.games;

public record ScoreEntry(String name, Double score) implements Comparable<ScoreEntry> {
	
	public static ScoreEntry fromString(String line) {
		String[] parts = line.split(" : ");
		return new ScoreEntry(parts[0].strip(), Double.parseDouble(parts[1].strip()));
	}

	@Override
	public int compareTo(ScoreEntry other) {
		return Double.compare(other.score, this.score);
	}
	
	@Override
	public String toString() {
		return name + " : " + score;
	}
}
