package com.github.sweettooth.model.events;

import com.github.sweettooth.model.experiences.Experience;
import com.github.sweettooth.model.gameRounds.GameRound;
import com.github.sweettooth.model.gameSession.GameSession;
import com.github.sweettooth.model.locations.Location;
import com.github.sweettooth.model.settings.InternSettings;
import com.github.sweettooth.model.snacks.SnackFactory;

public final class Travel extends Event {
	Travel(GameSession sessionData, GameRound gameData){
		super(sessionData, gameData);
	}
	
	@Override
	public Answer processMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput) {
		double travelCosts = InternSettings.TRAVEL_COSTS;
		String payment = "";
		if(player.getCash() >= travelCosts) {
			player.reduceCash(travelCosts);
			payment = "Du zahlst bar.";
		}
		else {
			bank.reduceClientsBalance(player, travelCosts);
			payment = "Du zahlst per Bankcard.";
		}
		player.setLocation(Location.valueOf(stringInput));
		SnackFactory snackFactory = (SnackFactory)gameSettings.getSnackFactory();
		snackFactory.changeSnackPrices(player.getCurrentLocation());
		String infoChangePrices = "(Die Marktpreise haben sich geändert.)";
		StringBuffer eventAnswer = new StringBuffer();
		eventAnswer.append(" ")
					.append(Experience.randomExperience(gameSettings).process(player))
					.append(" ");
		return new Answer(eventAnswer.toString(), payment, infoChangePrices);
	}

	@Override
	public String process(String stringInput, Integer integerInput, Double doubleInput) {
		throw new UnsupportedOperationException("The class " + this.getClass().getCanonicalName() + " don't support this operation.");
	}
}
