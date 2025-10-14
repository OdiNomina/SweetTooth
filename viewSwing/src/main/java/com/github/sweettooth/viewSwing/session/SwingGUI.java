package com.github.sweettooth.viewSwing.session;

import com.github.sweettooth.model.api.ISessionData;
import com.github.sweettooth.shared.api.FrameNavigator;
import com.github.sweettooth.shared.api.Loggable;
import com.github.sweettooth.viewSwing.api.SwingDisplay;
import com.github.sweettooth.viewSwing.round.GameRoundManager;

import java.util.logging.Logger;
import javax.swing.SwingUtilities;

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
		
		try {
			 SwingUtilities.invokeLater(() -> {
		            startFrameManager.createFrame();
		            startFrameManager.getStartFrame().setVisible(true);
		        });
		}
		catch (Exception ex) {
			error("Error when running " + ex.getClass().getName(), ex);
		}
	}

	@Override
	public void hideStartFrame() {
		startFrameManager.getStartFrame().setVisible(false);
	}
	
	@Override
	public void showStartFrame() {
		startFrameManager.getStartFrame().setVisible(true);
	}
	
	@Override
	public void showDealFrameNewGameRound() {
		gameRoundManager.disposeActiveGame();
		gameRoundManager.startRound();
	}
}
