package com.github.SweetTooth.model.events;

import com.github.SweetTooth.model.characters.Player;
import com.github.SweetTooth.model.experiences.Experience;
import com.github.SweetTooth.model.games.Game;
import com.github.SweetTooth.model.locations.Location;
import com.github.SweetTooth.model.snacks.CandyFactory;
import com.github.SweetTooth.model.snacks.Snackable;

final class Travel extends Event {
	@Override
	public Answer handleMultipleAnswers(String stringInput, Integer integerInput, Double doubleInput) {
		double travelCosts = Game.getTravelCosts();
		String payment = "";
		Player player = game.getPlayer();
		if(player.getCash() >= travelCosts) {
			player.reduceCash(travelCosts);
			payment = "Du zahlst bar.";
		}
		else {
			game.getBank().reduceClientsBalance(player, travelCosts);
			payment = "Du zahlst per Bankcard.";
		}
		player.setLocation(Location.valueOf(stringInput));
		Snackable.changeCandyPrices(new CandyFactory().getDefaultSnacks(), player.getLocation());
		String infoChangePrices = "Die Marktpreise haben sich geändert.";
		StringBuffer eventAnswer = new StringBuffer();
		eventAnswer.append(" ")
					.append(Experience.randomExperience().process(player))
					.append(" ");
		return new Answer(eventAnswer.toString(), payment, infoChangePrices);
	}

	@Override
	public String handle(String stringInput, Integer integerInput, Double doubleInput) {
		throw new UnsupportedOperationException("The class " + this.getClass().getCanonicalName() + " don't support this operation.");
	}
}
