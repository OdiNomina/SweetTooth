package com.github.sweettooth.viewSwing.views;

import java.util.concurrent.Callable;
import javax.swing.JFrame;

import com.github.sweettooth.controllerSwing.api.IStartController;
import com.github.sweettooth.model.api.GameSettings;
import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.viewAPI.Observer;
import com.github.sweettooth.model.api.viewAPI.ScoreProvider;

public class StartFrameManager extends FrameManager implements Observer, Callable<JFrame> {
	SwingGUI gui;
	StartFrameDesign design;
	IStartController controller;
	
	private ScoreProvider scoreProvider;
	private ScoreTableModel scoreTableModel;
	
	StartFrameManager(SwingGUI gui, IGameData gameData, GameSettings settings) {
		super(gameData, settings);
		this.gui = gui;
		scoreProvider = gameData.getScoreProvider();
		design = new StartFrameDesign();
		controller = IStartController.getInstance();
		scoreTableModel = new ScoreTableModel();
	}
	
	@Override
	public JFrame call() throws Exception {
		info(String.format(Thread.currentThread().getName() + " is running: "+ getClass().getSimpleName() + " > " + Thread.currentThread().getStackTrace()[1].getMethodName()));
		
		JFrame startFrame = gui.getStartFrame();
		JFrame newFrame = null;
		
		scoreProvider.readScores(); // liest highscore datei im user verzeichnis
		if (startFrame == null || !startFrame.isDisplayable()) {
			newFrame = design.createDesign(scoreTableModel);
			initializeContent();
			updateContent();
			return newFrame;
		}
		return startFrame;
	}
	
	@Override
	public void update() {
		updateContent();
	}

	void addInputHandling() {
		try {
			design.playButton.addActionListener(controller.createButtonListener(gui.getStartFrame(), gui.getDealFrame()));
		}
		catch(RuntimeException ex)  {
			error("Error when adding input handling " + ex.getClass().getName(), ex);
		}
	}

	private void initializeContent() {
		try {
			design.frame.setTitle("Sweet Tooth");
			design.titleLabel.setText("Sweet Tooth");
			design.playButton.setText("Play");
		}
		catch(RuntimeException ex) { 
			error("Error when initializing content " + ex.getClass().getName(), ex);
		}
	}

	private void updateContent() {
		try {
			updating = true;
			scoreTableModel.updateScores(gameData.getScores());
		}
		catch(RuntimeException ex) {
			error("Error when updating content " + ex.getClass().getName(), ex);
		}
		finally {
            updating = false;
        }
	}
}
