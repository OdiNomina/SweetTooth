package com.github.sweettooth.viewSwing.views;

import com.github.sweettooth.model.api.GameSettings;
import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.shared.api.Loggable;
import com.github.sweettooth.viewSwing.api.SwingDisplay;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.Objects;
import java.util.logging.Logger;

import javax.swing.JFrame;

public class SwingGUI implements SwingDisplay, Loggable {
	private final Logger logger;
	private ExecutorService executor = Executors.newCachedThreadPool();
	
	private DealFrameManager dealFrameManager;
	private StartFrameManager startFrameManager;
	private JFrame startFrame;
	private JFrame dealFrame;
	
	public SwingGUI(IGameData gameData) throws NullPointerException {
		logger = Logger.getLogger(SwingGUI.class.getName());
        dealFrameManager = new DealFrameManager(this, Objects.requireNonNull(gameData), new GameSettings());
        startFrameManager = new StartFrameManager(this, Objects.requireNonNull(gameData), new GameSettings());
		
		gameData.registerObserver(dealFrameManager);
	}
	
	public JFrame getDealFrame() {
		return dealFrame;
	}
	
    @Override
	public Logger getLogger() {	
		return logger;
	}
    
	public JFrame getStartFrame() {
		return startFrame;
	}

	@Override
	public SwingDisplay initialize(GameSettings settings) throws NullPointerException {
		dealFrameManager.setGameSettings(Objects.requireNonNull(settings));
		startFrameManager.setGameSettings(Objects.requireNonNull(settings));
		return this;
	}
	
	@Override
	public void run() {
		info(String.format(Thread.currentThread().getName() + " is running: "+ getClass().getSimpleName() + " > " + Thread.currentThread().getStackTrace()[1].getMethodName()));
		
		try {
			Future<JFrame> startFuture = executor.submit(startFrameManager);
			Future<JFrame> dealFuture = executor.submit(dealFrameManager);
			
			startFrame = startFuture.get();
			dealFrame = dealFuture.get();
			
			startFrameManager.addInputHandling();
			startFrame.setVisible(true);
		}
		catch (Exception ex) {
			error("Error when running " + ex.getClass().getName(), ex);
		}
	}
}
