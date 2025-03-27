package com.github.SweetTooth.gui;

import java.io.IOException;

import com.github.SweetTooth.characters.IPlayer;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor.RGB;
import com.googlecode.lanterna.graphics.SimpleTheme;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
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
	private WindowManager windowManager;
	private WindowPostRenderer postRenderer;
	private Component background;
	private MultiWindowTextGUI multiWindowTextGUI;
	IPlayer player;
	private SeparateTextGUIThread guiThread;
	
	SimpleTheme globalTheme = SimpleTheme.makeTheme(true, 
			new RGB(0, 0, 0),		// base foreground
			new RGB(255, 240, 140), // base background
			new RGB(0, 0, 0), 		// editable fore
			new RGB(255, 250, 180), // editable back
			new RGB(0, 0, 0), 		// selected fore
			new RGB(255, 250, 180), // selected back
			new RGB(255, 140, 80));	// gui
	
	public GUIManager(IPlayer player) throws IOException {
		DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
		terminalFactory.setInitialTerminalSize(new TerminalSize(127, 61));
		screen = terminalFactory.createScreen();
		windowManager = new DefaultWindowManager();
		postRenderer = null;
		background = new GUIBackdrop();
		multiWindowTextGUI = new MultiWindowTextGUI(new SeparateTextGUIThread.Factory(), screen, windowManager, postRenderer, background);
		this.player = player;
	}
	
    public void start() throws IOException, InterruptedException {
        Panel contentPanel = new Panel(new GridLayout(2));
        GUI gui = new GUI(this, contentPanel);
        gui.addComponents();
    	gui.initializeComponents();
    	gui.updateComponents();
        gui.addInputHandling();
    	
        Window window = new BasicWindow("Jaw Breaker");
    	window.setFixedSize(new TerminalSize(120, 56));
        window.setComponent(contentPanel);
        multiWindowTextGUI.addWindow(window);
        multiWindowTextGUI.setTheme(globalTheme);
        
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
