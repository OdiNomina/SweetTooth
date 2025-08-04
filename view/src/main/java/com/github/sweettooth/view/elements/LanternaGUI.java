package com.github.sweettooth.view.elements;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.logging.Logger;

import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.controllerLanterna.api.IController;
import com.github.sweettooth.model.api.GameSettings;
import com.github.sweettooth.model.api.viewAPI.Observer;
import com.github.sweettooth.shared.api.Loggable;
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

public class LanternaGUI implements Observer, DisplayElement, Loggable {
	private Screen screen;
	private MultiWindowTextGUI multiWindowTextGUI;
	private SeparateTextGUIThread guiThread;
	
	private final Logger logger;
	private IController controller;
	
	private IGameData gameModel;
	private GameSettings gameSettings;
	private ViewPanel mainViewPanel;
	
	public LanternaGUI(IGameData gameModel) throws NullPointerException {
		logger = Logger.getLogger(LanternaGUI.class.getName());
		this.gameModel = Objects.requireNonNull(gameModel);
		
		controller = IController.getInstance();
		controller.initialize(gameModel);
	}
	
	@Override
	public Logger getLogger() {	
		return logger;
	}

	@Override
	public DisplayElement initialize(GameSettings gameSettings) throws NullPointerException {
		this.gameSettings = Objects.requireNonNull(gameSettings);
		
		gameModel.registerObserver(this);
		return this;
	}

	public void interruptGuiThread() {
		try { screen.stopScreen(); }
		catch (IOException e) { error(e.getClass().getName() + " when attemting to stop the screen. ",e); }
		
		if (guiThread != null)
	        guiThread.stop(); // Ändert nur den Thread Status! Es handelt sich nicht um die deprecated Thread stop() Methode.
	}

	@Override
	public void run() {
		try {
			Instant start = Instant.now();
			DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(127, 60));
			try {
				screen = terminalFactory.createScreen();
				screen.startScreen();
			}
			catch (IOException e) { error(e.getClass().getName() + " when attemting to create or start the screen.", e); }
			
			multiWindowTextGUI = new MultiWindowTextGUI(new SeparateTextGUIThread.Factory(), screen);
			guiThread = (SeparateTextGUIThread)multiWindowTextGUI.getGUIThread();
			
			SimpleTheme globalTheme = makeGlobalTheme();
			multiWindowTextGUI.setTheme(globalTheme);
			
			mainViewPanel = createMainViewPanel();
			prepareView();
			
			guiThread.start();
			info(String.format(Thread.currentThread().getName() + " thread stopped: Runtime %s ms", start.until(Instant.now(), ChronoUnit.MILLIS)));
		}
		catch(RuntimeException e) { error(e.getClass().getName() + " when creating lanterna GUI.", e); }
	}
	
	private ViewPanel createMainViewPanel() {
		try {
			ViewPanel panel = new MainViewPanel(new GridLayout(2), gameModel, controller, gameSettings);
			panel.createContent();
		    panel.addContent();
		    panel.initializeContent();
		    panel.updateContent();
		    panel.addInputHandling();
			return panel;
		}
		catch(RuntimeException e) {
			error(e.getClass().getName() + " when creating 'main view panel'.", e);
			return null;
		}
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
	
	private void prepareView() {
		try {
			Window mainWindow = new BasicWindow("SWEET TOOTH");
			mainWindow.setFixedSize(new TerminalSize(120, 55));
	        mainWindow.setComponent(Objects.requireNonNull(mainViewPanel));
	        
	        multiWindowTextGUI.addWindow(mainWindow);
		}
		catch(NullPointerException e) { error(e.getClass().getName() + " when preparing view.", e); }
	}

	@Override
	public void update() {
		if(gameModel.isGameOver())
    		mainViewPanel.gameOverConfig();
    	if(gameModel.isExitButtonClicked())
    		interruptGuiThread();
    	else
    		mainViewPanel.updateContent();
	}
}