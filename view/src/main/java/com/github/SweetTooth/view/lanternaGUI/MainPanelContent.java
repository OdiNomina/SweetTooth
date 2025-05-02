package com.github.SweetTooth.view.lanternaGUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import com.github.SweetTooth.controller.controllerAPI.LanternaController;
import com.github.SweetTooth.model.characters.MoneyDealer;
import com.github.SweetTooth.model.game.Game;
import com.github.SweetTooth.model.locations.Location;
import com.github.SweetTooth.model.snacks.CandyFactory;
import com.github.SweetTooth.model.snacks.Snackable;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor.RGB;
import com.googlecode.lanterna.graphics.SimpleTheme;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.InputFilter;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LayoutData;
import com.googlecode.lanterna.gui2.LayoutManager;
import com.googlecode.lanterna.gui2.Separator;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

public class MainPanelContent extends PanelContent<String> {
	Game game;
	
	public MainPanelContent(LayoutManager layoutManager, LanternaController controller) {
        super(layoutManager);
        game = controller.getGame();
    }
	
	@Override
	public void createContent() {
		getLabels().put("titel1", new Label(""));
		getLabels().put("titel2", new Label(""));
		getLabels().put("titel3", new Label(""));
		getLabels().put("currentDayLabel", new Label(""));
		getLabels().put("currentDay", new Label(""));
		getLabels().put("currentLocationLabel", new Label(""));
		getLabels().put("currentLocation", new Label(""));
		getLabels().put("cashLabel", new Label(""));
		getLabels().put("cash", new Label(""));
		getLabels().put("pocketsLabel", new Label(""));
		
		getLabels().put("buyTitel", new Label(""));
		getLabels().put("buySelectionLabel", new Label(""));
		getLabels().put("sellTitel", new Label(""));
		getLabels().put("sellSelectionLabel", new Label(""));
		getLabels().put("buySellInfo", new Label(""));
		
		getLabels().put("hideSeekTitel", new Label(""));
		getLabels().put("stashLabel", new Label(""));
		getLabels().put("seekQuantityLabel", new Label(""));
		getLabels().put("hideSeekInfo", new Label(""));
		
		getLabels().put("bankTitel", new Label(""));
		getLabels().put("bankBalanceLabel", new Label(""));
		getLabels().put("bankBalance", new Label(""));
		getLabels().put("depositLabel", new Label(""));
		getLabels().put("withdrawLabel", new Label(""));
		getLabels().put("bankInfo", new Label(""));
		getLabels().put("bankDispoHint", new Label(""));
		getLabels().put("bankInterestHint", new Label(""));
		
		getLabels().put("loansharkTitel", new Label(""));
		getLabels().put("loansharkBalanceLabel", new Label(""));
		getLabels().put("loansharkBalance", new Label(""));
		getLabels().put("lendLabel", new Label(""));
		getLabels().put("giveBackLabel", new Label(""));
		getLabels().put("loansharkInfo", new Label(""));
		getLabels().put("loansharkInterestHint", new Label(""));
		
		getLabels().put("travelTitel1", new Label(""));
		getLabels().put("travelTitel2", new Label(""));
		getLabels().put("ticketLabel", new Label(""));
		getLabels().put("ticketPrice", new Label(""));
		getLabels().put("locationLabel", new Label(""));
		getLabels().put("travelEventInfo1", new Label(""));
		getLabels().put("travelEventInfo2", new Label(""));
		getLabels().put("travelEventInfo3", new Label(""));
		getLabels().put("travelInterestInfo", new Label(""));
		
		getComboBoxes().put("sweetsInPockets", new ComboBox<>(""));
		getComboBoxes().put("buySelection", new ComboBox<>(""));
		getComboBoxes().put("sellSelection", new ComboBox<>(""));
		getComboBoxes().put("stash", new ComboBox<>(""));
		getComboBoxes().put("locationSelection", new ComboBox<>(""));
	
		getTextBoxes().put("seekQuantity", new TextBox(""));
		getTextBoxes().put("balanceSheet", new TextBox("", TextBox.Style.MULTI_LINE));
		getTextBoxesIT().put("buyQuantity", new TextBoxInitialText(""));
		getTextBoxesIT().put("sellQuantity", new TextBoxInitialText(""));
		getTextBoxesIT().put("deposit", new TextBoxInitialText("Natürlich, welchen Betrag?"));
		getTextBoxesIT().put("withdraw", new TextBoxInitialText("Gerne, wie viel?"));
		getTextBoxesIT().put("lend", new TextBoxInitialText("Wie viel willst du?!"));
		getTextBoxesIT().put("giveBack", new TextBoxInitialText("Lass sehn..."));
	
		getButtons().put("hide", new Button(""));
		getButtons().put("seek", new Button(""));
		getButtons().put("exit", new Button(""));
	}

	@Override
	public void addContent() {
		LayoutData HF_1Span = GridLayout.createHorizontallyFilledLayoutData();
		LayoutData HF_2Span = GridLayout.createHorizontallyFilledLayoutData(2);
		LayoutData HEA_1Span = GridLayout.createHorizontallyEndAlignedLayoutData(1);
		LayoutData HEA_2Span = GridLayout.createHorizontallyEndAlignedLayoutData(2);
		
		addComponent(new EmptySpace(), HF_2Span);
		addComponent(getLabels().get("titel1"), HF_2Span);
		addComponent(getLabels().get("titel2"), HF_1Span);
		addComponent(getLabels().get("titel3"), HEA_1Span);
        addComponent(getLabels().get("currentDayLabel"), HEA_1Span);
        addComponent(getLabels().get("currentDay"));
        addComponent(new EmptySpace(), HF_2Span);
        addComponent(getLabels().get("currentLocationLabel"), HEA_1Span);
        addComponent(getLabels().get("currentLocation"));
        addComponent(new EmptySpace(), HF_2Span);
        addComponent(getLabels().get("cashLabel"), HEA_1Span);
        addComponent(getLabels().get("cash"));
        addComponent(new EmptySpace(), HF_2Span);
        addComponent(getLabels().get("pocketsLabel"), HEA_1Span);
        addComponent(getComboBoxes().get("sweetsInPockets"), HF_1Span);

        addComponent(new Separator(Direction.HORIZONTAL).setLayoutData(HF_2Span));
        addComponent(getLabels().get("buyTitel"), HF_2Span);
        addComponent(getLabels().get("buySelectionLabel").setLayoutData(HEA_1Span));
        addComponent(getComboBoxes().get("buySelection").setLayoutData(HF_1Span));
        addComponent(new EmptySpace());
        addComponent(getTextBoxesIT().get("buyQuantity").setLayoutData(HF_1Span));

        addComponent(getLabels().get("sellTitel"), HF_2Span);
        addComponent(getLabels().get("sellSelectionLabel").setLayoutData(HEA_1Span));
        addComponent(getComboBoxes().get("sellSelection").setLayoutData(HF_1Span));
        addComponent(new EmptySpace());
        addComponent(getTextBoxesIT().get("sellQuantity").setLayoutData(HF_1Span));      
        addComponent(getLabels().get("buySellInfo").setLayoutData(HEA_2Span));
        
        addComponent(new Separator(Direction.HORIZONTAL), HF_2Span);
        addComponent(getLabels().get("hideSeekTitel"), HF_2Span);
        addComponent(new EmptySpace());
        addComponent(getButtons().get("hide"));
        addComponent(new EmptySpace(), HF_2Span);
        addComponent(getLabels().get("stashLabel"), HEA_1Span);
        addComponent(getComboBoxes().get("stash").setLayoutData(HF_1Span));
        addComponent(getLabels().get("seekQuantityLabel").setLayoutData(HEA_1Span));
        addComponent(getTextBoxes().get("seekQuantity").setLayoutData(HF_1Span));
        addComponent(new EmptySpace());
        addComponent(getButtons().get("seek"));
        addComponent(getLabels().get("hideSeekInfo").setLayoutData(HEA_2Span));

        addComponent(new Separator(Direction.HORIZONTAL), HF_2Span);
        addComponent(getLabels().get("bankTitel"), HF_2Span);
        addComponent(getLabels().get("bankBalanceLabel"), HEA_1Span);
        addComponent(getLabels().get("bankBalance"));
        addComponent(getLabels().get("depositLabel").setLayoutData(HEA_1Span));
        addComponent(getTextBoxesIT().get("deposit").setLayoutData(HF_1Span));
        addComponent(getLabels().get("withdrawLabel").setLayoutData(HEA_1Span));
        addComponent(getTextBoxesIT().get("withdraw").setLayoutData(HF_1Span));
        addComponent(getLabels().get("bankInfo").setLayoutData(HEA_2Span));
        addComponent(getLabels().get("bankDispoHint"), HF_2Span);
        addComponent(getLabels().get("bankInterestHint"), HF_2Span);
        
        addComponent(new Separator(Direction.HORIZONTAL), HF_2Span);
        addComponent(getLabels().get("loansharkTitel"), HF_2Span);
        addComponent(getLabels().get("loansharkBalanceLabel"), HEA_1Span);
        addComponent(getLabels().get("loansharkBalance"));
        addComponent(getLabels().get("lendLabel").setLayoutData(HEA_1Span));
        addComponent(getTextBoxesIT().get("lend").setLayoutData(HF_1Span));
        addComponent(getLabels().get("giveBackLabel").setLayoutData(HEA_1Span));
        addComponent(getTextBoxesIT().get("giveBack").setLayoutData(HF_1Span));
        addComponent(getLabels().get("loansharkInfo").setLayoutData(HEA_2Span));
        addComponent(getLabels().get("loansharkInterestHint"), HF_2Span);
        
        addComponent(new Separator(Direction.HORIZONTAL), HF_2Span);
        addComponent(getLabels().get("travelTitel1"), HF_2Span);
        addComponent(getLabels().get("travelTitel2"), HF_2Span);
        addComponent(getLabels().get("locationLabel").setLayoutData(HEA_1Span));
        addComponent(getComboBoxes().get("locationSelection").setLayoutData(HF_1Span));
        addComponent(getLabels().get("ticketLabel").setLayoutData(HEA_1Span));
        addComponent(getLabels().get("ticketPrice"));
        addComponent(getLabels().get("travelEventInfo2").setLayoutData(HEA_2Span));
        addComponent(getLabels().get("travelEventInfo1"), HEA_2Span);
        addComponent(getLabels().get("travelEventInfo3"), HF_2Span);
        addComponent(getLabels().get("travelInterestInfo"), HF_2Span);
        
        addComponent(new Separator(Direction.HORIZONTAL), HF_2Span);
        addComponent(getTextBoxes().get("balanceSheet"), GridLayout.createLayoutData(GridLayout.Alignment.FILL, GridLayout.Alignment.FILL, true, true, 2, 1));
        addComponent(getButtons().get("exit").setLayoutData(HEA_2Span));
	}

	@Override
	public void initializeContent() {
		getLabels().get("titel1").addStyle(SGR.BOLD).setText("Du dealst mit Süßis?");
		getLabels().get("titel2").addStyle(SGR.BOLD).setText("Mal sehen was du in einem Monat verdienst...");
		getLabels().get("titel3").addStyle(SGR.BOLD).addStyle(SGR.REVERSE).setVisible(false).setText(" Das wars... NICHTS GEHT MEHR ! ");
		getLabels().get("currentDayLabel").addStyle(SGR.BOLD).setText("Tag:");
		getLabels().get("currentLocationLabel").addStyle(SGR.BOLD).setText("Wo bin ich eigentlich...?");
		getLabels().get("cashLabel").addStyle(SGR.BOLD).setText("Cash dabei:");
		getLabels().get("pocketsLabel").addStyle(SGR.BOLD).setText("Was hab ich in den Taschen?"); 
		
		getLabels().get("buyTitel").addStyle(SGR.BOLD).setText("Hast du was für mich?");
		getLabels().get("buySelectionLabel").setText("Ich mag..."); 
		getLabels().get("sellTitel").addStyle(SGR.BOLD).setText("Hey! Willst du was Süßes?");
		getLabels().get("sellSelectionLabel").setText("Ich verkaufe dir...");
		
		getLabels().get("hideSeekTitel").addStyle(SGR.BOLD).setText("Du hast ein echt gutes Versteck für deine Süßis, da sind sie sicher!");
		getLabels().get("stashLabel").addStyle(SGR.BOLD).setText("Was liegt schon im Versteck?");
		getLabels().get("seekQuantityLabel").setText("hmm... wie viel");
		
		getLabels().get("bankTitel").addStyle(SGR.BOLD).setText("BANK:");
	    getLabels().get("bankBalanceLabel").addStyle(SGR.BOLD).setText("Kontostand:");
	    getLabels().get("depositLabel").setText("Ich möchte Geld einzahlen.");
	    getLabels().get("withdrawLabel").setText("Ich würde gerne Geld abheben.");
	    getLabels().get("bankInterestHint").addStyle(SGR.ITALIC).setText(game.getBank().getInterestHint());
	    getLabels().get("bankDispoHint").addStyle(SGR.ITALIC).setText(game.getBank().getDispoHint());
	    
	    getLabels().get("loansharkTitel").addStyle(SGR.BOLD).setText("KREDITHAI:");
	    getLabels().get("loansharkBalanceLabel").addStyle(SGR.BOLD).setText("Schulden:");
	    getLabels().get("lendLabel").setText("Ich brauch Geld.");
	    getLabels().get("giveBackLabel").setText("Hier, ich hab dein Geld dabei.");
	    getLabels().get("loansharkInterestHint").addStyle(SGR.ITALIC).setText(game.getLoanShark().getInterestHint());
	    
	    getLabels().get("travelTitel1").addStyle(SGR.BOLD).setText("Du willst dich mal umschauen?");
	    getLabels().get("travelTitel2").addStyle(SGR.BOLD).setText("Klar, aber du wirst den ganzen Tag unterwegs sein.");
	    getLabels().get("ticketLabel").setText("Eine Fahrt mit deinem EasyTicket kostet pauschal:");
	    getLabels().get("locationLabel").setText("Wohin gehts?");
	    getLabels().get("travelEventInfo1").addStyle(SGR.BOLD).addStyle(SGR.REVERSE);
	    getLabels().get("travelEventInfo3").addStyle(SGR.ITALIC);
	    getLabels().get("travelInterestInfo").addStyle(SGR.ITALIC);
	    
	    getTextBoxes().get("balanceSheet").setEnabled(false).setTheme(new SimpleTheme(new RGB(50, 50, 0), new RGB(250, 220, 100), SGR.BOLD));
	    getTextBoxesIT().get("buyQuantity").setEnabled(false);
	    getTextBoxesIT().get("sellQuantity").setEnabled(false);
	    
		getComboBoxes().get("sweetsInPockets").setReadOnly(true);
	    getComboBoxes().get("buySelection").setReadOnly(true);
	    getComboBoxes().get("sellSelection").setReadOnly(true);
	    getComboBoxes().get("stash").setReadOnly(true);
	    ArrayList<String> locationList = new ArrayList<>();
	    for(Location l : Location.values())
	    	locationList.add(l.getOfficialName());
	    ComboBox<String> locationSelection = getComboBoxes().get("locationSelection").setReadOnly(true).clearItems();
	    for(String s : locationList)
	    	locationSelection.addItem(s);
	    
	    getButtons().get("hide").setLabel("Alles Verstecken");
	    getButtons().get("seek").setEnabled(false).setLabel("Aus dem Versteck holen");
	    getButtons().get("exit").setLabel("Ich hau ab, kein Bock mehr...");
	}
	
	@Override
	public void disableComponents() {
		updateContent();
		
		getLabels().get("titel3").setVisible(true);
		
		getTextBoxes().get("balanceSheet").setTheme(new SimpleTheme(new RGB(0, 0, 0), new RGB(255, 240, 140), SGR.BOLD));
		getTextBoxesIT().get("seekQuantity").setEnabled(false);
		getTextBoxesIT().get("deposit").setEnabled(false);
	    getTextBoxesIT().get("withdraw").setEnabled(false);
	    getTextBoxesIT().get("lend").setEnabled(false);
	    getTextBoxesIT().get("giveBack").setEnabled(false);
		
		getComboBoxes().get("buySelection").setEnabled(false);
	    getComboBoxes().get("sellSelection").setEnabled(false);
	    getComboBoxes().get("locationSelection").setEnabled(false);
	    
	    getButtons().get("hide").setEnabled(false);
	    getButtons().get("seek").setEnabled(false);
	}
	
	@Override
	public void updateContent() {
	    getLabels().get("currentDay").setText(Integer.toString(game.getDayOfGame()));
	    getLabels().get("currentLocation").setText(game.getPlayer().getLocation().getOfficialName());
	    getLabels().get("cash").setText(getMoneyFormatted(game.getPlayer().getCash()));
	    getLabels().get("buySellInfo").setText("");
	    getLabels().get("hideSeekInfo").setText("");
	    getLabels().get("bankBalance").setText(getMoneyFormatted(game.getBank().getBalance(game.getPlayer())));
	    getLabels().get("bankInfo").setText("");
	    getLabels().get("loansharkBalance").setText(getMoneyFormatted(game.getLoanShark().getBalance(game.getPlayer())));
	    getLabels().get("loansharkInfo").setText("");
	    getLabels().get("ticketPrice").setText(getMoneyFormatted(game.getTravelCosts()));
	    getLabels().get("travelEventInfo1").setText("");
	    getLabels().get("travelEventInfo2").setText("");
	    getLabels().get("travelEventInfo3").setText("");
	    getLabels().get("travelInterestInfo").setText("");
	    
	    getTextBoxes().get("seekQuantity").setText("");
	    getTextBoxes().get("balanceSheet").setText(calculateBalanceSheet(game));
	    
	    ArrayList<String> sweetsList = getSnacksFormatted(game.getPlayer().getCandies());
	    ComboBox<String> sweetsInPockets = getComboBoxes().get("sweetsInPockets").clearItems();
	    for(String s : sweetsList)
	    	sweetsInPockets.addItem(s);
	    
	    ArrayList<String> buyList = getDefaultCandiesFormatted();
	    ComboBox<String> buySelection = getComboBoxes().get("buySelection").clearItems();
	    for(String s : buyList)
	    	buySelection.addItem(s);
	
	    
	    ArrayList<String> sellList = getDefaultCandiesFormatted();
	    ComboBox<String> sellSelection = getComboBoxes().get("sellSelection").clearItems();
	    for(String s : sellList)
	    	sellSelection.addItem(s);
	
	    ArrayList<String> stashList = getSnacksFormatted(game.getPlayer().getCandyStash());
	    ComboBox<String> stash = getComboBoxes().get("stash").clearItems();
	    for(String s : stashList)
	    	stash.addItem(s);  
	}

	private String calculateBalanceSheet(Game game) {
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

	private ArrayList<String> getDefaultCandiesFormatted() {
	    ArrayList<? extends Snackable> candies = new CandyFactory().getDefaultSnacks();
	    candies.sort(Comparator.comparing(Snackable::getName)); //String implements Comparable
    	ArrayList<String> formattedList = new ArrayList<>();
	    for(Snackable s : candies)
	    	formattedList.add(String.format("%s - %.2f %s", s.getName(), s.getStaticPrice(), MoneyDealer.getCurrency()));
	    return formattedList;
	}
    
    private String getMoneyFormatted(double money) {
	   return String.format("%,.2f %s", money, MoneyDealer.getCurrency());
    }
    
    private ArrayList<String> getSnacksFormatted(ArrayList<? extends Snackable> snacks){
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