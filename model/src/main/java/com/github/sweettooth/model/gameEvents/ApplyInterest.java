package com.github.sweettooth.model.gameEvents;

import java.util.Currency;
import java.util.Locale;

import com.github.sweettooth.model.gameRounds.GameRound;
import com.github.sweettooth.model.gameSession.GameSession;

public final class ApplyInterest extends Event {
	ApplyInterest(GameSession sessionData, GameRound gameData){
		super(sessionData, gameData);
	}
	
	@Override
	public String[] process(String stringInput, Integer integerInput, Double doubleInput) {
		String[] returnArray = new String[1];
		Locale locale = globalSettings.getLocale();
		
		StringBuffer answer = new StringBuffer();
		answer.append("Fällige Zinsen für gestern: Bank ")
			.append(String.format(locale, "%.2f %s", bank.applyInterestToBalance(player), Currency.getInstance(locale).getSymbol()))
			.append(" | Kredithai ")
			.append(String.format(locale, "%.2f %s", loanShark.applyInterestToBalance(player), Currency.getInstance(locale).getSymbol()));
		
		returnArray[0] = answer.toString();
		return returnArray;
	}
}
