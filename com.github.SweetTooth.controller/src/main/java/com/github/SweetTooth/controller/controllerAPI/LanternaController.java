package com.github.SweetTooth.controller.controllerAPI;

import com.github.SweetTooth.controller.ButtonListeners.ExitListener;
import com.github.SweetTooth.controller.ButtonListeners.HideListener;
import com.github.SweetTooth.controller.ButtonListeners.SeekListener;
import com.github.SweetTooth.controller.TextBoxInputFilters.DealInputFilter;
import com.github.SweetTooth.controller.TextBoxInputFilters.FinancesInputFilter;
import com.github.SweetTooth.controller.TextBoxInputFilters.SeekQuantityInputFilter;
import com.github.SweetTooth.controller.comboBoxListeners.BuySelectionListener;
import com.github.SweetTooth.controller.comboBoxListeners.LocationSelectionListener;
import com.github.SweetTooth.controller.comboBoxListeners.SellSelectionListener;
import com.github.SweetTooth.model.events.EventFactory;
import com.github.SweetTooth.model.game.Game;
import com.github.SweetTooth.view.lanternaGUI.PanelContent;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.input.KeyStroke;

public class LanternaController {
	private Game game;
	private PanelContent panelContent;
	private EventFactory eventFactory = EventFactory.getDefaultFactory();
	
	public LanternaController(Game game, PanelContent panelContent) {
		this.game = game;
		this.panelContent = panelContent;
	}
	
	public Game getGame() {
		return game;
	}
	
	public PanelContent getPanelContent() {
		return panelContent;
	}
	
	public EventFactory getEventFactory() {
		return eventFactory;
	}
	
	public void addInputHandling() {
		panelContent.getComboBoxes().get("buySelection").addListener(new BuySelectionListener());
		panelContent.getComboBoxes().get("sellSelection").addListener(new SellSelectionListener());
		panelContent.getComboBoxes().get("locationSelection").addListener(new LocationSelectionListener());
		
		panelContent.getButtons().get("hide").addListener(new HideListener());
		panelContent.getButtons().get("seek").addListener(new SeekListener());
		panelContent.getButtons().get("exit").addListener(new ExitListener());
    
		panelContent.getTextBoxes().get("seekQuantity").setInputFilter(new SeekQuantityInputFilter("seekQuantity"));		
		panelContent.getTextBoxesIT().get("buyQuantity").setInputFilter(new DealInputFilter("buyQuantity"));
		panelContent.getTextBoxesIT().get("sellQuantity").setInputFilter(new DealInputFilter("sellQuantity"));
		panelContent.getTextBoxesIT().get("deposit").setInputFilter(new FinancesInputFilter("deposit"));
		panelContent.getTextBoxesIT().get("withdraw").setInputFilter(new FinancesInputFilter("withdraw"));
		panelContent.getTextBoxesIT().get("lend").setInputFilter(new FinancesInputFilter("lend"));
		panelContent.getTextBoxesIT().get("giveBack").setInputFilter(new FinancesInputFilter("giveBack"));
    }
	
//	public abstract void onTriggered(Button button);
//	public abstract void onSelectionChanged(int selectedIndex, int previousSelection, boolean changedByUserInteraction);
//	public abstract boolean onInput(Interactable interactable, KeyStroke keyStroke);
}
