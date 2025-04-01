package com.github.SweetTooth.events;

import com.github.SweetTooth.characters.MoneyDealer;
import com.github.SweetTooth.characters.Player;

final class Lend extends Event {
	@Override
	public String handle() {
		if(!isAtHometown()) 
			return notAtHometown;

		double amount = doubleInput != 0 ? doubleInput : integerInput;
		MoneyDealer loanShark =  game.getLoanShark();
		Player player = game.getPlayer();
		if(loanShark.getBalance(player) < 0)
			return "Kannst du vergessen Alder.";
		player.addCash(amount);
		loanShark.reduceClientsBalance(player, amount);
		return "Hier, lass dir ruhig Zeit... aber nicht ZU lange!";
	}

	@Override
	public Answer handleMultipleAnswers() {
		throw new UnsupportedOperationException("The class " + this.getClass().getCanonicalName() + " don't support this operation.");
	}
}
