package com.github.sweettooth.model.events;

import com.github.sweettooth.model.api.GameModelInterface;
import com.github.sweettooth.model.api.Settings;
import com.github.sweettooth.model.api.SnackFactory;
import com.github.sweettooth.model.api.Snackable;
import com.github.sweettooth.model.characters.MoneyDealer;
import com.github.sweettooth.model.characters.Player;
import com.github.sweettooth.model.experiences.Experience;
import com.github.sweettooth.model.locations.Location;

public final class Travel extends Event {
	Travel(GameModelInterface gameData){
		super(gameData);
	}
	
	@Override
	public Answer processMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput) {
		double travelCosts = Settings.TRAVEL_COSTS;
		String payment = "";
		Player player = (Player)gameData.getPlayer();
		if(player.getCash() >= travelCosts) {
			player.reduceCash(travelCosts);
			payment = "Du zahlst bar.";
		}
		else {
			MoneyDealer bank = (MoneyDealer)gameData.getBank();
			bank.reduceClientsBalance(player, travelCosts);
			payment = "Du zahlst per Bankcard.";
		}
		player.setLocation(Location.valueOf(stringInput));
		Snackable.changeSnackPrices(SnackFactory.getDefaultSnacks(), player.getLocation());
		String infoChangePrices = "(Die Marktpreise haben sich geändert.)";
		StringBuffer eventAnswer = new StringBuffer();
		eventAnswer.append(" ")
					.append(Experience.randomExperience().process(player))
					.append(" ");
		return new Answer(eventAnswer.toString(), payment, infoChangePrices);
	}

	@Override
	public String process(String stringInput, Integer integerInput, Double doubleInput) {
		throw new UnsupportedOperationException("The class " + this.getClass().getCanonicalName() + " don't support this operation.");
	}
}
