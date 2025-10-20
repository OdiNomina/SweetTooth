package com.github.sweettooth.viewSwing.round;

import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.ISessionData;
import com.github.sweettooth.shared.api.FrameNavigator;
import com.github.sweettooth.viewSwing.commons.Tools;

public class GameRoundManager {
    private final FrameNavigator navigator;
	private final ISessionData sessionData;
    private IGameData activeGameData;
	private DealFrameManager dealFrameManager;

    public GameRoundManager(FrameNavigator navigator, ISessionData sessionData) {
        this.navigator = navigator;
    	this.sessionData = sessionData;
    }
	
    public void startRound() {
    	sessionData.getPlayer().reset();
    	activeGameData = IGameData.createGameData(sessionData.getSettings());
    	
    	// First game round
    	if (dealFrameManager == null) {
            dealFrameManager = new DealFrameManager(navigator, sessionData, activeGameData);
            dealFrameManager.createFrame();
        }
    	// Further rounds: Reinitialize existing window
    	else
            dealFrameManager.resetWithNewGame(activeGameData);
    	
    	Tools.runOnEDT( () -> {
    			dealFrameManager.getDealFrame().setVisible(true);
    		});
    }
}
