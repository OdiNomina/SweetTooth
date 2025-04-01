package com.github.SweetTooth.events;

import com.github.SweetTooth.characters.IMoneyDealer;
import com.github.SweetTooth.characters.IPlayer;

final class Lend extends Event implements IEvent{
	@Override
	public String handleEvent() {
		if(!isAtHometown()) 
			return notAtHometown;

		double amount = doubleInput != 0 ? doubleInput : integerInput;
		IMoneyDealer loanShark =  game.getLoanShark();
		IPlayer player = game.getPlayer();
		if(loanShark.getBalance(player) < 0)
			return "Kannst du vergessen Alder.";
		player.addCash(amount);
		loanShark.reduceClientsBalance(player, amount);
		return "Hier, lass dir ruhig Zeit... aber nicht ZU lange!";
	}
}
