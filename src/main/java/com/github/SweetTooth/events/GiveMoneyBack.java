package com.github.SweetTooth.events;

import com.github.SweetTooth.characters.IMoneyDealer;

final class GiveMoneyBack extends Event implements IEvent {
	@Override
	public String handleEvent() {
		if(!isAtHometown()) 
			return notAtHometown;
		return giveBack();
	}
	
	private String giveBack() {
		double amount = doubleInput != 0 ? doubleInput : integerInput;
		amount = Math.round(amount * 100) / 100.00;
		IMoneyDealer loanShark =  IMoneyDealer.create("LoanShark");
		if(amount + loanShark.getBalance(player) > 0)
			return "Digga was gibst du mir soviel Geld? Hab ich dir gar nicht gegeben.";
		player.reduceCash(amount);
		loanShark.increaseClientsBalance(player, amount);
		if(loanShark.getBalance(player) < 0)
			return "Da fehlt aber noch was!";
		return "Geht klar Alder, bis zum nächsten Mal.";
	}
}
