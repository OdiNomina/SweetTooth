package com.github.sweettooth.viewSwing.session;

import java.util.logging.Logger;

import javax.swing.JFrame;

import com.github.sweettooth.controllerSwing.api.IStartController;
import com.github.sweettooth.model.api.gameSession.ISessionData;
import com.github.sweettooth.model.api.gameSession.ScoreProvider;
import com.github.sweettooth.shared.api.gameControl.GameNavigator;
import com.github.sweettooth.shared.api.gameControl.WindowNavigator;
import com.github.sweettooth.shared.api.logging.Loggable;
import com.github.sweettooth.shared.api.util.Observer;
import com.github.sweettooth.shared.api.util.UpdateGuard;
import com.github.sweettooth.viewSwing.commons.SwingExecutor;
import com.github.sweettooth.viewSwing.commons.Tools;

public class StartFrameManager implements Observer, UpdateGuard, Loggable {
	private final Logger logger;
	private final StartFrameDesign design;
	private final IStartController startController;
	private final ScoreProvider scoreProvider;
	private final ScoreTableModel scoreTableModel;
	private final ISessionData sessionData;
	
	private JFrame startFrame;
	private boolean updating;
	
	StartFrameManager(GameNavigator gameNavigator, WindowNavigator windowNavigator, ISessionData sessionData) {
		logger = Logger.getLogger(StartFrameManager.class.getName());
		this.sessionData = sessionData;
		design = new StartFrameDesign();
		startController = IStartController.getInstance(gameNavigator, windowNavigator);
		scoreProvider = sessionData.getScoreProvider();
		scoreTableModel = new ScoreTableModel();
		
		sessionData.registerObserver(this);
	}
	
	void createFrame() {
		startFrame = design.createFrame(scoreTableModel);
		initializeUI();
		addInputHandling();
		SwingExecutor.getInstance().submit(
				() -> scoreProvider.readScoresFromFile(sessionData.getGlobalSettings().getLocale()),
				() -> updateUI()
			);
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
				design.namePlayer.addActionListener(startController.createTextFieldListener(sessionData));
				design.playButton.addActionListener(startController.createButtonListener(sessionData, design.namePlayer));
				design.frame.addWindowListener(startController.createWindowCloseListener());
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
				design.namePlayerLabel.setText("Wer spielt?");
				design.namePlayer.setText(sessionData.getPlayer().getName());
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
				scoreTableModel.updateScores(sessionData.getScores());
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
