package com.github.SweetTooth.events;

import com.github.SweetTooth.characters.IMoneyDealer;

final class ApplyInterest extends Event implements IEvent {
	@Override
	public String handleEvent() {
		StringBuffer answer = new StringBuffer();
		answer.append("Fällige Zinsen: Bank-Zinsen ")
			.append(String.format("%.2f %s", game.getBank().applyInterestToBalance(game.getPlayer()), IMoneyDealer.getCurrency()))
			.append(" | Kredithai-Zinsen ")
			.append(String.format("%.2f %s", game.getLoanShark().applyInterestToBalance(game.getPlayer()), IMoneyDealer.getCurrency()));
		return answer.toString();
	}
	
}
