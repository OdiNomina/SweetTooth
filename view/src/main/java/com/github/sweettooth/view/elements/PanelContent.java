package com.github.sweettooth.view.elements;

import java.util.concurrent.ConcurrentHashMap;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LayoutManager;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;

public abstract class PanelContent extends Panel {
	final ConcurrentHashMap<String, Label> labels = new ConcurrentHashMap<>();
	final ConcurrentHashMap<String, TextBox> textBoxes = new ConcurrentHashMap<>();
	final ConcurrentHashMap<String, TextBoxInitialText> textBoxesIT = new ConcurrentHashMap<>();
	final ConcurrentHashMap<String, ComboBox<String>> comboBoxes = new ConcurrentHashMap<>();
	final ConcurrentHashMap<String, Button> buttons = new ConcurrentHashMap<>();

	PanelContent(LayoutManager layoutManager) {
        super(layoutManager);
    }

	public abstract void addContent();
	public abstract void addInputHandling();
	public abstract void createContent();
	public abstract void gameOverConfig();
	public abstract void initializeContent();
	public abstract void updateContent();
}