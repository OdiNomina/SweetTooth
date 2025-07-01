package com.github.sweettooth.model.events;

import com.github.sweettooth.model.games.GameData;

public final class ApplyInterest extends Event {
	ApplyInterest(GameData gameData){
		super(gameData);
	}
	
	@Override
	public String process(String stringInput, Integer integerInput, Double doubleInput) {
		StringBuffer answer = new StringBuffer();
		answer.append("Fällige Zinsen für gestern: Bank ")
			.append(String.format(gameSettings.getLocale(), "%.2f %s", bank.applyInterestToBalance(player), gameSettings.getCurrency()))
			.append(" | Kredithai ")
			.append(String.format(gameSettings.getLocale(), "%.2f %s", loanShark.applyInterestToBalance(player), gameSettings.getCurrency()));
		return answer.toString();
	}

	@Override
	public Answer processMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput) {
		throw new UnsupportedOperationException("The class " + this.getClass().getCanonicalName() + " don't support this operation.");
	}
}
