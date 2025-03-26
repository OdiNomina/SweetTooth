package com.github.SweetTooth.events;

import com.github.SweetTooth.characters.IMoneyDealer;
import com.github.SweetTooth.characters.IPlayer;
import com.github.SweetTooth.experiences.Experience;
import com.github.SweetTooth.locations.Location;
import com.github.SweetTooth.snacks.CandyFactory;
import com.github.SweetTooth.snacks.Snackable;

final class Travel extends Event implements IEvent {
	@Override
	public Answer handleEventMultipleAnswers() {
		return travel();
	}
	
	private Answer travel() {
		double travelCosts = IPlayer.getTravelCosts();
		String payment = "";
		if(player.getCash() >= travelCosts) {
			player.reduceCash(travelCosts);
			payment = "Du zahlst bar.";
		}
		else {
			IMoneyDealer.create("Bank").reduceClientsBalance(player, travelCosts);
			payment = "Du zahlst per Bankcard.";
		}
		player.setLocation(Location.valueOf(stringInput));
		Snackable.changeCandyPrices(new CandyFactory().getDefaultSnacks());
		String infoChangePrices = "Die Marktpreise der Süßigkeiten haben sich geändert.";
		StringBuffer eventAnswer = new StringBuffer();
		eventAnswer.append(" ")
					.append(Experience.randomExperience().process(player))
					.append(" ");
		return new Answer(eventAnswer.toString(), payment, infoChangePrices);
	}
}
