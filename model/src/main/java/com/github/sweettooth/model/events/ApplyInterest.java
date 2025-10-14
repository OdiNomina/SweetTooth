package com.github.sweettooth.model.events;

import java.util.Currency;
import java.util.Locale;

import com.github.sweettooth.model.games.GameData;
import com.github.sweettooth.model.session.SessionData;

public final class ApplyInterest extends Event {
	ApplyInterest(SessionData sessionData, GameData gameData){
		super(sessionData, gameData);
	}
	
	@Override
	public String process(String stringInput, Integer integerInput, Double doubleInput) {
		Locale locale = settings.getLocale();
		StringBuffer answer = new StringBuffer();
		answer.append("Fällige Zinsen für gestern: Bank ")
			.append(String.format(locale, "%.2f %s", bank.applyInterestToBalance(player), Currency.getInstance(locale).getSymbol()))
			.append(" | Kredithai ")
			.append(String.format(locale, "%.2f %s", loanShark.applyInterestToBalance(player), Currency.getInstance(locale).getSymbol()));
		return answer.toString();
	}

	@Override
	public Answer processMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput) {
		throw new UnsupportedOperationException("The class " + this.getClass().getCanonicalName() + " don't support this operation.");
	}
}
