package com.github.sweettooth.viewSwing.session;

import com.github.sweettooth.model.api.gameSession.IGameSession;
import com.github.sweettooth.shared.api.gameControl.GameNavigator;
import com.github.sweettooth.shared.api.gameControl.WindowNavigator;
import com.github.sweettooth.shared.api.logging.Loggable;
import com.github.sweettooth.viewSwing.api.SwingDisplay;
import com.github.sweettooth.viewSwing.commons.SwingExecutor;
import com.github.sweettooth.viewSwing.commons.Tools;
import com.github.sweettooth.viewSwing.round.GameRoundManager;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class SwingGUI implements SwingDisplay, Loggable, GameNavigator, WindowNavigator {
	private final Logger logger;
	private IGameSession sessionData;
	private GameRoundManager gameRoundManager;
	private StartFrameManager startFrameManager;
	
	public SwingGUI(IGameSession sessionData) throws NullPointerException {
		logger = Logger.getLogger(SwingGUI.class.getName());
		this.sessionData = sessionData;
        gameRoundManager = new GameRoundManager(this, this, sessionData);
        startFrameManager = new StartFrameManager(this, this, sessionData);
	}
	
    @Override
	public Logger getLogger() {	
		return logger;
	}

	@Override
	public void exitGame() {
		SwingExecutor.getInstance().shutdownAndAwait(2, TimeUnit.SECONDS);
	}

	@Override
	public void hideStartWindow() {
		Tools.runOnEDT( () -> {
				startFrameManager.getStartFrame().setVisible(false);
			});
	}
	
	@Override
	public void newDealGame() {
		gameRoundManager.startRound();
	}

	@Override
	public void showStartWindow() {
		Tools.runOnEDT( () -> {
				startFrameManager.getStartFrame().setVisible(true);
			});
	}
	
	@Override
	public void showDealWindow() {
		Tools.runOnEDT( () -> {
				gameRoundManager.getDealFrameManager().getDealFrame().setVisible(true);
			});
	}
	
	@Override
	public void start() {
		startFrameManager.createFrame();
		showStartWindow();
	}

	@Override
	public void writeDealScores() {
		SwingExecutor.getInstance().submit( () -> 
				sessionData.getScoreProvider().writePersistentScore()
			);
	}
}
