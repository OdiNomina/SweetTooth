package com.github.SweetTooth.game;

import java.io.IOException;

import com.github.SweetTooth.characters.Bank;
import com.github.SweetTooth.characters.IMoneyDealer;
import com.github.SweetTooth.characters.IPlayer;
import com.github.SweetTooth.characters.LoanShark;
import com.github.SweetTooth.characters.Player;
import com.github.SweetTooth.gui.GUI;
import com.github.SweetTooth.gui.GUIManager;

public class Game {
	final static double TRAVEL_COSTS = Double.valueOf(10.00);
	private int dayOfGame = Integer.valueOf(1);
	private IPlayer player;
	private IMoneyDealer bank;
	private IMoneyDealer loanShark;
	
	public static double getTravelCosts() {
		return TRAVEL_COSTS;
	}
	
	Game(IPlayer player, IMoneyDealer bank, IMoneyDealer loanShark) {
		this.player = player;
		this.bank = bank;
		this.loanShark = loanShark;
	}
	
	public IMoneyDealer getBank() {
		return bank;
	}
	
	public int getDayOfGame() {
		return dayOfGame;
	}
	
	public IMoneyDealer getLoanShark() {
		return loanShark;
	}
	
	public IPlayer getPlayer() {
		return player;
	}
	
	public void increaseDayOfGame(int numberOfDays, GUI gui) throws IOException {
		if(dayOfGame < 30)
			dayOfGame += numberOfDays;
		else
			gui.disableComponents(this);
	}
	
	public static void main(String[] args) throws InterruptedException {	
		try {
			Game game = new Game(new Player(null), new Bank(), new LoanShark());

	    	GUIManager guiManager = new GUIManager();
	        guiManager.start(game);
	    }
		catch(RuntimeException e) {
			e.printStackTrace();
		}
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}