package com.github.SweetTooth.gui;

import java.io.IOException;

import com.github.SweetTooth.events.IEvent;
import com.github.SweetTooth.locations.Location;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.InputFilter;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

class Listener {
	GUI gui;
	
	Listener(GUI gui) {
		this.gui = gui;
	}
	
	void addListenerDeal(ComboBox<String> selection, IEvent event, TextBox quantity) {
    	selection.addListener(new ComboBox.Listener() {
    		@Override
    		public void onSelectionChanged(int selectedIndex, int previousSelection, boolean changedByUserInteraction) {
    			if(changedByUserInteraction) {
    				event.setStringInput(selection.getItem(selectedIndex));
    				selection.setEnabled(false);
    				quantity.setEnabled(true).takeFocus();
    			}
    		}
    	});
	}
	
	void addListenerExit() {
    	gui.exit.addListener(new Button.Listener() {
			@Override
			public void onTriggered(Button button){
				try {
					gui.guiManager.stop();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
        });
    }
    
    void addListenerHideSeek(Button button, IEvent event) {
    	button.addListener(new Button.Listener() {
			@Override
			public void onTriggered(Button button) {
				String hideSeekAnswer = event.handleEvent();
				gui.updateComponents();
				gui.hideSeekInfo.setText(hideSeekAnswer);
			}
        });
    }
	
    void addListenerTravel(IEvent travelEvent, IEvent applyInterestEvent) {
        gui.locationSelection.addListener(new ComboBox.Listener() {
        	@Override
            public void onSelectionChanged(int selectedIndex, int previousSelection, boolean changedByUserInteraction) {
        		if(changedByUserInteraction) {
	        		if(selectedIndex == previousSelection) {
	            		gui.travelEventInfo1.setText("Du bist doch schon da!");
	            		return;
	            	}
	        		try {
	        			Location location = Location.valueOfficialName(gui.locationSelection.getItem(selectedIndex));
	        			String input = location.toString();
	        			travelEvent.setStringInput(input);
		            	IEvent.Answer travelAnswer = travelEvent.handleEventMultipleAnswers();
		            	String applyInterestAnswer = applyInterestEvent.handleEvent();
	        			gui.updateComponents();
		            	gui.travelEventInfo1.setText(travelAnswer.first());
		            	gui.travelEventInfo2.setText(travelAnswer.second());
		            	gui.travelEventInfo3.setText(travelAnswer.third());
		        	    gui.travelInterestInfo.setText(applyInterestAnswer);
		        	    IEvent.increaseDayOfGame(1, gui);
	        		}
	        		catch(IllegalArgumentException e) {
	        			e.printStackTrace();
	        		} catch (IOException e) {
						e.printStackTrace();
					}
	        	}
        	}
        });
    }
    
	void setInputFilterDeal(TextBox inputContainer, IEvent event, Label answerContainer, ComboBox<String> nextInFocus) {
    	inputContainer.setInputFilter(new InputFilter() {
        	@Override
			public boolean onInput(Interactable interactable, KeyStroke keyStroke) {
        		if(keyStroke.getKeyType() == KeyType.Enter) {
        			if(inputContainer.getText().isBlank()) {
        				inputContainer.removeLine(0);
        				answerContainer.setText("");
            			return false;
        			}
        			else {
        				try {
							int input = Integer.parseInt(inputContainer.getText().strip());
							event.setIntegerInput(input);
						}
						catch(NumberFormatException e) {
							answerContainer.setText("Du musst eine Zahl eingeben!");
							inputContainer.removeLine(0);
		        			return false;
						}
						String eventAnswer = event.handleEvent();
						gui.updateComponents();
						answerContainer.setText(eventAnswer);
						inputContainer.setEnabled(false);
						nextInFocus.setEnabled(true).takeFocus();
						return false;
					}
        		}
        		if(keyStroke.getKeyType() == KeyType.Character)
        			return true;
        		return false;
			}
		}); 
    }
    
    void setInputFilterFinances(TextBox inputContainer, IEvent event, Label answerContainer, ComboBox<String> nextInFocus) {
    	inputContainer.setInputFilter(new InputFilter() {
        	@Override
			public boolean onInput(Interactable interactable, KeyStroke keyStroke) {
        		if(keyStroke.getKeyType() == KeyType.Enter) {
        			if(inputContainer.getText().isBlank()) {
        				inputContainer.removeLine(0);
        				answerContainer.setText("");
            			return false;
        			}
        			else {
	        			try {
							double input = Double.parseDouble(inputContainer.getText().strip());
							event.setDoubleInput(input);
						}
						catch(NumberFormatException e) {
							answerContainer.setText("Du musst eine Zahl eingeben!");
							inputContainer.removeLine(0);
		        			return false;
						}
	        			String eventAnswer = event.handleEvent();
						gui.updateComponents();
						answerContainer.setText(eventAnswer);
						nextInFocus.takeFocus();
						return false;
        			}
				}
				return true;
			}
		});
    }
}
