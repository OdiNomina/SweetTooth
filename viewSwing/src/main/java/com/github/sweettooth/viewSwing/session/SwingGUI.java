package com.github.sweettooth.viewSwing.session;

import com.github.sweettooth.model.api.ISessionData;
import com.github.sweettooth.shared.api.FrameNavigator;
import com.github.sweettooth.shared.api.Loggable;
import com.github.sweettooth.viewSwing.api.SwingDisplay;
import com.github.sweettooth.viewSwing.commons.Tools;
import com.github.sweettooth.viewSwing.round.GameRoundManager;

import java.util.logging.Logger;

public class SwingGUI implements SwingDisplay, Loggable, FrameNavigator {
	private final Logger logger;
	private GameRoundManager gameRoundManager;
	private StartFrameManager startFrameManager;
	
	public SwingGUI(ISessionData sessionData) throws NullPointerException {
		logger = Logger.getLogger(SwingGUI.class.getName());
	
        gameRoundManager = new GameRoundManager(this, sessionData);
        startFrameManager = new StartFrameManager(this, sessionData);
	}
	
    @Override
	public Logger getLogger() {	
		return logger;
	}
	
	@Override
	public void run() {
		info(String.format(Thread.currentThread().getName() + " is running: "+ getClass().getSimpleName() + " > " + Thread.currentThread().getStackTrace()[1].getMethodName()));
		
		startFrameManager.createFrame();
		showStartFrame();
	}

	@Override
	public void hideStartFrame() {
		Tools.runOnEDT( () -> {
				startFrameManager.getStartFrame().setVisible(false);
			});
	}
	
	@Override
	public void showStartFrame() {
		Tools.runOnEDT( () -> {
				startFrameManager.getStartFrame().setVisible(true);
			});
	}
	
	@Override
	public void startNewGameRound() {
		gameRoundManager.startRound();
	}
}
