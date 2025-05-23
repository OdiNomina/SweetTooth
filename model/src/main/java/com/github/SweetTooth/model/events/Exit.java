package com.github.sweettooth.model.events;

final class Exit extends Event {
	@Override
	public String handle(String stringInput, Integer integerInput, Double doubleInput) {
		return "";
	}

	@Override
	public Answer handleMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput) {
		throw new UnsupportedOperationException("The class " + this.getClass().getCanonicalName() + " don't support this operation.");
	}
}
