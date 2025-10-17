package com.github.sweettooth.viewSwing.session;

import java.util.logging.Logger;

import javax.swing.JFrame;

import com.github.sweettooth.controllerSwing.api.IStartController;
import com.github.sweettooth.model.api.ISessionData;
import com.github.sweettooth.model.api.viewAPI.Observer;
import com.github.sweettooth.model.api.viewAPI.ScoreProvider;
import com.github.sweettooth.shared.api.FrameNavigator;
import com.github.sweettooth.shared.api.Loggable;
import com.github.sweettooth.shared.api.UpdateGuard;
import com.github.sweettooth.viewSwing.commons.Tools;

public class StartFrameManager implements Observer, UpdateGuard, Loggable {
	private final Logger logger;
	private final StartFrameDesign design;
	private final IStartController startController;
	private final ScoreProvider scoreProvider;
	private final ScoreTableModel scoreTableModel;
	
	private JFrame startFrame;
	private boolean updating;
	
	StartFrameManager(FrameNavigator frameNavigator, ISessionData sessionData) {
		logger = Logger.getLogger(StartFrameManager.class.getName());
		design = new StartFrameDesign();
		startController = IStartController.getInstance(frameNavigator);
		scoreProvider = sessionData.getScoreProvider();
		scoreTableModel = new ScoreTableModel();
		
		sessionData.registerObserver(this);
	}
	
	void createFrame() {
		scoreProvider.readScoresFromFile();
		
		startFrame = design.createFrame(scoreTableModel);
		initializeUI();
		updateUI();
		addInputHandling();
	}
	
	@Override
	public Logger getLogger() {
		return logger;
	}
	
	JFrame getStartFrame() {
		return startFrame;
	}
	
	@Override
    public boolean isUpdating() {
        return updating;
    }
	
	@Override
	public void update() {
		updateUI();
	}

	private void addInputHandling() {
		Tools.runOnEDT( () -> {
			try {
				design.playButton.addActionListener(startController.createButtonListener());
			}
			catch(RuntimeException ex)  {
				error("Error when adding input handling " + ex.getClass().getName(), ex);
			}
		});
	}

	private void initializeUI() {
		Tools.runOnEDT( () -> {
			try {
				startFrame.setTitle("Sweet Tooth");
				design.titleLabel.setText("Sweet Tooth");
				design.playButton.setText("Play");
			}
			catch(RuntimeException ex) { 
				error("Error when initializing content " + ex.getClass().getName(), ex);
			}
		});
	}

	private void updateUI() {
		Tools.runOnEDT( () -> {
			updating = true;
			try {
				scoreTableModel.updateScores(scoreProvider.getScores());
			}
			catch(RuntimeException ex) {
				error("Error when updating content " + ex.getClass().getName(), ex);
			}
			finally {
	            updating = false;
	        }
		});
	}
}
