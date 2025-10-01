package com.github.sweettooth.viewSwing.views;

import com.github.sweettooth.model.api.GameSettings;
import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.shared.api.Loggable;
import com.github.sweettooth.viewSwing.api.SwingDisplay;

import java.util.Objects;
import java.util.logging.Logger;

import javax.swing.JFrame;

public class SwingGUI implements SwingDisplay, Loggable {
	private final Logger logger;
	private TabbedFrameManager tabbedPane;
	
	public SwingGUI(IGameData gameData) throws NullPointerException {
		logger = Logger.getLogger(SwingGUI.class.getName());
        tabbedPane = new TabbedFrameManager(Objects.requireNonNull(gameData), new GameSettings());
		
		gameData.registerObserver(tabbedPane);
	}
	
    @Override
	public Logger getLogger() {	
		return logger;
	}
    
	@Override
	public SwingDisplay initialize(GameSettings settings) throws NullPointerException {
		tabbedPane.setGameSettings(Objects.requireNonNull(settings));
		return this;
	}
	
	@Override
	public void run() {
		info(String.format(Thread.currentThread().getName() + " 'tabbed frame' is running."));
		try {
			JFrame tabbedFrame = tabbedPane.createTabbedFrame();
			tabbedFrame.setVisible(true);
		} catch (Exception ex) {
			error("Error when running " + ex.getClass().getName(), ex);
		}
	}
}
