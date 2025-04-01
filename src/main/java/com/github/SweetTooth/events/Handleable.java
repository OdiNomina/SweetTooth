package com.github.SweetTooth.events;

public interface Handleable {
	public record Answer(String... answer) {}
	
	String handle();
	Answer handleMultipleAnswers();
}
