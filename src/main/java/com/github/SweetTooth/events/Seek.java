package com.github.SweetTooth.events;

import com.github.SweetTooth.snacks.CandyFactory;
import com.github.SweetTooth.snacks.Candy;

final class Seek extends Event implements IEvent {
	@Override
	public String handleEvent() {
		if(!isAtHometown())
			return notAtHometown;
		return seek();
	}
	
	public String seek() {
		if(isTooMuchToCarry(integerInput))
			return "Soviel kannst du nicht tragen.";
		Candy snack = new CandyFactory().valueOf(stringInput);
		player.addSnack(snack, player.getCandies(), integerInput);
		player.removeSnack(snack, player.getCandyStash(), integerInput);
		return "Eingepackt";
	}
}
