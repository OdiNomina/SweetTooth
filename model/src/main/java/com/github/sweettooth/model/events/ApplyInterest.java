package com.github.sweettooth.model.events;

import com.github.sweettooth.model.api.Settings;
import com.github.sweettooth.model.characters.MoneyDealer;
import com.github.sweettooth.model.characters.Player;
import com.github.sweettooth.model.games.GameData;

public final class ApplyInterest extends Event {
	ApplyInterest(GameData gameData){
		super(gameData);
	}
	
	@Override
	public String handle(String stringInput, Integer integerInput, Double doubleInput) {
		MoneyDealer bank = (MoneyDealer)gameData.getBank();
		MoneyDealer loanShark = (MoneyDealer)gameData.getLoanShark();
		Player player = (Player)gameData.getPlayer();
		
		StringBuffer answer = new StringBuffer();
		answer.append("Fällige Zinsen: Bank-Zinsen ")
			.append(String.format("%.2f %s", bank.applyInterestToBalance(player), Settings.CURRENCY))
			.append(" | Kredithai-Zinsen ")
			.append(String.format("%.2f %s", loanShark.applyInterestToBalance(player), Settings.CURRENCY));
		return answer.toString();
	}

	@Override
	public Answer handleMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput) {
		throw new UnsupportedOperationException("The class " + this.getClass().getCanonicalName() + " don't support this operation.");
	}
}
