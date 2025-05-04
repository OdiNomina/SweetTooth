package com.github.SweetTooth.view.lanternaGUI;

import java.util.concurrent.ConcurrentHashMap;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LayoutManager;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;


abstract class PanelContent<T> extends Panel {
	private ConcurrentHashMap<String, Label> labels = new ConcurrentHashMap<>();
	private ConcurrentHashMap<String, TextBox> textBoxes = new ConcurrentHashMap<>();
	private ConcurrentHashMap<String, TextBoxInitialText> textBoxesIT = new ConcurrentHashMap<>();
	private ConcurrentHashMap<String, ComboBox<T>> comboBoxes = new ConcurrentHashMap<String, ComboBox<T>>();
	private ConcurrentHashMap<String, Button> buttons = new ConcurrentHashMap<>();

	PanelContent(LayoutManager layoutManager) {
        super(layoutManager);
    }
	
	ConcurrentHashMap<String, Button> getButtons(){
		return buttons;
	}

	ConcurrentHashMap<String, ComboBox<T>> getComboBoxes(){
		return comboBoxes;
	}

	ConcurrentHashMap<String, Label> getLabels(){
		return labels;
	}
	
	ConcurrentHashMap<String, TextBox> getTextBoxes(){
		return textBoxes;
	}
	
	ConcurrentHashMap<String, TextBoxInitialText> getTextBoxesIT(){
		return textBoxesIT;
	}
	
	abstract void addContent();
	abstract void createContent();
	abstract void initializeContent();
	abstract void updateContent();
}