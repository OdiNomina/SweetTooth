package com.github.sweettooth.model.session;

public record ScoreEntry(String name, Double score) implements Comparable<ScoreEntry> {
	
	public static ScoreEntry fromString(String line) {
		if (line == null) {
	        throw new IllegalArgumentException(ScoreEntry.class.getSimpleName() + " - Input line is null");
	    }
		
		try {
			String[] parts = line.split(" : ");
			if (parts.length != 2) {
				return new ScoreEntry(parts[0].strip(), null);
	        }
			return new ScoreEntry(parts[0].strip(), Double.parseDouble(parts[1].strip()));
		}
		catch (RuntimeException ex) {
	        throw new RuntimeException(ScoreEntry.class.getSimpleName() + " - Error while parsing.", ex);
	    }
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
