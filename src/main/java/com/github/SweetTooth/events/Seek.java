package com.github.SweetTooth.events;

import com.github.SweetTooth.snacks.CandyFactory;
import com.github.SweetTooth.characters.Player;
import com.github.SweetTooth.snacks.Candy;

final class Seek extends Event {
	@Override
	public String handle() {
		if(!isAtHometown())
			return notAtHometown;

		if(isTooMuchToCarry(integerInput))
			return "Soviel kannst du nicht tragen.";
		Candy snack = new CandyFactory().valueOf(stringInput);
		Player player = game.getPlayer();
		player.addSnack(snack, player.getCandies(), integerInput);
		player.removeSnack(snack, player.getCandyStash(), integerInput);
		return "Eingepackt";
	}

	@Override
	public Answer handleMultipleAnswers() {
		throw new UnsupportedOperationException("The class " + this.getClass().getCanonicalName() + " don't support this operation.");
	}
}
