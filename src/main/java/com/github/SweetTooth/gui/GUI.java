package com.github.SweetTooth.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

import com.github.SweetTooth.characters.MoneyDealer;
import com.github.SweetTooth.events.EventFactory;
import com.github.SweetTooth.events.IEvent;
import com.github.SweetTooth.game.Game;
import com.github.SweetTooth.locations.Location;
import com.github.SweetTooth.snacks.CandyFactory;
import com.github.SweetTooth.snacks.Snackable;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor.RGB;
import com.googlecode.lanterna.graphics.SimpleTheme;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Separator;
import com.googlecode.lanterna.gui2.TextBox;

public class GUI {
	Panel panel;
	
	// Titel
	Label titel1 = new Label("");
	Label titel2 = new Label("");
	Label titel3 = new Label("");
	// Current day
	// Current day
    Label currentDayLabel = new Label("");
    Label currentDay = new Label("");
    // Current location
    Label currentLocationLabel = new Label("");
    Label currentLocation = new Label("");
    // Cash
    Label cashLabel = new Label("");
    Label cash = new Label("");
    // Sweets in pockets
    Label pocketsLabel = new Label("");
    ComboBox<String> sweetsInPockets = new ComboBox<>("").setReadOnly(true);
    // Buy
    Label buyTitel = new Label("");;
    Label buySelectionLabel = new Label("");
    ComboBox<String> buySelection = new ComboBox<>("").setReadOnly(true);
    TextBox buyQuantity = new TextBox().setEnabled(false);
    Label buyInfo = new Label("");
    // Sell
    Label sellTitel = new Label("");
    Label sellSelectionLabel = new Label("");
    ComboBox<String> sellSelection = new ComboBox<>("").setReadOnly(true);
    TextBox sellQuantity = new TextBox().setEnabled(false);
    Label sellInfo = new Label("");
    // Hide and Seek
    Label hideSeekTitel = new Label("");
    Label stashLabel = new Label("");
    Button hide = new Button("");
    ComboBox<String> stash = new ComboBox<>("").setReadOnly(true);
    Label seekQuantityLabel = new Label("");
    TextBox seekQuantity = new TextBox();
    Button seek = new Button("");
    Label hideSeekInfo = new Label("");
    // Bank
    Label bankTitel = new Label("");
    Label bankBalanceLabel = new Label("");
    Label bankBalance = new Label("");
    Label depositLabel = new Label("");
    MyTextBox deposit = new MyTextBox();
    Label withdrawLabel = new Label("");
    MyTextBox withdraw = new MyTextBox();
    Label bankInfo = new Label("");
    Label bankDispoHint = new Label("");
    Label bankInterestHint = new Label("");
    // Loanshark
    Label loansharkTitel = new Label("");
    Label loansharkBalanceLabel = new Label("");
    Label loansharkBalance = new Label("");
    Label lendLabel = new Label("");
    MyTextBox lend = new MyTextBox();
    Label giveBackLabel = new Label("");
    MyTextBox giveBack = new MyTextBox();
    Label loansharkInfo = new Label("");
    Label loansharkInterestHint = new Label("");
    // Travel
    Label travelTitel1 = new Label("");
    Label travelTitel2 = new Label("");
    Label ticketLabel = new Label("");
    Label ticketPrice = new Label("");
    Label locationLabel = new Label("");
    ComboBox<String> locationSelection = new ComboBox<>("").setReadOnly(true);
    Label travelEventInfo1 = new Label("");
    Label travelEventInfo2 = new Label("");
    Label travelEventInfo3 = new Label("");
    Label travelInterestInfo = new Label("");
    // Balance sheet
    TextBox balanceSheet = new TextBox("", TextBox.Style.MULTI_LINE).setEnabled(false);
    // End
    Button exit = new Button("").setEnabled(true);
	
	GUI(Panel contentPanel) {
		this.panel = contentPanel;
	}
	
	void addComponents() {
		// Titel
		titel1.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(2)).addStyle(SGR.BOLD);
		titel2.setLayoutData(GridLayout.createHorizontallyFilledLayoutData()).addStyle(SGR.BOLD);
		titel3.setLayoutData(GridLayout.createHorizontallyEndAlignedLayoutData(1)).addStyle(SGR.BOLD).addStyle(SGR.REVERSE).setVisible(false);
		panel.addComponent(addEmptyRow());
		panel.addComponent(titel1);
		panel.addComponent(titel2);
		panel.addComponent(titel3);
    	// Current day
    	currentDayLabel.setLayoutData(GridLayout.createHorizontallyEndAlignedLayoutData(1)).addStyle(SGR.BOLD);
        panel.addComponent(currentDayLabel);
        panel.addComponent(currentDay);
        // Current location
        currentLocationLabel.setLayoutData(GridLayout.createHorizontallyEndAlignedLayoutData(1)).addStyle(SGR.BOLD);
        panel.addComponent(addEmptyRow());
        panel.addComponent(currentLocationLabel);
        panel.addComponent(currentLocation);
        // Cash
        cashLabel.setLayoutData(GridLayout.createHorizontallyEndAlignedLayoutData(1)).addStyle(SGR.BOLD);
        panel.addComponent(addEmptyRow());
        panel.addComponent(cashLabel);
        panel.addComponent(cash);
        // Sweets in pockets
        pocketsLabel.setLayoutData(GridLayout.createHorizontallyEndAlignedLayoutData(1)).addStyle(SGR.BOLD);
        panel.addComponent(addEmptyRow());
        panel.addComponent(pocketsLabel);
        panel.addComponent(sweetsInPockets.setLayoutData(GridLayout.createHorizontallyFilledLayoutData()));
        // Buy
        buyTitel.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(2)).addStyle(SGR.BOLD);
        panel.addComponent(addHorizontalLine());
        panel.addComponent(buyTitel);
        panel.addComponent(buySelectionLabel.setLayoutData(GridLayout.createHorizontallyEndAlignedLayoutData(1)));
        panel.addComponent(buySelection.setLayoutData(GridLayout.createHorizontallyFilledLayoutData()));
        panel.addComponent(new EmptySpace());
        panel.addComponent(buyQuantity.setLayoutData(GridLayout.createHorizontallyFilledLayoutData()));
        panel.addComponent(buyInfo.setLayoutData(GridLayout.createHorizontallyEndAlignedLayoutData(2)));
        // Sell
        sellTitel.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(2)).addStyle(SGR.BOLD);
        panel.addComponent(sellTitel);
        panel.addComponent(sellSelectionLabel.setLayoutData(GridLayout.createHorizontallyEndAlignedLayoutData(1)));
        panel.addComponent(sellSelection.setLayoutData(GridLayout.createHorizontallyFilledLayoutData()));
        panel.addComponent(new EmptySpace());
        panel.addComponent(sellQuantity.setLayoutData(GridLayout.createHorizontallyFilledLayoutData()));      
        panel.addComponent(sellInfo.setLayoutData(GridLayout.createHorizontallyEndAlignedLayoutData(2)));
        // Hide and Seek
        hideSeekTitel.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(2)).addStyle(SGR.BOLD);
        stashLabel.setLayoutData(GridLayout.createHorizontallyEndAlignedLayoutData(1)).addStyle(SGR.BOLD);
        panel.addComponent(addHorizontalLine());
        panel.addComponent(hideSeekTitel);
        panel.addComponent(new EmptySpace());
        panel.addComponent(hide);
        panel.addComponent(addEmptyRow());
        panel.addComponent(stashLabel);
        panel.addComponent(stash.setLayoutData(GridLayout.createHorizontallyFilledLayoutData()));
        panel.addComponent(seekQuantityLabel.setLayoutData(GridLayout.createHorizontallyEndAlignedLayoutData(1)));
        panel.addComponent(seekQuantity.setLayoutData(GridLayout.createHorizontallyFilledLayoutData()));
        panel.addComponent(new EmptySpace());
        panel.addComponent(seek);
        panel.addComponent(hideSeekInfo.setLayoutData(GridLayout.createHorizontallyEndAlignedLayoutData(2)));
        // Bank
        bankTitel.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(2)).addStyle(SGR.BOLD);
        bankBalanceLabel.setLayoutData(GridLayout.createHorizontallyEndAlignedLayoutData(1)).addStyle(SGR.BOLD);
        bankInterestHint.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(2)).addStyle(SGR.ITALIC);
        bankDispoHint.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(2)).addStyle(SGR.ITALIC);
        panel.addComponent(addHorizontalLine());
        panel.addComponent(bankTitel);
        panel.addComponent(bankBalanceLabel);
        panel.addComponent(bankBalance);
        panel.addComponent(depositLabel.setLayoutData(GridLayout.createHorizontallyEndAlignedLayoutData(1)));
        panel.addComponent(deposit.setLayoutData(GridLayout.createHorizontallyFilledLayoutData()));
        panel.addComponent(withdrawLabel.setLayoutData(GridLayout.createHorizontallyEndAlignedLayoutData(1)));
        panel.addComponent(withdraw.setLayoutData(GridLayout.createHorizontallyFilledLayoutData()));
        panel.addComponent(bankInfo.setLayoutData(GridLayout.createHorizontallyEndAlignedLayoutData(2)));
        panel.addComponent(bankDispoHint);
        panel.addComponent(bankInterestHint);
        // Loanshark
        loansharkTitel.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(2)).addStyle(SGR.BOLD);
        loansharkBalanceLabel.setLayoutData(GridLayout.createHorizontallyEndAlignedLayoutData(1)).addStyle(SGR.BOLD);
        loansharkInterestHint.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(2)).addStyle(SGR.ITALIC);
        panel.addComponent(addHorizontalLine());
        panel.addComponent(loansharkTitel);
        panel.addComponent(loansharkBalanceLabel);
        panel.addComponent(loansharkBalance);
        panel.addComponent(lendLabel.setLayoutData(GridLayout.createHorizontallyEndAlignedLayoutData(1)));
        panel.addComponent(lend.setLayoutData(GridLayout.createHorizontallyFilledLayoutData()));
        panel.addComponent(giveBackLabel.setLayoutData(GridLayout.createHorizontallyEndAlignedLayoutData(1)));
        panel.addComponent(giveBack.setLayoutData(GridLayout.createHorizontallyFilledLayoutData()));
        panel.addComponent(loansharkInfo.setLayoutData(GridLayout.createHorizontallyEndAlignedLayoutData(2)));
        panel.addComponent(loansharkInterestHint);
        // Travel
        travelTitel1.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(2)).addStyle(SGR.BOLD);
        travelTitel2.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(2)).addStyle(SGR.BOLD);
        travelEventInfo1.setLayoutData(GridLayout.createHorizontallyEndAlignedLayoutData(2)).addStyle(SGR.BOLD).addStyle(SGR.REVERSE);
        travelEventInfo3.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(2)).addStyle(SGR.ITALIC);
        travelInterestInfo.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(2)).addStyle(SGR.ITALIC);
        panel.addComponent(addHorizontalLine());
        panel.addComponent(travelTitel1);
        panel.addComponent(travelTitel2);
        panel.addComponent(locationLabel.setLayoutData(GridLayout.createHorizontallyEndAlignedLayoutData(1)));
        panel.addComponent(locationSelection.setLayoutData(GridLayout.createHorizontallyFilledLayoutData()));
        panel.addComponent(ticketLabel.setLayoutData(GridLayout.createHorizontallyEndAlignedLayoutData(1)));
        panel.addComponent(ticketPrice);
        panel.addComponent(travelEventInfo2.setLayoutData(GridLayout.createHorizontallyEndAlignedLayoutData(2)));
        panel.addComponent(travelEventInfo1);
        panel.addComponent(travelEventInfo3);
        panel.addComponent(travelInterestInfo);
        // Balance sheet
        balanceSheet.setLayoutData(GridLayout.createLayoutData(GridLayout.Alignment.FILL, GridLayout.Alignment.FILL, true, true, 2, 1))
        	.setTheme(new SimpleTheme(new RGB(50, 50, 0), new RGB(250, 220, 100), SGR.BOLD));
        panel.addComponent(addHorizontalLine());
        panel.addComponent(balanceSheet);
        // Exit
        panel.addComponent(exit.setLayoutData(GridLayout.createHorizontallyEndAlignedLayoutData(2)));
	}
	
	void initializeComponents(Game game) {
		// Titel
		titel1.setText("Du dealst mit Süßis?");
		titel2.setText("Mal sehen was du in einem Monat verdienst...");
		titel3.setText(" Das wars... NICHTS GEHT MEHR ! ");
		// Current day
	    currentDayLabel.setText("Tag:");
	    // Current location
	    currentLocationLabel.setText("Wo bin ich eigentlich...?");
	    // Cash
	    cashLabel.setText("Cash dabei:");
	    // Sweets in pockets
	    pocketsLabel.setText("Was hab ich in den Taschen?");   
	    // Buy
	    buyTitel.setText("Hast du was für mich?");
	    buySelectionLabel.setText("Ich mag..."); 
	    // Sell
	    sellTitel.setText("Hey! Willst du was Süßes?");
	    sellSelectionLabel.setText("Ich verkaufe dir...");
	    // Hide and Seek
	    hideSeekTitel.setText("Du hast ein echt gutes Versteck für deine Süßis, da sind sie sicher!");
	    stashLabel.setText("Was liegt schon im Versteck?");
	    seekQuantityLabel.setText("hmm... wie viel");
	    hide.setLabel("Alles Verstecken");
	    seek.setLabel("Aus dem Versteck holen");
	    // Bank
	    bankTitel.setText("BANK:");
	    bankBalanceLabel.setText("Kontostand:");
	    depositLabel.setText("Ich möchte Geld einzahlen.");
	    deposit.setText("Natürlich, welchen Betrag?");
	    deposit.setInitialText(deposit.getText());
	    withdrawLabel.setText("Ich würde gerne Geld abheben.");
	    withdraw.setText("Gerne, wie viel?");
	    withdraw.setInitialText(withdraw.getText());
	    bankDispoHint.setText(game.getBank().getDispoHint());
	    bankInterestHint.setText(game.getBank().getInterestHint());
	    // Loanshark
	    loansharkTitel.setText("KREDITHAI:");
	    loansharkBalanceLabel.setText("Schulden:");
	    lendLabel.setText("Ich brauch Geld.");
	    lend.setText("Wie viel willst du?!");
	    lend.setInitialText(lend.getText());
	    giveBackLabel.setText("Hier, ich hab dein Geld dabei.");
	    giveBack.setText("Lass sehn...");
	    giveBack.setInitialText(giveBack.getText());
	    loansharkInterestHint.setText(game.getLoanShark().getInterestHint());
	    // Travel
	    travelTitel1.setText("Du willst dich mal umschauen?");
	    travelTitel2.setText("Klar, aber du wirst den ganzen Tag unterwegs sein.");
	    ticketLabel.setText("Eine Fahrt mit deinem EasyTicket kostet pauschal:");
	    locationLabel.setText("Wohin gehts?");
	    locationSelection.clearItems();
	    Collection<String> itemsLocation = getLocationListFormatted();
	    for(String i : itemsLocation)
	    	locationSelection.addItem(i);
	    // End
	    exit.setLabel("Ich hau ab, kein Bock mehr...");
	}
	
	void updateComponents(Game game) {
		// Current day
	    currentDay.setText(Integer.toString(game.getDayOfGame()));
	    // Current location
	    currentLocation.setText(game.getPlayer().getLocation().getOfficialName());
	    // Cash
	    cash.setText(getMoneyFormatted(game.getPlayer().getCash()));
	    // Sweets in pockets
	    sweetsInPockets.clearItems();
	    Collection<String> itemsPockets = getSnacksFormatted(game.getPlayer().getCandies());
	    for(var i : itemsPockets)
	    	sweetsInPockets.addItem(i);    
	    // Buy
	    buySelection.clearItems();
	    Collection<String> itemsBuy = getDefaultCandiesFormatted();
	    for(var i : itemsBuy)
	    	buySelection.addItem(i);
	    buyQuantity.setText("");
	    buyInfo.setText("");
	    // Sell
	    sellSelection.clearItems();
	    Collection<String> itemsSell = getDefaultCandiesFormatted();
	    for(var i : itemsSell)
	    	sellSelection.addItem(i);
	    sellQuantity.setText("");
	    sellInfo.setText("");
	    // Hide and Seek
	    stash.clearItems();
	    Collection<String> items = getSnacksFormatted(game.getPlayer().getCandyStash());
	    for(var i : items)
	    	stash.addItem(i);
	    seekQuantity.setText("");
	    hideSeekInfo.setText("");
	    // Bank
	    bankBalance.setText(getMoneyFormatted(game.getBank().getBalance(game.getPlayer())));
	    bankInfo.setText("");
	    // Loanshark
	    loansharkBalance.setText(getMoneyFormatted(game.getLoanShark().getBalance(game.getPlayer())));
	    loansharkInfo.setText("");
	    // Travel
	    ticketPrice.setText(getMoneyFormatted(Game.getTravelCosts()));
	    travelEventInfo1.setText("");
	    travelEventInfo2.setText("");
	    travelEventInfo3.setText("");
	    travelInterestInfo.setText("");
	    // Balance sheet
	    balanceSheet.setText(calculateBalanceSheet(game));
	}
	
	void addInputHandling(Game game, InputManager inputManager) {
		EventFactory factory = EventFactory.getDefaultFactory();
		IEvent applyInterestEvent = factory.create("ApplyInterest", game);
		IEvent buyEvent = factory.create("Buy", game);
		IEvent depositEvent = factory.create("Deposit", game);
		IEvent giveBackEvent = factory.create("GiveMoneyBack", game);
		IEvent hideEvent = factory.create("Hide", game);
		IEvent lendEvent = factory.create("Lend", game);
		IEvent sellEvent = factory.create("Sell", game);
	    IEvent seekEvent = factory.create("Seek", game);
	    IEvent travelEvent = factory.create("Travel", game);
	    IEvent withdrawEvent = factory.create("Withdraw", game);
    	
	    inputManager.addListenerDeal(buySelection, buyEvent, buyQuantity);
	    inputManager.addListenerDeal(sellSelection, sellEvent, sellQuantity);
	    inputManager.addListenerHide(hide, hideEvent, game);
	    inputManager.addListenerSeek(seek, seekEvent, stash, seekQuantity, game);
	    inputManager.addListenerTravel(travelEvent, applyInterestEvent, game);
	    inputManager.addListenerExit();
	    inputManager.setInputFilterDeal(buyQuantity, buyEvent, buyInfo, buySelection, game);
	    inputManager.setInputFilterDeal(sellQuantity, sellEvent, sellInfo, sellSelection, game);
	    inputManager.setInputFilterFinances(deposit, depositEvent, bankInfo, locationSelection, game);
	    inputManager.setInputFilterFinances(withdraw, withdrawEvent, bankInfo, locationSelection, game);
	    inputManager.setInputFilterFinances(lend, lendEvent, loansharkInfo, locationSelection, game);
	    inputManager.setInputFilterFinances(giveBack, giveBackEvent, loansharkInfo, locationSelection, game);
	    inputManager.setInputFilterSeek(seekQuantity, seekEvent, hideSeekInfo, stash);
    }
	
	public void disableComponents(Game game) {
		updateComponents(game);
		buySelection.setEnabled(false);
		sellSelection.setEnabled(false);
		seekQuantity.setEnabled(false);
		hide.setEnabled(false);
		seek.setEnabled(false);
		deposit.setEnabled(false);
		withdraw.setEnabled(false);
		lend.setEnabled(false);
		giveBack.setEnabled(false);
		locationSelection.setEnabled(false);
		titel3.setVisible(true);
		balanceSheet.setTheme(new SimpleTheme(new RGB(0, 0, 0), new RGB(255, 240, 140), SGR.BOLD));
	}
	
    // ######################## Utilities
	
	private EmptySpace addEmptyRow() {
    	return new EmptySpace().setLayoutData(GridLayout.createHorizontallyFilledLayoutData(2));
    }
    
    private Separator addHorizontalLine() {
    	return new Separator(Direction.HORIZONTAL).setLayoutData(GridLayout.createHorizontallyFilledLayoutData(2));
    }
    
    String calculateBalanceSheet(Game game) {
		double cash = game.getPlayer().getCash();
		double loan = game.getLoanShark().getBalance(game.getPlayer());
		double balance = game.getBank().getBalance(game.getPlayer());
		StringBuffer answer = new StringBuffer();
		answer.append(String.format("Cash: %,.2f", cash))
			.append(String.format(" | Kredithai: %,.2f", loan))
			.append(String.format(" | Bankkonto: %,.2f", balance))
			.append(String.format("\nSaldo: %,.2f", cash + loan + balance));
		return answer.toString();
	}
    
    ArrayList<String> getDefaultCandiesFormatted() {
	    ArrayList<? extends Snackable> candies = new CandyFactory().getDefaultSnacks();
	    candies.sort(Comparator.comparing(Snackable::getName)); //String implements Comparable
    	ArrayList<String> formattedList = new ArrayList<>();
	    for(Snackable s : candies)
	    	formattedList.add(String.format("%s - %.2f %s", s.getName(), s.getStaticPrice(), MoneyDealer.getCurrency()));
	    return formattedList;
	}

    ArrayList<String> getLocationListFormatted() {
	    ArrayList<String> answer = new ArrayList<>();
	    for(Location c : Location.values())
			answer.add(c.getOfficialName());
	    return answer;
    }
    
    String getMoneyFormatted(double money) {
	   return String.format("%,.2f %s", money, MoneyDealer.getCurrency());
    }
    
	ArrayList<String> getSnacksFormatted(ArrayList<? extends Snackable> snacks){
		snacks.sort(Comparator.comparing(Snackable::getName));
		ArrayList<String> formattedList = new ArrayList<>();
		if(snacks.isEmpty()) {
			formattedList.add("Nix drin!");
			return formattedList;
		}
		for(Snackable s : snacks)
			formattedList.add(String.format("%d | %s", s.getQuantity(), s.getName()));
		return formattedList;
	}
}
