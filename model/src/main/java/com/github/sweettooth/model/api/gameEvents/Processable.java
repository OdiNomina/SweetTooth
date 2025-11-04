package com.github.sweettooth.model.api.gameEvents;

public interface Processable {
	/**
	 * Returns an array of strings, which usually contains only one event answer.<br>
	 * If necessary, multiple answers can also be returned.
	 * @param stringInput a string to be processed
	 * @param integerInput an integer to be processed
	 * @param doubleInput a double to be processed
	 * @return a String[] containing the processed output
	 */
	String[] process(String stringInput, Integer integerInput, Double doubleInput);
}
