package com.github.SweetTooth.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import com.github.SweetTooth.characters.MoneyDealer;
import com.github.SweetTooth.events.Event;
import com.github.SweetTooth.events.EventFactory;
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
import com.googlecode.lanterna.gui2.InputFilter;
import com.googlecode.lanterna.gui2.Interactable;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LayoutData;
import com.googlecode.lanterna.gui2.LayoutManager;
import com.googlecode.lanterna.gui2.Separator;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

public class PanelContentMain extends PanelContent<String> {
	GUIManager guiManager;
	
	public PanelContentMain(GUIManager guiManager, LayoutManager layoutManager, Game game) {
        super(layoutManager, game);
        this.guiManager = guiManager;
    }
	
	@Override
	public void addContent() {
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
        addComponent(labels.get("travelEventInfo2").setLayoutData(HEA_2Span));
        addComponent(labels.get("travelEventInfo1"), HEA_2Span);
        addComponent(labels.get("travelEventInfo3"), HF_2Span);
        addComponent(labels.get("travelInterestInfo"), HF_2Span);
        
        addComponent(new Separator(Direction.HORIZONTAL), HF_2Span);
        addComponent(textBoxes.get("balanceSheet"), GridLayout.createLayoutData(GridLayout.Alignment.FILL, GridLayout.Alignment.FILL, true, true, 2, 1));
        addComponent(buttons.get("exit").setLayoutData(HEA_2Span));
	}

	@Override
	public void addInputHandling() {
		EventFactory eventFactory = EventFactory.getDefaultFactory();
		
		ComboBox.Listener buySelectionListener = (selectedIndex, previousSelection, changedByUserInteraction) -> {
					if(changedByUserInteraction) {
						comboBoxes.get("buySelection").setEnabled(false);
						textBoxesIT.get("buyQuantity").setEnabled(true).takeFocus();
					}};
		ComboBox.Listener sellSelectionListener = (selectedIndex, previousSelection, changedByUserInteraction) -> {
					if(changedByUserInteraction) {
						comboBoxes.get("sellSelection").setEnabled(false);
						textBoxesIT.get("sellQuantity").setEnabled(true).takeFocus();
					}};
		ComboBox.Listener locationSelectionListener = (selectedIndex, previousSelection, changedByUserInteraction) -> {
					if(changedByUserInteraction) {
						if(selectedIndex == previousSelection)
							labels.get("travelEventInfo1").setText("Du bist doch schon da!");
						else
							try {
								updateContent();
								Location location = Location.valueOfficialName(comboBoxes.get("locationSelection").getItem(selectedIndex));
				    			String input = location.toString();
				            	Event.Answer answer = eventFactory.create("Travel", game).handleMultipleAnswers(input, null, null);
				            	String applyInterestAnswer = eventFactory.create("ApplyInterest", game).handle(null, null, null);
				    			labels.get("travelEventInfo1").setText(answer.answer()[0]);
				    			labels.get("travelEventInfo2").setText(answer.answer()[1]);
				    			labels.get("travelEventInfo3").setText(answer.answer()[2]);
				            	labels.get("travelInterestInfo").setText(applyInterestAnswer);
				        	    game.increaseDayOfGame(1, PanelContentMain.this);
							}
							catch(IllegalArgumentException e) { e.printStackTrace(); }
							catch (IOException e) {	e.printStackTrace(); }
					}};
		Button.Listener hideListener = button -> {
						String answer = eventFactory.create("Hide", game).handle(null, null, null);
						updateContent();
						labels.get("hideSeekInfo").setText(answer);
					};
		Button.Listener seekListener = button -> {
						String snackInput = comboBoxes.get("stash").getSelectedItem();
		    			Integer snackQuantity = Integer.parseInt(textBoxes.get("seekQuantity").getText());
		    			String answer = eventFactory.create("Seek", game).handle(snackInput, snackQuantity, null);
		    			updateContent();
		    			labels.get("hideSeekInfo").setText(answer);
		    			comboBoxes.get("stash").takeFocus();
		    			textBoxes.get("seekQuantity").setEnabled(true);
		    			button.setEnabled(false);
					};
		Button.Listener exitListener = button -> {
						try { guiManager.stop(); }
						catch (IOException e) { e.printStackTrace(); }
					};
		comboBoxes.get("buySelection").addListener(buySelectionListener);
		comboBoxes.get("sellSelection").addListener(sellSelectionListener);
		comboBoxes.get("locationSelection").addListener(locationSelectionListener);
		buttons.get("hide").addListener(hideListener);
		buttons.get("seek").addListener(seekListener);
		buttons.get("exit").addListener(exitListener);
    
		InputFilter seekQuantityInputFilter = (Interactable interactable, KeyStroke keyStroke) -> {
						TextBox seekQuantity = (TextBox) interactable;
						if(keyStroke.getKeyType() == KeyType.Enter) {
							if(seekQuantity.getText().isBlank()) { 
								seekQuantity.removeLine(0);
				    			return false;
							}
							try {
								Integer input = Integer.parseInt(seekQuantity.getText().strip());
								if(input > 100) throw new NumberFormatException();
								buttons.get("seek").setEnabled(true).takeFocus();
								seekQuantity.setEnabled(false);
								return false;
							}
							catch(NumberFormatException e) {
								seekQuantity.removeLine(0);
								labels.get("hideSeekInfo").setText("Du musst eine Zahl eingeben! (<= 100)");
			        			return false;
							}
						}
						return true;
					};
		textBoxes.get("seekQuantity").setInputFilter(seekQuantityInputFilter);			
		textBoxesIT.get("buyQuantity").setInputFilter(new InputFilterDeal(this, eventFactory.create("Buy", game)));
		textBoxesIT.get("sellQuantity").setInputFilter(new InputFilterDeal(this, eventFactory.create("Sell", game)));
		textBoxesIT.get("deposit").setInputFilter(new InputFilterFinances(this, eventFactory.create("Deposit", game)));
		textBoxesIT.get("withdraw").setInputFilter(new InputFilterFinances(this, eventFactory.create("Withdraw", game)));
		textBoxesIT.get("lend").setInputFilter(new InputFilterFinances(this, eventFactory.create("Lend", game)));
		textBoxesIT.get("giveBack").setInputFilter(new InputFilterFinances(this, eventFactory.create("GiveMoneyBack", game)));
    }
	
	@Override
	public void createContent() {
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
		labels.put("travelEventInfo1", new Label(""));
		labels.put("travelEventInfo2", new Label(""));
		labels.put("travelEventInfo3", new Label(""));
		labels.put("travelInterestInfo", new Label(""));
		
		comboBoxes.put("sweetsInPockets", new ComboBox<>(""));
		comboBoxes.put("buySelection", new ComboBox<>(""));
		comboBoxes.put("sellSelection", new ComboBox<>(""));
		comboBoxes.put("stash", new ComboBox<>(""));
		comboBoxes.put("locationSelection", new ComboBox<>(""));
	
		textBoxes.put("seekQuantity", new TextBox(""));
		textBoxes.put("balanceSheet", new TextBox("", TextBox.Style.MULTI_LINE));
		textBoxesIT.put("buyQuantity", new TextBoxInitialText(""));
		textBoxesIT.put("sellQuantity", new TextBoxInitialText(""));
		textBoxesIT.put("deposit", new TextBoxInitialText("Natürlich, welchen Betrag?"));
		textBoxesIT.put("withdraw", new TextBoxInitialText("Gerne, wie viel?"));
		textBoxesIT.put("lend", new TextBoxInitialText("Wie viel willst du?!"));
		textBoxesIT.put("giveBack", new TextBoxInitialText("Lass sehn..."));
	
		buttons.put("hide", new Button(""));
		buttons.put("seek", new Button(""));
		buttons.put("exit", new Button(""));
	}
	
	@Override
	public void disableComponents() {
		updateContent();
		
		labels.get("titel3").setVisible(true);
		
		textBoxes.get("balanceSheet").setTheme(new SimpleTheme(new RGB(0, 0, 0), new RGB(255, 240, 140), SGR.BOLD));
		textBoxesIT.get("seekQuantity").setEnabled(false);
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
	    labels.get("bankInterestHint").addStyle(SGR.ITALIC).setText(game.getBank().getInterestHint());
	    labels.get("bankDispoHint").addStyle(SGR.ITALIC).setText(game.getBank().getDispoHint());
	    
	    labels.get("loansharkTitel").addStyle(SGR.BOLD).setText("KREDITHAI:");
	    labels.get("loansharkBalanceLabel").addStyle(SGR.BOLD).setText("Schulden:");
	    labels.get("lendLabel").setText("Ich brauch Geld.");
	    labels.get("giveBackLabel").setText("Hier, ich hab dein Geld dabei.");
	    labels.get("loansharkInterestHint").addStyle(SGR.ITALIC).setText(game.getLoanShark().getInterestHint());
	    
	    labels.get("travelTitel1").addStyle(SGR.BOLD).setText("Du willst dich mal umschauen?");
	    labels.get("travelTitel2").addStyle(SGR.BOLD).setText("Klar, aber du wirst den ganzen Tag unterwegs sein.");
	    labels.get("ticketLabel").setText("Eine Fahrt mit deinem EasyTicket kostet pauschal:");
	    labels.get("locationLabel").setText("Wohin gehts?");
	    labels.get("travelEventInfo1").addStyle(SGR.BOLD).addStyle(SGR.REVERSE);
	    labels.get("travelEventInfo3").addStyle(SGR.ITALIC);
	    labels.get("travelInterestInfo").addStyle(SGR.ITALIC);
	    
	    textBoxes.get("balanceSheet").setEnabled(false).setTheme(new SimpleTheme(new RGB(50, 50, 0), new RGB(250, 220, 100), SGR.BOLD));
	    textBoxesIT.get("buyQuantity").setEnabled(false);
	    textBoxesIT.get("sellQuantity").setEnabled(false);
	    
		comboBoxes.get("sweetsInPockets").setReadOnly(true);
	    comboBoxes.get("buySelection").setReadOnly(true);
	    comboBoxes.get("sellSelection").setReadOnly(true);
	    comboBoxes.get("stash").setReadOnly(true);
	    ArrayList<String> locationList = new ArrayList<>();
	    for(Location l : Location.values())
	    	locationList.add(l.getOfficialName());
	    ComboBox<String> locationSelection = comboBoxes.get("locationSelection").setReadOnly(true).clearItems();
	    for(String s : locationList)
	    	locationSelection.addItem(s);
	    
	    buttons.get("hide").setLabel("Alles Verstecken");
	    buttons.get("seek").setEnabled(false).setLabel("Aus dem Versteck holen");
	    buttons.get("exit").setLabel("Ich hau ab, kein Bock mehr...");
	}

	@Override
	public void updateContent() {
	    labels.get("currentDay").setText(Integer.toString(game.getDayOfGame()));
	    labels.get("currentLocation").setText(game.getPlayer().getLocation().getOfficialName());
	    labels.get("cash").setText(getMoneyFormatted(game.getPlayer().getCash()));
	    labels.get("buySellInfo").setText("");
	    labels.get("hideSeekInfo").setText("");
	    labels.get("bankBalance").setText(getMoneyFormatted(game.getBank().getBalance(game.getPlayer())));
	    labels.get("bankInfo").setText("");
	    labels.get("loansharkBalance").setText(getMoneyFormatted(game.getLoanShark().getBalance(game.getPlayer())));
	    labels.get("loansharkInfo").setText("");
	    labels.get("ticketPrice").setText(getMoneyFormatted(Game.getTravelCosts()));
	    labels.get("travelEventInfo1").setText("");
	    labels.get("travelEventInfo2").setText("");
	    labels.get("travelEventInfo3").setText("");
	    labels.get("travelInterestInfo").setText("");
	    
	    textBoxes.get("seekQuantity").setText("");
	    textBoxes.get("balanceSheet").setText(calculateBalanceSheet(game));
	    
	    ArrayList<String> sweetsList = getSnacksFormatted(game.getPlayer().getCandies());
	    ComboBox<String> sweetsInPockets = comboBoxes.get("sweetsInPockets").clearItems();
	    for(String s : sweetsList)
	    	sweetsInPockets.addItem(s);
	    
	    ArrayList<String> buyList = getDefaultCandiesFormatted();
	    ComboBox<String> buySelection = comboBoxes.get("buySelection").clearItems();
	    for(String s : buyList)
	    	buySelection.addItem(s);
	
	    
	    ArrayList<String> sellList = getDefaultCandiesFormatted();
	    ComboBox<String> sellSelection = comboBoxes.get("sellSelection").clearItems();
	    for(String s : sellList)
	    	sellSelection.addItem(s);
	
	    ArrayList<String> stashList = getSnacksFormatted(game.getPlayer().getCandyStash());
	    ComboBox<String> stash = comboBoxes.get("stash").clearItems();
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