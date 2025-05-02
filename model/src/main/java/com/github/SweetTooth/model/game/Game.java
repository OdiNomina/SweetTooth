package com.github.SweetTooth.model.game;

import java.io.IOException;

import com.github.SweetTooth.gui.PanelContentMain;
import com.github.SweetTooth.model.characters.Bank;
import com.github.SweetTooth.model.characters.LoanShark;
import com.github.SweetTooth.model.characters.MoneyDealer;
import com.github.SweetTooth.model.characters.Player;
import com.github.SweetTooth.gui.GUIManager;

public class Game {
	final static double TRAVEL_COSTS = Double.valueOf(10.00);
	private int dayOfGame = Integer.valueOf(1);
	private Player player;
	private MoneyDealer bank;
	private MoneyDealer loanShark;
	
	public Game(Player player, MoneyDealer bank, MoneyDealer loanShark) {
		this.player = player;
		this.bank = bank;
		this.loanShark = loanShark;
	}
	
	public static double getTravelCosts() {
		return TRAVEL_COSTS;
	}
	
	public MoneyDealer getBank() {
		return bank;
	}
	
	public int getDayOfGame() {
		return dayOfGame;
	}
	
	public MoneyDealer getLoanShark() {
		return loanShark;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void increaseDayOfGame(int numberOfDays, PanelContentMain panelContentMain) throws IOException {
		if(dayOfGame < 30)
			dayOfGame += numberOfDays;
		else
			panelContentMain.disableComponents();
	}
}