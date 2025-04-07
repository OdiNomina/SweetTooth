package com.github.SweetTooth.gui;

import java.util.concurrent.ConcurrentHashMap;

import com.github.SweetTooth.game.Game;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LayoutManager;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;


public abstract class PanelContent<T> extends Panel {
	Game game;
	ConcurrentHashMap<String, Label> labels = new ConcurrentHashMap<>();
	ConcurrentHashMap<String, TextBox> textBoxes = new ConcurrentHashMap<>();
	ConcurrentHashMap<String, TextBoxInitialText> textBoxesIT = new ConcurrentHashMap<>();
	ConcurrentHashMap<String, ComboBox<T>> comboBoxes = new ConcurrentHashMap<String, ComboBox<T>>();
	ConcurrentHashMap<String, Button> buttons = new ConcurrentHashMap<>();

	PanelContent(LayoutManager layoutManager, Game game) {
        super(layoutManager);
        this.game = game;
    }
	
	public abstract void addContent();
	public abstract void addInputHandling();
	public abstract void createContent();
	public abstract void disableComponents();
	public abstract void initializeContent();
	public abstract void updateContent();
}