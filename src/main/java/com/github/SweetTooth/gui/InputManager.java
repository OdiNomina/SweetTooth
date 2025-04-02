package com.github.SweetTooth.gui;

import java.io.IOException;
import com.github.SweetTooth.events.Event;
import com.github.SweetTooth.game.Game;
import com.github.SweetTooth.locations.Location;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.InputFilter;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

class InputManager {
	GUIManager guiManager;
	GUI gui;
	
	InputManager(GUIManager guiManager, GUI gui) {
		this.guiManager = guiManager;
		this.gui = gui;
	}
	
	void addListenerDeal(ComboBox<String> selection, Event event, TextBox quantity) {
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
					guiManager.stop();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
        });
    }
    
    void addListenerHide(Button button, Event event, Game game) {
    	button.addListener(new Button.Listener() {
			@Override
			public void onTriggered(Button button) {
				String hideAnswer = event.handle();
				gui.updateComponents(game);
				gui.hideSeekInfo.setText(hideAnswer);
			}
        });
    }
	
    void addListenerSeek(Button button, Event event, ComboBox<String> selection, TextBox inputContainer, Game game) {
    	button.addListener(new Button.Listener() {
			@Override
			public void onTriggered(Button button) {
				try {
					int input = validateQuantity(inputContainer);
					event.setIntegerInput(input);
					event.setStringInput(selection.getSelectedItem());
					String seekAnswer = event.handle();
					gui.updateComponents(game);
					gui.hideSeekInfo.setText(seekAnswer);	
				}
				catch(NumberFormatException e) {
					inputContainer.removeLine(0);
					inputContainer.takeFocus();
				}
			}
        });
    }
    
    void addListenerTravel(Event travelEvent, Event applyInterestEvent, Game game) {
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
		            	Event.Answer travelAnswer = travelEvent.handleMultipleAnswers();
		            	String applyInterestAnswer = applyInterestEvent.handle();
	        			gui.updateComponents(game);
		            	gui.travelEventInfo1.setText(travelAnswer.answer()[0]);
		            	gui.travelEventInfo2.setText(travelAnswer.answer()[1]);
		            	gui.travelEventInfo3.setText(travelAnswer.answer()[2]);
		        	    gui.travelInterestInfo.setText(applyInterestAnswer);
		        	    game.increaseDayOfGame(1, gui);
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
    
	void setInputFilterDeal(TextBox inputContainer, Event event, Label answerContainer, Interactable nextInFocus, Game game) {
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
							if(input > 100)
								throw new NumberFormatException();
							event.setIntegerInput(input);
						}
						catch(NumberFormatException e) {
							answerContainer.setText("Du musst eine Zahl eingeben! (<= 100)");
							inputContainer.removeLine(0);
		        			return false;
						}
						String eventAnswer = event.handle();
						gui.updateComponents(game);
						answerContainer.setText(eventAnswer);
						inputContainer.setEnabled(false);
						inputContainer.removeLine(0);
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
    
    void setInputFilterFinances(TextBox inputContainer, Event event, Label answerContainer, Interactable nextInFocus, Game game) {
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
							if(input > 100_000)
								throw new NumberFormatException();
							event.setDoubleInput(input);
						}
						catch(NumberFormatException e) {
							answerContainer.setText("Du musst eine Zahl eingeben! (<= 100.000)");
							inputContainer.removeLine(0);
		        			return false;
						}
	        			String eventAnswer = event.handle();
						gui.updateComponents(game);
						answerContainer.setText(eventAnswer);
						inputContainer.removeLine(0);
						nextInFocus.takeFocus();
						return false;
        			}
				}
				return true;
			}
		});
    }
    
    void setInputFilterSeek(TextBox inputContainer, Event event, Label answerContainer, Interactable nextInFocus) {
    	inputContainer.setInputFilter(new InputFilter() {
        	@Override
			public boolean onInput(Interactable interactable, KeyStroke keyStroke) {
        		if(keyStroke.getKeyType() == KeyType.Enter) {
        			if(inputContainer.getText().isBlank()) {
        				inputContainer.removeLine(0);
        				answerContainer.setText("");
            			return false;
        			}
        			else
        				try {
        					validateQuantity(inputContainer);
        					inputContainer.removeLine(0);
        					nextInFocus.takeFocus();
        					answerContainer.setText("");
        					return false;
        				}
        				catch(NumberFormatException e) {
        					answerContainer.setText("Du musst eine Zahl eingeben! (<= 100)");
        					inputContainer.removeLine(0);
        					return false;
        				}
        		}
        		return true;
			}
		});
    }
    
    private int validateQuantity(TextBox inputContainer) {
    	int input = Integer.parseInt(inputContainer.getText().strip());
		if(input > 100)
			throw new NumberFormatException();
		return input;
    }
}
