package com.github.sweettooth.viewSwing.round;

import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.ISessionData;
import com.github.sweettooth.shared.api.gameControl.GameNavigator;
import com.github.sweettooth.shared.api.gameControl.WindowNavigator;

public class GameRoundManager {
	private final GameNavigator gameNavigator;
	private final WindowNavigator windowNavigator;
	private final ISessionData sessionData;
    private IGameData activeGameData;
	private DealFrameManager dealFrameManager;

    public GameRoundManager(GameNavigator gameNavigator, WindowNavigator navigator, ISessionData sessionData) {
        this.gameNavigator = gameNavigator;
    	this.windowNavigator = navigator;
    	this.sessionData = sessionData;
    }
	
    public DealFrameManager getDealFrameManager() {
    	return dealFrameManager;
    }
    
    public void startRound() {
    	sessionData.getPlayer().reset();
    	activeGameData = IGameData.createGameData(sessionData.getGlobalSettings());
    	
    	// First game round
    	if (dealFrameManager == null) {
            dealFrameManager = new DealFrameManager(gameNavigator, windowNavigator, sessionData, activeGameData);
            dealFrameManager.createFrame();
        }
    	// Further rounds: Reinitialize existing window
    	else
            dealFrameManager.resetWithNewGame(activeGameData);
    }
}
