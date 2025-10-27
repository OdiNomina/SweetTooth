package com.github.sweettooth.controllerSwing.dealWindowListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.github.sweettooth.controllerSwing.controllers.DealController;
import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.ISessionData;

public class DealWindowCloseListener extends WindowAdapter {
	private final DealController dealController;
	private final ISessionData sessionData;
	private final IGameData gameData;
	
	public DealWindowCloseListener(DealController dealController, ISessionData sessionData, IGameData gameData) {
		this.dealController = dealController;
		this.sessionData = sessionData;
		this.gameData = gameData;
	}
	
	@Override
    public void windowClosing(WindowEvent e) {
        sessionData.addScore(sumUpScore());
        dealController.getGameNavigator().writeDealScores();
        
        sessionData.notifyObservers();
        dealController.getWindowNavigator().showStartWindow();
    }
	
	private double sumUpScore() {
    	double cash = sessionData.getPlayer().getCash();
		double loan = gameData.loanShark().clientsBalance(sessionData.getPlayer());
		double balance = gameData.bank().clientsBalance(sessionData.getPlayer());
		return cash + loan + balance;
	}
}
