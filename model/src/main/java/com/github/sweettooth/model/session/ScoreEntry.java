package com.github.sweettooth.model.session;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public record ScoreEntry(String name, Double score) implements Comparable<ScoreEntry> {
	
	public static ScoreEntry fromLocaleString(String line, Locale locale) throws RuntimeException {
		if (line == null) {
	        throw new IllegalArgumentException(ScoreEntry.class.getSimpleName() + " - Input line is null");
	    }
		
		try {
			//Regex \s* means zero or more whitespace
			String[] parts = line.split("\\s*:\\s*");
			if (parts.length != 2)
				return new ScoreEntry(parts[0].strip(), 0.0);
	        
			NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
			return new ScoreEntry(parts[0].strip(), numberFormat.parse(parts[1].strip()).doubleValue());
		}
		catch (ParseException | RuntimeException ex) {
			throw new RuntimeException(ScoreEntry.class.getSimpleName() + " - Error while parsing line: \"" + line + "\"", ex);
		}
	}

	@Override
	public int compareTo(ScoreEntry other) {
		return Double.compare(other.score, this.score);
	}
	
	/*
	 * %[flags][.precision]conversion
	 * Flag ',': The result will include locale-specific grouping separators.
	 * Conversion 'f': The result is formatted as a decimal number.
	 */
	String toLocaleString(Locale locale) {
		return String.format(locale, "%s : %,.2f", name, Math.round(score*100)/100.0);
	}
}
