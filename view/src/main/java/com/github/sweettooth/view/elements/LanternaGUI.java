package com.github.sweettooth.view.elements;

import java.io.IOException;

import com.github.sweettooth.controller.api.ControllerInterface;
import com.github.sweettooth.model.api.GameModelInterface;
import com.github.sweettooth.model.api.Observer;
import com.github.sweettooth.model.api.Settings;
import com.github.sweettooth.view.api.DisplayElement;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor.RGB;
import com.googlecode.lanterna.graphics.SimpleTheme;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.SeparateTextGUIThread;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

public class LanternaGUI implements Observer, DisplayElement {
	private Screen screen;
	private MultiWindowTextGUI multiWindowTextGUI;
	private SeparateTextGUIThread guiThread;
	
	private GameModelInterface gameModel;
	private ControllerInterface controller;
	private Settings settings;
	
	private ViewPanel mainViewPanel;
	
	public LanternaGUI(GameModelInterface gameModel, ControllerInterface controller, Settings settings) throws IOException {
		DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(127, 60));
		screen = terminalFactory.createScreen();
		screen.startScreen();
		multiWindowTextGUI = new MultiWindowTextGUI(new SeparateTextGUIThread.Factory(), screen);
		guiThread = (SeparateTextGUIThread)multiWindowTextGUI.getGUIThread();
		
		this.gameModel = gameModel;
		this.controller = controller;
		this.settings = settings;
		
		SimpleTheme globalTheme = makeGlobalTheme();
		multiWindowTextGUI.setTheme(globalTheme);
		
		mainViewPanel = createMainViewPanel();
		prepareView();
		
		gameModel.registerObserver(this);
	}
	
	private SimpleTheme makeGlobalTheme() {
		SimpleTheme globalTheme = SimpleTheme.makeTheme(true, 
				new RGB(0, 0, 0),		// base foreground
				new RGB(255, 240, 140), // base background
				new RGB(0, 0, 0), 		// editable fore
				new RGB(255, 250, 180), // editable back
				new RGB(0, 0, 0), 		// selected fore
				new RGB(255, 250, 180), // selected back
				new RGB(255, 140, 80));	// gui
		return globalTheme;
	}
	
	private ViewPanel createMainViewPanel() {
		ViewPanel panel = new MainViewPanel(new GridLayout(2), gameModel, controller, settings);
		panel.createContent();
        panel.addContent();
        panel.initializeContent();
        panel.updateContent();
        panel.addInputHandling();
		return panel;
	}
	
	private void prepareView() {
		Window mainWindow = new BasicWindow("SWEET TOOTH");
		mainWindow.setFixedSize(new TerminalSize(120, 55));
        mainWindow.setComponent(mainViewPanel);
		
        multiWindowTextGUI.addWindow(mainWindow);
	}
	
	@Override
	public void startGuiThread() throws IOException, InterruptedException {
        guiThread.start(); // ... this thread will continue while the GUI runs on a separate thread ...
    }

	@Override
	public void update() {
		if(gameModel.isGameOver())
    		mainViewPanel.gameOverConfig();
		
		mainViewPanel.updateContent();
	}
	
	public void stopView() throws IOException {
		screen.stopScreen();
		
		if (guiThread != null)
	        guiThread.stop();
	}
}