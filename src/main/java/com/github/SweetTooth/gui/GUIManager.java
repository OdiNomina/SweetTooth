package com.github.SweetTooth.gui;

import java.io.IOException;

import com.github.SweetTooth.game.Game;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor.RGB;
import com.googlecode.lanterna.graphics.SimpleTheme;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.SeparateTextGUIThread;
import com.googlecode.lanterna.gui2.WindowPostRenderer;
import com.googlecode.lanterna.gui2.WindowManager;
import com.googlecode.lanterna.gui2.DefaultWindowManager;
import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.GUIBackdrop;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

public class GUIManager {
	private Screen screen;
	private MultiWindowTextGUI multiWindowTextGUI;
	private SeparateTextGUIThread guiThread;
	
	SimpleTheme globalTheme = SimpleTheme.makeTheme(true, 
			new RGB(0, 0, 0),		// base foreground
			new RGB(255, 240, 140), // base background
			new RGB(0, 0, 0), 		// editable fore
			new RGB(255, 250, 180), // editable back
			new RGB(0, 0, 0), 		// selected fore
			new RGB(255, 250, 180), // selected back
			new RGB(255, 140, 80));	// gui
	
	public GUIManager() throws IOException {
		DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(127, 60));
		screen = terminalFactory.createScreen();
		WindowManager windowManager = new DefaultWindowManager();
		WindowPostRenderer postRenderer = null;
		Component background = new GUIBackdrop();
		multiWindowTextGUI = new MultiWindowTextGUI(new SeparateTextGUIThread.Factory(), screen, windowManager, postRenderer, background);
	}
	
    public void start(Game game) throws IOException, InterruptedException {
        PanelContentMain panelContentMain = new PanelContentMain(this, new GridLayout(2), game);
        panelContentMain.createContent();
        panelContentMain.addContent();
        panelContentMain.initializeContent();
        panelContentMain.updateContent();
        panelContentMain.addInputHandling();
    	
        Window mainWindow = new BasicWindow("SWEET TOOTH");
    	mainWindow.setFixedSize(new TerminalSize(120, 55));
        mainWindow.setComponent(panelContentMain);
        multiWindowTextGUI.setTheme(globalTheme);
        multiWindowTextGUI.addWindow(mainWindow);
        
        screen.startScreen();
    	guiThread = (SeparateTextGUIThread)multiWindowTextGUI.getGUIThread();
    	guiThread.start(); // ... this thread will continue while the GUI runs on a separate thread ...
    }

    public void stop() throws IOException {
    	if (guiThread != null)
            guiThread.stop();
    	
    	screen.stopScreen();
    }
}
