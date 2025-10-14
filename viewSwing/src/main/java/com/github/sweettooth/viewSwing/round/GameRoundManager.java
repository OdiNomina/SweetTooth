package com.github.sweettooth.viewSwing.round;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.ISessionData;
import com.github.sweettooth.shared.api.FrameNavigator;

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
    	activeGameData = IGameData.createGameData(sessionData.getSettings());
        dealFrameManager = new DealFrameManager(navigator, sessionData, activeGameData);

        SwingUtilities.invokeLater(() -> {
            dealFrameManager.createFrame();
            dealFrameManager.getDealFrame().setVisible(true);
        });
    }
    
    public void disposeActiveGame() {
        sessionData.getPlayer().reset();
    	
    	if (dealFrameManager != null) {
            JFrame dealFrame = dealFrameManager.getDealFrame();
        	if (dealFrame != null) {
        		if(SwingUtilities.isEventDispatchThread()) {
                	dealFrame.dispose();
        		}
        		else {
        			try {
                        SwingUtilities.invokeAndWait(dealFrame::dispose);
                    }
        			catch (Exception ex) {
                        ex.printStackTrace();
                    }
        		}
            }
        	if (dealFrameManager != null) {
        		activeGameData.unregisterObserver(dealFrameManager);
        	}
            dealFrameManager = null;
            activeGameData = null;
        }
    }
}
