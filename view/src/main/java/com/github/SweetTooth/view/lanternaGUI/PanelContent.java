package com.github.SweetTooth.view.lanternaGUI;

import java.util.concurrent.ConcurrentHashMap;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LayoutManager;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;


public abstract class PanelContent<T> extends Panel {
	private ConcurrentHashMap<String, Label> labels = new ConcurrentHashMap<>();
	private ConcurrentHashMap<String, TextBox> textBoxes = new ConcurrentHashMap<>();
	private ConcurrentHashMap<String, TextBoxInitialText> textBoxesIT = new ConcurrentHashMap<>();
	private ConcurrentHashMap<String, ComboBox<T>> comboBoxes = new ConcurrentHashMap<String, ComboBox<T>>();
	private ConcurrentHashMap<String, Button> buttons = new ConcurrentHashMap<>();

	PanelContent(LayoutManager layoutManager) {
        super(layoutManager);
    }
	
	public ConcurrentHashMap<String, Label> getLabels(){
		return labels;
	}
	
	public ConcurrentHashMap<String, TextBox> getTextBoxes(){
		return textBoxes;
	}
	
	public ConcurrentHashMap<String, TextBoxInitialText> getTextBoxesIT(){
		return textBoxesIT;
	}
	
	public ConcurrentHashMap<String, ComboBox<T>> getComboBoxes(){
		return comboBoxes;
	}
	
	public ConcurrentHashMap<String, Button> getButtons(){
		return buttons;
	}
	
	public abstract void addContent();
	public abstract void createContent();
	public abstract void disableComponents();
	public abstract void initializeContent();
	public abstract void updateContent();
}