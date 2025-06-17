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
import com.googlecode.lanterna.gui2.WindowManager;
import com.googlecode.lanterna.gui2.DefaultWindowManager;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

public class LanternaGUI implements Observer, DisplayElement {
	private DefaultTerminalFactory terminalFactory;
	private Screen screen;
	private WindowManager windowManager;
	private MultiWindowTextGUI multiWindowTextGUI;
	
	private GameModelInterface gameModel;
	private ControllerInterface controller;
	private Settings settings;
	
	private SeparateTextGUIThread guiThread;
	private SimpleTheme globalTheme;
	private ViewPanel mainViewPanel;
	private Window mainWindow;
	
	public LanternaGUI(GameModelInterface gameModel, ControllerInterface controller, Settings settings) throws IOException {
		terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(127, 60));
		screen = terminalFactory.createScreen();
		windowManager = new DefaultWindowManager();
		multiWindowTextGUI = new MultiWindowTextGUI(new SeparateTextGUIThread.Factory(), screen, windowManager);
		
		this.gameModel = gameModel;
		this.controller = controller;
		this.settings = settings;
		
		globalTheme = makeGlobalTheme();
		multiWindowTextGUI.setTheme(globalTheme);
		
		gameModel.registerObserver(this);
		createMainView();
	}
	
//	private void initialize() {	}
	
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
	
	void createMainView() {
		mainViewPanel = new MainViewPanel(new GridLayout(2), gameModel, controller, settings);
		mainViewPanel.createContent();
        mainViewPanel.addContent();
        mainViewPanel.initializeContent();
        mainViewPanel.updateContent();
        mainViewPanel.addInputHandling();
		
		mainWindow = new BasicWindow("SWEET TOOTH");
		mainWindow.setFixedSize(new TerminalSize(120, 55));
        mainWindow.setComponent(mainViewPanel);
		
        multiWindowTextGUI.addWindow(mainWindow);
	}
	
	@Override
	public void display() throws IOException, InterruptedException {
        screen.startScreen();
        guiThread = (SeparateTextGUIThread)multiWindowTextGUI.getGUIThread();
        guiThread.start(); // ... this thread will continue while the GUI runs on a separate thread ...
    }

	@Override
	public void updateMainView() {
		if(gameModel.isGameOver())
    		mainViewPanel.gameOverConfig();
		
		mainViewPanel.updateContent();
	}
	
	public void stopView() throws IOException {
		if (guiThread != null)
	        guiThread.stop();
		
		screen.stopScreen();
	}
}