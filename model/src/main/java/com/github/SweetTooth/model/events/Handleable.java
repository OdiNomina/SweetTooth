package com.github.SweetTooth.model.events;

public interface Handleable {
	public record Answer(String... answer) {}
	
	String handle(String stringInput, Integer integerInput, Double doubleInput);
	Answer handleMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput);
}
