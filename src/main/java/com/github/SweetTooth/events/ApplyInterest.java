package com.github.SweetTooth.events;

import com.github.SweetTooth.characters.IMoneyDealer;

final class ApplyInterest extends Event implements IEvent {
	@Override
	public String handleEvent() {
			return applyInterest();
	}
	
	private String applyInterest() {
		StringBuffer answer = new StringBuffer();
		answer.append("Fällige Zinsen: Bank-Zinsen ")
			.append(String.format("%.2f %s", IMoneyDealer.create("Bank").applyInterestToBalance(player), IMoneyDealer.getCurrency()))
			.append(" | Kredithai-Zinsen ")
			.append(String.format("%.2f %s", IMoneyDealer.create("LoanShark").applyInterestToBalance(player), IMoneyDealer.getCurrency()));
		return answer.toString();
	}
}
