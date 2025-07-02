package com.github.sweettooth.model.api.controllerAPI;

public interface Processable {
	//This nested record is used as a response container (... no special reason).
	public record Answer(String answer1, String answer2, String answer3) {}
	
	String process(String stringInput, Integer integerInput, Double doubleInput);
	Answer processMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput);
}
