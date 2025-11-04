package com.github.sweettooth.controllerSwing.dealWindowListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.github.sweettooth.controllerSwing.controllers.DealController;
import com.github.sweettooth.model.api.IGameRound;
import com.github.sweettooth.model.api.gameSession.IGameSession;

public class DealWindowCloseListener extends WindowAdapter {
	private final DealController dealController;
	private final IGameSession sessionData;
	private final IGameRound gameData;
	
	public DealWindowCloseListener(DealController dealController, IGameSession sessionData, IGameRound gameData) {
		this.dealController = dealController;
		this.sessionData = sessionData;
		this.gameData = gameData;
	}
	
	@Override
    public void windowClosing(WindowEvent e) {
        sessionData.getScoreProvider().addScore(sessionData.getPlayer().getName(), sumUpScore());
        dealController.getGameNavigator().writeDealScores();
        
        sessionData.notifyObservers();
        dealController.getWindowNavigator().showStartWindow();
    }
	
	private double sumUpScore() {
    	double cash = sessionData.getPlayer().getCash();
		double loan = gameData.loanShark().getClientsBalance(sessionData.getPlayer());
		double balance = gameData.bank().getClientsBalance(sessionData.getPlayer());
		return cash + loan + balance;
	}
}
