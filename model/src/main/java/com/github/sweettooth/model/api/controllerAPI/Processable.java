package com.github.sweettooth.model.api.controllerAPI;

public interface Processable {
	//This nested record is used as a response container (... no special reason).
	public record Answer(String answer1, String answer2, String answer3) {}
	
	/**
	 * 
	 * @param stringInput
	 * @param integerInput
	 * @param doubleInput
	 * @return Returns a string as the answer.
	 */
	String process(String stringInput, Integer integerInput, Double doubleInput);
	
	/**
	 * 
	 * @param stringInput
	 * @param integerInput
	 * @param doubleInput
	 * @return Returns a record containing multiple strings as answers.
	 */
	Answer processMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput);
}
