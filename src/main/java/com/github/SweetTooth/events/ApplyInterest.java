package com.github.SweetTooth.events;

import com.github.SweetTooth.characters.MoneyDealer;

final class ApplyInterest extends Event {
	@Override
	public String handle() {
		StringBuffer answer = new StringBuffer();
		answer.append("Fällige Zinsen: Bank-Zinsen ")
			.append(String.format("%.2f %s", game.getBank().applyInterestToBalance(game.getPlayer()), MoneyDealer.getCurrency()))
			.append(" | Kredithai-Zinsen ")
			.append(String.format("%.2f %s", game.getLoanShark().applyInterestToBalance(game.getPlayer()), MoneyDealer.getCurrency()));
		return answer.toString();
	}

	@Override
	public Answer handleMultipleAnswers() {
		throw new UnsupportedOperationException("The class " + this.getClass().getCanonicalName() + " don't support this operation.");
	}
}
