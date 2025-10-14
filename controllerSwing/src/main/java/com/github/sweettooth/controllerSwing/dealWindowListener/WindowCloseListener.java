package com.github.sweettooth.controllerSwing.dealWindowListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.ISessionData;
import com.github.sweettooth.shared.api.FrameNavigator;

public class WindowCloseListener extends WindowAdapter {
	private final FrameNavigator frameNavigator;
	private final ISessionData sessionData;
	private final IGameData gameData;
	
	public WindowCloseListener(FrameNavigator frameNavigator, ISessionData sessionData, IGameData gameData) {
		this.frameNavigator = frameNavigator;
		this.sessionData = sessionData;
		this.gameData = gameData;
	}
	
	@Override
    public void windowClosing(WindowEvent e) {
        sessionData.getScoreProvider().addScore(sessionData.getPlayer().getName(), sumUpScore());
        sessionData.notifyObservers();
        frameNavigator.showStartFrame();
    }
	
	private double sumUpScore() {
    	double cash = sessionData.getPlayer().getCash();
		double loan = gameData.loanShark().clientsBalance(sessionData.getPlayer());
		double balance = gameData.bank().clientsBalance(sessionData.getPlayer());
		return cash + loan + balance;
	}
}
