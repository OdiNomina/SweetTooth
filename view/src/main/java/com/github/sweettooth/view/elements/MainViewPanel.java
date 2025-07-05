package com.github.sweettooth.view.elements;

import java.util.ArrayList;
import java.util.Comparator;

import com.github.sweettooth.controller.api.ControllerInterface;

import com.github.sweettooth.model.api.GameModelInterface;
import com.github.sweettooth.model.api.ILocation;
import com.github.sweettooth.model.api.ModelSettings;
import com.github.sweettooth.model.api.viewAPI.IMoneyDealer;
import com.github.sweettooth.model.api.viewAPI.IPlayer;
import com.github.sweettooth.model.api.viewAPI.Snackable;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor.RGB;
import com.googlecode.lanterna.graphics.SimpleTheme;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LayoutData;
import com.googlecode.lanterna.gui2.LayoutManager;
import com.googlecode.lanterna.gui2.Separator;
import com.googlecode.lanterna.gui2.TextBox;

public class MainViewPanel extends ViewPanel {
	private ControllerInterface controller;
	private GameModelInterface gameData;
	private ModelSettings modelSettings;
	
	private IPlayer player;
	private IMoneyDealer loanShark;
	private IMoneyDealer bank;
	
	public MainViewPanel(LayoutManager layoutManager, GameModelInterface gameModel, ControllerInterface controller, ModelSettings modelSettings) {
        super(layoutManager);
        this.controller = controller;
        this.gameData = gameModel;
        this.modelSettings = modelSettings;
        
        player = gameData.player();
        loanShark = gameData.loanShark();
        bank = gameData.bank();
    }
	
	@Override
	public final void addContent() {
		LayoutData HF_1Span = GridLayout.createHorizontallyFilledLayoutData();
		LayoutData HF_2Span = GridLayout.createHorizontallyFilledLayoutData(2);
		LayoutData HEA_1Span = GridLayout.createHorizontallyEndAlignedLayoutData(1);
		LayoutData HEA_2Span = GridLayout.createHorizontallyEndAlignedLayoutData(2);
		
		addComponent(new EmptySpace(), HF_2Span);
		addComponent(labels.get("titel1"), HF_2Span);
		addComponent(labels.get("titel2"), HF_1Span);
		addComponent(labels.get("titel3"), HEA_1Span);
        addComponent(labels.get("currentDayLabel"), HEA_1Span);
        addComponent(labels.get("currentDay"));
        addComponent(new EmptySpace(), HF_2Span);
        addComponent(labels.get("currentLocationLabel"), HEA_1Span);
        addComponent(labels.get("currentLocation"));
        addComponent(new EmptySpace(), HF_2Span);
        addComponent(labels.get("cashLabel"), HEA_1Span);
        addComponent(labels.get("cash"));
        addComponent(new EmptySpace(), HF_2Span);
        addComponent(labels.get("pocketsLabel"), HEA_1Span);
        addComponent(comboBoxes.get("sweetsInPockets"), HF_1Span);

        addComponent(new Separator(Direction.HORIZONTAL).setLayoutData(HF_2Span));
        addComponent(labels.get("buyTitel"), HF_2Span);
        addComponent(labels.get("buySelectionLabel").setLayoutData(HEA_1Span));
        addComponent(comboBoxes.get("buySelection").setLayoutData(HF_1Span));
        addComponent(new EmptySpace());
        addComponent(textBoxesIT.get("buyQuantity").setLayoutData(HF_1Span));

        addComponent(labels.get("sellTitel"), HF_2Span);
        addComponent(labels.get("sellSelectionLabel").setLayoutData(HEA_1Span));
        addComponent(comboBoxes.get("sellSelection").setLayoutData(HF_1Span));
        addComponent(new EmptySpace());
        addComponent(textBoxesIT.get("sellQuantity").setLayoutData(HF_1Span));      
        addComponent(labels.get("buySellInfo").setLayoutData(HEA_2Span));
        
        addComponent(new Separator(Direction.HORIZONTAL), HF_2Span);
        addComponent(labels.get("hideSeekTitel"), HF_2Span);
        addComponent(new EmptySpace());
        addComponent(buttons.get("hide"));
        addComponent(new EmptySpace(), HF_2Span);
        addComponent(labels.get("stashLabel"), HEA_1Span);
        addComponent(comboBoxes.get("stash").setLayoutData(HF_1Span));
        addComponent(labels.get("seekQuantityLabel").setLayoutData(HEA_1Span));
        addComponent(textBoxes.get("seekQuantity").setLayoutData(HF_1Span));
        addComponent(new EmptySpace());
        addComponent(buttons.get("seek"));
        addComponent(labels.get("hideSeekInfo").setLayoutData(HEA_2Span));

        addComponent(new Separator(Direction.HORIZONTAL), HF_2Span);
        addComponent(labels.get("bankTitel"), HF_2Span);
        addComponent(labels.get("bankBalanceLabel"), HEA_1Span);
        addComponent(labels.get("bankBalance"));
        addComponent(labels.get("depositLabel").setLayoutData(HEA_1Span));
        addComponent(textBoxesIT.get("deposit").setLayoutData(HF_1Span));
        addComponent(labels.get("withdrawLabel").setLayoutData(HEA_1Span));
        addComponent(textBoxesIT.get("withdraw").setLayoutData(HF_1Span));
        addComponent(labels.get("bankInfo").setLayoutData(HEA_2Span));
        addComponent(labels.get("bankDispoHint"), HF_2Span);
        addComponent(labels.get("bankInterestHint"), HF_2Span);
        
        addComponent(new Separator(Direction.HORIZONTAL), HF_2Span);
        addComponent(labels.get("loansharkTitel"), HF_2Span);
        addComponent(labels.get("loansharkBalanceLabel"), HEA_1Span);
        addComponent(labels.get("loansharkBalance"));
        addComponent(labels.get("lendLabel").setLayoutData(HEA_1Span));
        addComponent(textBoxesIT.get("lend").setLayoutData(HF_1Span));
        addComponent(labels.get("giveBackLabel").setLayoutData(HEA_1Span));
        addComponent(textBoxesIT.get("giveBack").setLayoutData(HF_1Span));
        addComponent(labels.get("loansharkInfo").setLayoutData(HEA_2Span));
        addComponent(labels.get("loansharkInterestHint"), HF_2Span);
        
        addComponent(new Separator(Direction.HORIZONTAL), HF_2Span);
        addComponent(labels.get("travelTitel1"), HF_2Span);
        addComponent(labels.get("travelTitel2"), HF_2Span);
        addComponent(labels.get("locationLabel").setLayoutData(HEA_1Span));
        addComponent(comboBoxes.get("locationSelection").setLayoutData(HF_1Span));
        addComponent(labels.get("ticketLabel").setLayoutData(HEA_1Span));
        addComponent(labels.get("ticketPrice"));
        addComponent(labels.get("travel2").setLayoutData(HEA_2Span));
        addComponent(labels.get("travel1"), HEA_2Span);
        addComponent(labels.get("travel3"), HF_2Span);
        addComponent(labels.get("travelInterest"), HF_2Span);
        
        addComponent(new Separator(Direction.HORIZONTAL), HF_2Span);
        addComponent(textBoxes.get("balanceSheet"), GridLayout.createLayoutData(GridLayout.Alignment.FILL, GridLayout.Alignment.FILL, true, true, 2, 1));
        addComponent(buttons.get("exit").setLayoutData(HEA_2Span));
	}

	@Override
	public void addInputHandling() {
		comboBoxes.get("buySelection").addListener(controller.createComboBoxListener(comboBoxes.get("buySelection"), "Buy", textBoxesIT.get("buyQuantity"), labels.get("buySellInfo")));
		comboBoxes.get("sellSelection").addListener(controller.createComboBoxListener(comboBoxes.get("sellSelection"), "Sell", textBoxesIT.get("sellQuantity"), labels.get("buySellInfo")));
		comboBoxes.get("locationSelection").addListener(
				controller.createComboBoxListener(comboBoxes.get("locationSelection"), "Travel", comboBoxes.get("locationSelection"), labels.get("travel1"), labels.get("travel2"), labels.get("travel3"), labels.get("travelInterest")));

		buttons.get("hide").addListener(controller.createButtonListener("Hide", null, null, buttons.get("hide"), labels.get("hideSeekInfo")));
		buttons.get("seek").addListener(controller.createButtonListener("Seek", comboBoxes.get("stash"), textBoxes.get("seekQuantity"), comboBoxes.get("stash"), labels.get("hideSeekInfo")));
		buttons.get("exit").addListener(controller.createButtonListener("Exit", null, null, buttons.get("exit"), labels.get("titel3")));
		
		textBoxes.get("seekQuantity").setInputFilter(controller.createInputFilter("Seek", comboBoxes.get("stash"), buttons.get("seek"), labels.get("hideSeekInfo")));
		
		textBoxesIT.get("buyQuantity").setInputFilter(controller.createInputFilter("Buy", comboBoxes.get("buySelection"), comboBoxes.get("buySelection"), labels.get("buySellInfo")));
		textBoxesIT.get("sellQuantity").setInputFilter(controller.createInputFilter("Sell", comboBoxes.get("sellSelection"), comboBoxes.get("sellSelection"), labels.get("buySellInfo")));
		textBoxesIT.get("deposit").setInputFilter(controller.createInputFilter("Deposit", null, textBoxesIT.get("deposit"), labels.get("bankInfo")));
		textBoxesIT.get("withdraw").setInputFilter(controller.createInputFilter("Withdraw", null, textBoxesIT.get("withdraw"), labels.get("bankInfo")));
		textBoxesIT.get("lend").setInputFilter(controller.createInputFilter("Lend", null, textBoxesIT.get("lend"), labels.get("loansharkInfo")));
		textBoxesIT.get("giveBack").setInputFilter(controller.createInputFilter("GiveMoneyBack", null, textBoxesIT.get("giveBack"), labels.get("loansharkInfo")));
	}

	@Override
	public final void createContent() {
		labels.put("titel1", new Label(""));
		labels.put("titel2", new Label(""));
		labels.put("titel3", new Label(""));
		labels.put("currentDayLabel", new Label(""));
		labels.put("currentDay", new Label(""));
		labels.put("currentLocationLabel", new Label(""));
		labels.put("currentLocation", new Label(""));
		labels.put("cashLabel", new Label(""));
		labels.put("cash", new Label(""));
		labels.put("pocketsLabel", new Label(""));
		
		labels.put("buyTitel", new Label(""));
		labels.put("buySelectionLabel", new Label(""));
		labels.put("sellTitel", new Label(""));
		labels.put("sellSelectionLabel", new Label(""));
		labels.put("buySellInfo", new Label(""));
		
		labels.put("hideSeekTitel", new Label(""));
		labels.put("stashLabel", new Label(""));
		labels.put("seekQuantityLabel", new Label(""));
		labels.put("hideSeekInfo", new Label(""));
		
		labels.put("bankTitel", new Label(""));
		labels.put("bankBalanceLabel", new Label(""));
		labels.put("bankBalance", new Label(""));
		labels.put("depositLabel", new Label(""));
		labels.put("withdrawLabel", new Label(""));
		labels.put("bankInfo", new Label(""));
		labels.put("bankDispoHint", new Label(""));
		labels.put("bankInterestHint", new Label(""));
		
		labels.put("loansharkTitel", new Label(""));
		labels.put("loansharkBalanceLabel", new Label(""));
		labels.put("loansharkBalance", new Label(""));
		labels.put("lendLabel", new Label(""));
		labels.put("giveBackLabel", new Label(""));
		labels.put("loansharkInfo", new Label(""));
		labels.put("loansharkInterestHint", new Label(""));
		
		labels.put("travelTitel1", new Label(""));
		labels.put("travelTitel2", new Label(""));
		labels.put("ticketLabel", new Label(""));
		labels.put("ticketPrice", new Label(""));
		labels.put("locationLabel", new Label(""));
		labels.put("travel1", new Label(""));
		labels.put("travel2", new Label(""));
		labels.put("travel3", new Label(""));
		labels.put("travelInterest", new Label(""));
		
		comboBoxes.put("sweetsInPockets", new ComboBox<>(""));
		comboBoxes.put("buySelection", new ComboBox<>(""));
		comboBoxes.put("sellSelection", new ComboBox<>(""));
		comboBoxes.put("stash", new ComboBox<>(""));
		comboBoxes.put("locationSelection", new ComboBox<>(""));
	
		textBoxes.put("seekQuantity", new TextBox(""));
		textBoxes.put("balanceSheet", new TextBox("", TextBox.Style.MULTI_LINE));
		textBoxesIT.put("buyQuantity", new ExtendedTextBox(""));
		textBoxesIT.put("sellQuantity", new ExtendedTextBox(""));
		textBoxesIT.put("deposit", new ExtendedTextBox(""));
		textBoxesIT.put("withdraw", new ExtendedTextBox(""));
		textBoxesIT.put("lend", new ExtendedTextBox(""));
		textBoxesIT.put("giveBack", new ExtendedTextBox(""));
	
		buttons.put("hide", new Button(""));
		buttons.put("seek", new Button(""));
		buttons.put("exit", new Button(""));
	}

    @Override
	public void initializeContent() {
		labels.get("titel1").addStyle(SGR.BOLD).setText("Du dealst mit Süßis?");
		labels.get("titel2").addStyle(SGR.BOLD).setText("Mal sehen was du in einem Monat verdienst...");
		labels.get("titel3").addStyle(SGR.BOLD).addStyle(SGR.REVERSE).setVisible(false).setText(" Das wars... NICHTS GEHT MEHR ! ");
		labels.get("currentDayLabel").addStyle(SGR.BOLD).setText("Tag:");
		labels.get("currentLocationLabel").addStyle(SGR.BOLD).setText("Wo bin ich eigentlich...?");
		labels.get("cashLabel").addStyle(SGR.BOLD).setText("Cash dabei:");
		labels.get("pocketsLabel").addStyle(SGR.BOLD).setText("Was hab ich in den Taschen?"); 
		
		labels.get("buyTitel").addStyle(SGR.BOLD).setText("Hast du was für mich?");
		labels.get("buySelectionLabel").setText("Ich mag..."); 
		labels.get("sellTitel").addStyle(SGR.BOLD).setText("Hey! Willst du was Süßes?");
		labels.get("sellSelectionLabel").setText("Ich verkaufe dir...");
		
		labels.get("hideSeekTitel").addStyle(SGR.BOLD).setText("Du hast ein echt gutes Versteck für deine Süßis, da sind sie sicher!");
		labels.get("stashLabel").addStyle(SGR.BOLD).setText("Was liegt schon im Versteck?");
		labels.get("seekQuantityLabel").setText("hmm... wie viel");
		
		labels.get("bankTitel").addStyle(SGR.BOLD).setText("BANK:");
	    labels.get("bankBalanceLabel").addStyle(SGR.BOLD).setText("Kontostand:");
	    labels.get("depositLabel").setText("Ich möchte Geld einzahlen.");
	    labels.get("withdrawLabel").setText("Ich würde gerne Geld abheben.");
	    labels.get("bankInterestHint").addStyle(SGR.ITALIC).setText(bank.getInterestHint());
	    labels.get("bankDispoHint").addStyle(SGR.ITALIC).setText(bank.getDispoHint());
	    
	    labels.get("loansharkTitel").addStyle(SGR.BOLD).setText("KREDITHAI:");
	    labels.get("loansharkBalanceLabel").addStyle(SGR.BOLD).setText("Schulden:");
	    labels.get("lendLabel").setText("Ich brauch Geld.");
	    labels.get("giveBackLabel").setText("Hier, ich hab dein Geld dabei.");
	    labels.get("loansharkInterestHint").addStyle(SGR.ITALIC).setText(loanShark.getInterestHint());
	    
	    labels.get("travelTitel1").addStyle(SGR.BOLD).setText("Du willst dich mal umschauen?");
	    labels.get("travelTitel2").addStyle(SGR.BOLD).setText("Klar, aber du wirst den ganzen Tag unterwegs sein.");
	    labels.get("ticketLabel").setText("Eine Fahrt mit deinem EasyTicket kostet pauschal:");
	    labels.get("locationLabel").setText("Wohin gehts?");
	    labels.get("travel1").addStyle(SGR.BOLD).addStyle(SGR.REVERSE);
	    labels.get("travel3").addStyle(SGR.ITALIC);
	    labels.get("travelInterest").addStyle(SGR.ITALIC);
	    
	    textBoxes.get("balanceSheet").setEnabled(false).setTheme(new SimpleTheme(new RGB(50, 50, 0), new RGB(250, 220, 100), SGR.BOLD));
	    
	    textBoxesIT.get("buyQuantity").setInitialText("").setEnabled(false);
	    textBoxesIT.get("sellQuantity").setInitialText("").setEnabled(false);
	    textBoxesIT.get("deposit").setInitialText("Natürlich, welchen Betrag?");
	    textBoxesIT.get("withdraw").setInitialText("Gerne, wie viel?");
		textBoxesIT.get("lend").setInitialText("Wie viel willst du?!");
		textBoxesIT.get("giveBack").setInitialText("Lass sehn...");
	    
		comboBoxes.get("sweetsInPockets").setReadOnly(true);
	    comboBoxes.get("buySelection").setReadOnly(true);
	    comboBoxes.get("sellSelection").setReadOnly(true);
	    comboBoxes.get("stash").setReadOnly(true);
	    ArrayList<String> locationList = new ArrayList<>();
	    for(ILocation l : ILocation.values())
	    	locationList.add(l.getOfficialName());
	    ComboBox<String> locationSelection = comboBoxes.get("locationSelection").setReadOnly(true).clearItems();
	    for(String s : locationList)
	    	locationSelection.addItem(s);
	    
	    buttons.get("hide").setLabel("Alles Verstecken");
	    buttons.get("seek").setEnabled(false).setLabel("Aus dem Versteck holen");
	    buttons.get("exit").setLabel("Ich hau ab, kein Bock mehr...");
	}
    
    @Override
    public void gameOverConfig() {
		labels.get("titel3").setVisible(true);
		
		textBoxes.get("balanceSheet").setTheme(new SimpleTheme(new RGB(0, 0, 0), new RGB(255, 240, 140), SGR.BOLD));
		textBoxes.get("seekQuantity").setEnabled(false);
		textBoxesIT.get("deposit").setEnabled(false);
	    textBoxesIT.get("withdraw").setEnabled(false);
	    textBoxesIT.get("lend").setEnabled(false);
	    textBoxesIT.get("giveBack").setEnabled(false);
		
		comboBoxes.get("buySelection").setEnabled(false);
	    comboBoxes.get("sellSelection").setEnabled(false);
	    comboBoxes.get("locationSelection").setEnabled(false);
	    
	    buttons.get("hide").setEnabled(false);
    	buttons.get("seek").setEnabled(false);
    }
    
    @Override
	public void updateContent() {
		labels.get("currentDay").setText(Integer.toString(gameData.getDayOfGame()));
	    labels.get("currentLocation").setText(player.location().getOfficialName());
	    labels.get("cash").setText(formatMoney(player.cash()));
	    labels.get("buySellInfo").setText("");
	    labels.get("hideSeekInfo").setText("");
	    labels.get("bankBalance").setText(formatMoney(bank.clientsBalance(player)));
	    labels.get("bankInfo").setText("");
	    labels.get("loansharkBalance").setText(formatMoney(loanShark.clientsBalance(player)));
	    labels.get("loansharkInfo").setText("");
	    labels.get("ticketPrice").setText(formatMoney(modelSettings.getTravelCosts()));
	    labels.get("travel1").setText("");
	    labels.get("travel2").setText("");
	    labels.get("travel3").setText("");
	    labels.get("travelInterest").setText("");
	    
	    textBoxes.get("seekQuantity").setText("");
	    textBoxes.get("balanceSheet").setText(formatBalanceSheet());
	    
	    ExtendedTextBox deposit = textBoxesIT.get("deposit");
	    deposit.setText(deposit.getInitialText());
	    
	    ExtendedTextBox withdraw = textBoxesIT.get("withdraw");
	    withdraw.setText(withdraw.getInitialText());
	    
	    ExtendedTextBox lend = textBoxesIT.get("lend");
	    lend.setText(lend.getInitialText());
	    
	    ExtendedTextBox giveBack = textBoxesIT.get("giveBack");
	    giveBack.setText(giveBack.getInitialText());
	    
	    ArrayList<String> sweetsList = formatSnacks(player.snacks());
	    ComboBox<String> sweetsInPockets = comboBoxes.get("sweetsInPockets").clearItems();
	    for(String s : sweetsList)
	    	sweetsInPockets.addItem(s);
	    
	    ArrayList<String> buyList = formatDefaultCandies();
	    ComboBox<String> buySelection = comboBoxes.get("buySelection").clearItems();
	    for(String s : buyList)
	    	buySelection.addItem(s);
	
	    
	    ArrayList<String> sellList = formatDefaultCandies();
	    ComboBox<String> sellSelection = comboBoxes.get("sellSelection").clearItems();
	    for(String s : sellList)
	    	sellSelection.addItem(s);
	
	    ArrayList<String> stashList = formatSnacks(player.stash());
	    ComboBox<String> stash = comboBoxes.get("stash").clearItems();
	    for(String s : stashList)
	    	stash.addItem(s);	
	}
    
    /*
	 * %[flags][.precision]conversion
	 * Flag ',': The result will include locale-specific grouping separators.
	 * Conversion 'f': The result is formatted as a decimal number.
	 */
    private String formatBalanceSheet() {
		double cash = player.cash();
		double loan = loanShark.clientsBalance(player);
		double balance = bank.clientsBalance(player);
		StringBuffer answer = new StringBuffer();
		answer.append(String.format(modelSettings.getLocale(), "Cash: %,.2f %s", cash, modelSettings.getCurrency()))
		.append(String.format(modelSettings.getLocale(), " | Kredithai: %,.2f %s", loan, modelSettings.getCurrency()))
		.append(String.format(modelSettings.getLocale(), " | Bankkonto: %,.2f %s", balance, modelSettings.getCurrency()))
		.append(String.format(modelSettings.getLocale(), "\nSaldo: %,.2f %s", cash + loan + balance, modelSettings.getCurrency()));
		return answer.toString();
	}
	
	private ArrayList<String> formatDefaultCandies() {
	    ArrayList<? extends Snackable> candies = modelSettings.getSnackFactory().defaultSnacks();
	    candies.sort(Comparator.comparing(Snackable::name)); //String implements Comparable
    	ArrayList<String> formattedList = new ArrayList<>();
	    for(Snackable s : candies)
	    	formattedList.add(String.format(modelSettings.getLocale(), "%s - %.2f %s", s.name(), s.staticPrice(), modelSettings.getCurrency()));
	    return formattedList;
	}
	
	private String formatMoney(double money) {
	   return String.format(modelSettings.getLocale(), "%,.2f %s", money, modelSettings.getCurrency());
    }

	private ArrayList<String> formatSnacks(ArrayList<? extends Snackable> snacks){
		snacks.sort(Comparator.comparing(Snackable::name));
		ArrayList<String> formattedList = new ArrayList<>();
		if(snacks.isEmpty()) {
			formattedList.add("Nix drin!");
			return formattedList;
		}
		for(Snackable s : snacks)
			formattedList.add(String.format(modelSettings.getLocale(), "%,d | %s", s.quantity(), s.name()));
		return formattedList;
	}
}