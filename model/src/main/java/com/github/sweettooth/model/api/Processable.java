package com.github.sweettooth.model.api;

public interface Processable {
	//This nested record is used as a response container (... instead of an array).
	public record Answer(String answer1, String answer2, String answer3) {}
	
	String handle(String stringInput, Integer integerInput, Double doubleInput);
	Answer handleMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput);
}
