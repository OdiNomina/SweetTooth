package com.github.sweettooth.viewSwing.views;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Logger;

import javax.swing.JFrame;

import com.github.sweettooth.controllerSwing.api.ISwingController;
import com.github.sweettooth.model.api.GameSettings;
import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.ILocation;
import com.github.sweettooth.model.api.viewAPI.IMoneyDealer;
import com.github.sweettooth.model.api.viewAPI.IPlayer;
import com.github.sweettooth.model.api.viewAPI.Observer;
import com.github.sweettooth.model.api.viewAPI.Snackable;
import com.github.sweettooth.shared.api.Loggable;
import com.github.sweettooth.shared.api.UpdateGuard;
import com.github.sweettooth.viewSwing.commons.Tools;

public class TabbedPaneManager implements Observer, UpdateGuard, Loggable {
	private final Logger logger;
	private IGameData gameData;
	private GameSettings settings;
	private TabbedPaneDesign design;
	private ISwingController controller;
	private boolean updating;
	
	private IPlayer player;
	private IMoneyDealer loanShark;
	private IMoneyDealer bank;
	
	TabbedPaneManager(IGameData gameData, GameSettings settings) {
		logger = Logger.getLogger(TabbedPaneManager.class.getName());
		this.gameData = gameData;
		this.settings = settings;
		controller = ISwingController.getInstance();
		controller.initialize(gameData);
		design = new TabbedPaneDesign();
		
		player = gameData.player();
		loanShark = gameData.loanShark();
		bank = gameData.bank();
	}
	
	void addInputHandling() {
		try {
			design.buySelection.addActionListener(controller.createComboBoxListener(this, null, "Buy", design.buyQuantity, design.buySellInfo));
			design.sellSelection.addActionListener(controller.createComboBoxListener(this, null, "Sell", design.sellQuantity, design.buySellInfo));
			design.locationSelection.addActionListener(
					controller.createComboBoxListener(this, design.currentLocation, "Travel", design.locationSelection, design.travelInfo1, design.travelInfo2, design.travelInfo3, design.travelInterest));
	
			design.hideButton.addActionListener(controller.createButtonListener("Hide", null, null, design.hideButton, design.hideSeekInfo));
			design.seekButton.addActionListener(controller.createButtonListener("Seek", design.stash, design.seekQuantity, design.stash, design.hideSeekInfo));
			design.exitButton.addActionListener(controller.createButtonListener("Exit", null, null, design.exitButton, design.gameOverLabel));
			
			design.seekQuantity.addActionListener(controller.createTextFieldListener("Seek", design.stash, design.seekButton, design.hideSeekInfo));
			
			design.buyQuantity.addActionListener(controller.createTextFieldListener("Buy", design.buySelection, design.buySelection, design.buySellInfo));
			design.sellQuantity.addActionListener(controller.createTextFieldListener("Sell", design.sellSelection, design.sellSelection, design.buySellInfo));
			design.deposit.addActionListener(controller.createTextFieldListener("Deposit", null, design.deposit, design.bankInfo));
			design.withdraw.addActionListener(controller.createTextFieldListener("Withdraw", null, design.withdraw, design.bankInfo));
			design.lend.addActionListener(controller.createTextFieldListener("Lend", null, design.lend, design.loansharkInfo));
			design.giveBack.addActionListener(controller.createTextFieldListener("GiveMoneyBack", null, design.giveBack, design.loansharkInfo));
		}
		catch(RuntimeException ex)  {
			error("Error when adding input handling " + ex.getClass().getName(), ex);
		}
	}
	
	JFrame createDesign() {
		return design.createGroupLayoutDesign();
	}

	private void gameOverConfig() {
		try {
			design.gameOverLabel.setVisible(true);
			
			design.buySelection.setEnabled(false);
			design.sellSelection.setEnabled(false);
	
			design.seekQuantity.setEnabled(false);
			design.hideButton.setEnabled(false);
			design.seekButton.setEnabled(false);
	
			design.deposit.setEnabled(false);
			design.withdraw.setEnabled(false);
	
			design.lend.setEnabled(false);
			design.giveBack.setEnabled(false);
	
			design.locationSelection.setEnabled(false);
	
			design.balanceSheet.setEnabled(false);
		}
		catch(RuntimeException ex) {
			error("Error when setting 'game over configuration' " + ex.getClass().getName(), ex);
		}
	}

	@Override
	public Logger getLogger() {
		return logger;
	}

	void initializeContent() {
    	try {
    		// Title Panel
    		design.title1Label.setText("Du dealst mit Süßis?");
    		design.title2Label.setText("Mal sehen was du in einem Monat verdienst...");
    		design.gameOverLabel.setText(" Das wars... NICHTS GEHT MEHR ! ");
    		design.gameOverLabel.setVisible(false);
			// Current Panel
    		design.currentDayLabel.setText("Tag:");
    		design.currentLocationLabel.setText("Wo bin ich eigentlich...?");
    		design.cashLabel.setText("Cash dabei:");
    		design.pocketsLabel.setText("Was hab ich in den Taschen?"); 
			// Buy Sell Panel
    		design.buyTitle.setText("Hast du was für mich?");
    		design.sellTitel.setText("Hey! Willst du was Süßes?");
    		design.buySelectionLabel.setText("Ich mag...");
    		design.sellSelectionLabel.setText("Ich verkaufe dir...");
    		settings.getSnackFactory().defaultSnacks().stream()
				.sorted(Comparator.comparing(Snackable::name))
				.forEach(e -> { design.buySelection.addItem(e.name()); design.sellSelection.addItem(e.name()); });
			design.buySelection.setSelectedIndex(0);
			design.sellSelection.setSelectedIndex(0);
			design.buyPriceLabel.setText("Was kosten die?");
			design.sellPriceLabel.setText("für");
			design.buyQuantity.setText("");
			design.sellQuantity.setText("");
			// Hide Seek Panel
			design.hideSeekTitle.setText("Du hast ein echt gutes Versteck für deine Süßis, da sind sie sicher!");
			design.stashLabel.setText("Was liegt schon im Versteck?");
			design.seekQuantityLabel.setText("hmm... wie viel");
			design.hideButton.setText("Alles Verstecken");
			design.seekButton.setText("Aus dem Versteck holen");
			// Bank Panel
			design.bankTitle.setText("BANK:");
			design.bankBalanceLabel.setText("Kontostand:");
			design.depositLabel.setText("Ich möchte Geld einzahlen.");
			design.depositAnswer.setText("Natürlich, welchen Betrag?");
			design.withdrawLabel.setText("Ich würde gerne Geld abheben.");
			design.withdrawAnswer.setText("Gerne, wie viel?");
			design.bankDispoHint.setText(bank.getDispoHint());
			design.bankInterestHint1.setText(bank.getCreditInterestHint());
			design.bankInterestHint2.setText(bank.getDebitInterestHint());
		    // Loanshark Panel
			design.loansharkTitle.setText("KREDITHAI:");
			design.loansharkBalanceLabel.setText("Schulden:");
			design.lendLabel.setText("Ich brauch Geld.");
			design.lendAnswer.setText("Wie viel willst du?!");
			design.giveBackLabel.setText("Hier, ich hab dein Geld dabei.");
			design.giveBackAnswer.setText("Lass sehn...");
			design.loansharkInterestHint.setText(loanShark.getDebitInterestHint());
		    // Travel Panel
			design.travelTitle1.setText("Du willst dich mal umschauen?");
			design.travelTitle2.setText("Klar, aber du wirst den ganzen Tag unterwegs sein.");
			design.ticketLabel.setText("Eine Fahrt mit deinem EasyTicket kostet pauschal:");
			design.locationSelectionLabel.setText("Wohin gehts?");
			design.locationSelection.removeAllItems();
		    ArrayList<String> locations = new ArrayList<>();
		    for(ILocation l : ILocation.values())
		    	locations.add(l.getOfficialName());
		    for(String s : locations)
		    	design.locationSelection.addItem(s);
		    // Balance Panel
		    design.exitButton.setText("Ich hau ab, kein Bock mehr...");
		    
		    updateContent();
    	}
    	catch(RuntimeException ex) { 
    		error("Error when initializing content " + ex.getClass().getName(), ex);
    	}
	}
	
	@Override
    public boolean isUpdating() {
        return updating;
    }
	
	void setGameSettings(GameSettings settings) {
		this.settings = settings;
	}
	
	@Override
	public void update() {
		if(gameData.isGameOver() || gameData.isExitButtonClicked()) {
			updateContent();
			gameOverConfig();
		}
		else
			updateContent();
	}

	private void updateContent() {
		try {
			updating = true;
			// Current Panel
			design.currentDay.setText(Integer.toString(gameData.getDayOfGame()));
			design.currentLocation.setText(player.location().getOfficialName());
			design.cash.setText(Tools.formatMoney(settings, player.cash()));
			design.pockets.removeAllItems();
		    ArrayList<String> pocketItems = Tools.formatSnacks(settings, player.snacks());
		    for(String pi : pocketItems)
		    	design.pockets.addItem(pi);
		    // Buy Sell Panel
		    String buySelectedItem = design.buySelection.getSelectedItem().toString().strip();
		    Double buyPriceValue = settings.getSnackFactory().defaultSnacks().stream()
		    	.filter(e -> e.name().equalsIgnoreCase(buySelectedItem))
		    	.findFirst()
		    	.orElseThrow(() -> new IllegalArgumentException("Snack nicht gefunden: " + buySelectedItem))
		    	.staticPrice();
		    design.buyPrice.setText(Tools.formatMoney(settings, buyPriceValue));
		    String sellSelectedItem = design.sellSelection.getSelectedItem().toString().strip();
		    Double sellPriceValue = settings.getSnackFactory().defaultSnacks().stream()
		    		.filter(e -> e.name().equalsIgnoreCase(sellSelectedItem))
		    		.findFirst()
		    		.orElseThrow(() -> new IllegalArgumentException("Snack nicht gefunden: " + sellSelectedItem))
		    		.staticPrice();
		    design.sellPrice.setText(Tools.formatMoney(settings, sellPriceValue));
		    design.buySellInfo.setText("");
		    // Hide Seek Panel
		    design.stash.removeAllItems();
		    ArrayList<String> stashedItems = Tools.formatSnacks(settings, player.stash());
		    for(String si : stashedItems)
		    	design.stash.addItem(si);
		    design.seekQuantity.setText("");
		    design.hideSeekInfo.setText("");
		    // Bank Panel
		    design.deposit.setText("");
		    design.withdraw.setText("");
		    design.bankBalance.setText(Tools.formatMoney(settings, bank.clientsBalance(player)));
		    design.bankInfo.setText("");
		    // Loanshark Panel
		    design.lend.setText("");
		    design.giveBack.setText("");
		    design.loansharkBalance.setText(Tools.formatMoney(settings, loanShark.clientsBalance(player)));
		    design.loansharkInfo.setText("");
		    // Travel Panel
		    design.ticketPrice.setText(Tools.formatMoney(settings, settings.getTravelCosts()));
		    design.travelInfo1.setText("");
		    design.travelInfo2.setText("");
		    design.travelInfo3.setText("");
		    design.travelInterest.setText("");
		    // Info Panel
		    design.balanceSheet.setText(Tools.formatBalanceSheet(settings, gameData));
		}
		catch(RuntimeException ex) {
			error("Error when updating content " + ex.getClass().getName(), ex);
		}
		finally {
            updating = false;
        }
	}
}
