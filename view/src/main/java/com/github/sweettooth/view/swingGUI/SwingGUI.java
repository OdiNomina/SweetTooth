package com.github.sweettooth.view.swingGUI;

import com.github.sweettooth.controllerSwing.api.IController;
import com.github.sweettooth.model.api.GameSettings;
import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.ILocation;
import com.github.sweettooth.model.api.viewAPI.IMoneyDealer;
import com.github.sweettooth.model.api.viewAPI.IPlayer;
import com.github.sweettooth.model.api.viewAPI.Observer;
import com.github.sweettooth.shared.api.Loggable;
import com.github.sweettooth.shared.api.UpdateGuard;
import com.github.sweettooth.view.api.DisplayElement;
import com.github.sweettooth.view.commons.Tools;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Logger;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class SwingGUI implements Observer, DisplayElement, Loggable, UpdateGuard {
	private final Logger logger;
	private boolean updating;
	
	private IController controller;
	private IGameData gameData;
	private GameSettings gameSettings;
	
	private IPlayer player;
	private IMoneyDealer loanShark;
	private IMoneyDealer bank;
	
	private JFrame mainFrame;
	// Title Panel
	private JLabel title1Label;
	private JLabel title2Label;
	private JLabel gameOverLabel;
	// Current Panel
	private JLabel currentDayLabel;
	private JLabel currentDay;
	private JLabel currentLocationLabel;
	private JLabel currentLocation;
	private JLabel cashLabel;
	private JLabel cash;
	private JLabel pocketsLabel;
	private JComboBox<String> pockets;
	// Buy Sell Panel
	private JLabel buyTitle;
	private JLabel buySelectionLabel;
	private JComboBox<String> buySelection;
	private JTextField buyQuantity;
	private JLabel sellTitel;
	private JLabel sellSelectionLabel;
	private JComboBox<String> sellSelection;
	private JTextField sellQuantity;
	private JLabel buySellInfo;
	// Bank Panel
	private JLabel bankTitle;
	private JLabel bankBalanceLabel;
	private JLabel bankBalance;
	private JLabel depositLabel;
	private JTextField deposit;
	private JLabel depositAnswer;
	private JLabel withdrawLabel;
	private JTextField withdraw;
	private JLabel withdrawAnswer;
	private JLabel bankInfo;
	private JLabel bankDispoHint;
	private JLabel bankInterestHint1;
	private JLabel bankInterestHint2;
	// Loanshark Panel
	private JLabel loansharkTitle;
	private JLabel loansharkBalanceLabel;
	private JLabel loansharkBalance;
	private JLabel lendLabel;
	private JTextField lend;
	private JLabel lendAnswer;
	private JLabel giveBackLabel;
	private JTextField giveBack;
	private JLabel giveBackAnswer;
	private JLabel loansharkInfo;
	private JLabel loansharkInterestHint;
	// Travel Panel
	private JLabel travelTitle1;
	private JLabel travelTitle2;
	private JLabel ticketLabel;
	private JLabel ticketPrice;
	private JLabel locationSelectionLabel;
	private JComboBox<String> locationSelection;
	private JLabel travelInfo1;
	private JLabel travelInfo2;
	private JLabel travelInfo3;
	private JLabel travelInterest;
	// Balance Panel
	private JTextArea balanceSheet;
	private JButton exitButton;
	// Hide Seek Panel
	private JLabel hideSeekTitle;
	private JButton hideButton;
	private JLabel stashLabel;
	private JComboBox<String> stash;
	private JLabel seekQuantityLabel;
	private JTextField seekQuantity;
	private JButton seekButton;
	private JLabel hideSeekInfo;
	
	public SwingGUI(IGameData gameData) {
		logger = Logger.getLogger(SwingGUI.class.getName());
		this.gameData = Objects.requireNonNull(gameData);
		
		controller = IController.getInstance();
		controller.initialize(gameData);
	}
	
    @Override
	public Logger getLogger() {	
		return logger;
	}
    
    @Override
    public boolean isUpdating() {
        return updating;
    }

	@Override
	public DisplayElement initialize(GameSettings gameSettings) throws NullPointerException {
		this.gameSettings = Objects.requireNonNull(gameSettings);
		
		player = gameData.player();
        loanShark = gameData.loanShark();
        bank = gameData.bank();
		
		gameData.registerObserver(this);
		return this;
	}
	
	@Override
	public void run() {
		Instant start = Instant.now();
		try {
			createMainFrame();
			initializeContent();
			updateContent();
			addInputHandling();
			mainFrame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		info(String.format(Thread.currentThread().getName() + " thread stopped: Runtime %s ms", start.until(Instant.now(), ChronoUnit.MILLIS)));
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

	private void initializeContent() {
    	try {
    		// Title Panel
    		title1Label.setText("Du dealst mit Süßis?");
    		title2Label.setText("Mal sehen was du in einem Monat verdienst...");
    		gameOverLabel.setText(" Das wars... NICHTS GEHT MEHR ! ");
    		gameOverLabel.setVisible(false);
			// Current Panel
    		currentDayLabel.setText("Tag:");
			currentLocationLabel.setText("Wo bin ich eigentlich...?");
			cashLabel.setText("Cash dabei:");
			pocketsLabel.setText("Was hab ich in den Taschen?"); 
			// Buy Sell Panel
			buyTitle.setText("Hast du was für mich?");
			buySelectionLabel.setText("Ich mag..."); 
			buyQuantity.setText("");
			sellTitel.setText("Hey! Willst du was Süßes?");
			sellSelectionLabel.setText("Ich verkaufe dir...");
		    sellQuantity.setText("");
			// Hide Seek Panel
			hideSeekTitle.setText("Du hast ein echt gutes Versteck für deine Süßis, da sind sie sicher!");
			stashLabel.setText("Was liegt schon im Versteck?");
			seekQuantityLabel.setText("hmm... wie viel");
			hideButton.setText("Alles Verstecken");
		    seekButton.setText("Aus dem Versteck holen");
			// Bank Panel
			bankTitle.setText("BANK:");
		    bankBalanceLabel.setText("Kontostand:");
		    depositLabel.setText("Ich möchte Geld einzahlen.");
		    depositAnswer.setText("Natürlich, welchen Betrag?");
		    withdrawLabel.setText("Ich würde gerne Geld abheben.");
		    withdrawAnswer.setText("Gerne, wie viel?");
		    bankDispoHint.setText(bank.getDispoHint());
		    bankInterestHint1.setText(bank.getCreditInterestHint());
		    bankInterestHint2.setText(bank.getDebitInterestHint());
		    // Loanshark Panel
		    loansharkTitle.setText("KREDITHAI:");
		    loansharkBalanceLabel.setText("Schulden:");
		    lendLabel.setText("Ich brauch Geld.");
		    lendAnswer.setText("Wie viel willst du?!");
		    giveBackLabel.setText("Hier, ich hab dein Geld dabei.");
		    giveBackAnswer.setText("Lass sehn...");
		    loansharkInterestHint.setText(loanShark.getDebitInterestHint());
		    // Travel Panel
		    travelTitle1.setText("Du willst dich mal umschauen?");
		    travelTitle2.setText("Klar, aber du wirst den ganzen Tag unterwegs sein.");
		    ticketLabel.setText("Eine Fahrt mit deinem EasyTicket kostet pauschal:");
		    locationSelectionLabel.setText("Wohin gehts?");
		    locationSelection.removeAllItems();
		    ArrayList<String> locations = new ArrayList<>();
		    for(ILocation l : ILocation.values())
		    	locations.add(l.getOfficialName());
		    for(String s : locations)
		    	locationSelection.addItem(s);
		    // Balance Panel
		    exitButton.setText("Ich hau ab, kein Bock mehr...");
    	}
    	catch(RuntimeException e) { error(e.getClass().getName() + " when initializing content.", e); }
	}

	private void updateContent() {
		try {
			updating = true;
			// Current Panel
			currentDay.setText(Integer.toString(gameData.getDayOfGame()));
		    currentLocation.setText(player.location().getOfficialName());
		    cash.setText(Tools.formatMoney(gameSettings, player.cash()));
		    pockets.removeAllItems();
		    ArrayList<String> pocketItems = Tools.formatSnacks(gameSettings, player.snacks());
		    for(String pi : pocketItems)
		    	pockets.addItem(pi);
		    // Buy Sell Panel
		    buySelection.removeAllItems();
		    sellSelection.removeAllItems();
		    ArrayList<String> availableSnacks = Tools.formatDefaultSnacks(gameSettings);
		    for(String as : availableSnacks) { // Will be updated because prices change!
		    	buySelection.addItem(as);
		    	sellSelection.addItem(as);
		    }
		    buySellInfo.setText("");
		    // Hide Seek Panel
		    stash.removeAllItems();
		    ArrayList<String> stashedItems = Tools.formatSnacks(gameSettings, player.stash());
		    for(String si : stashedItems)
		    	stash.addItem(si);
		    seekQuantity.setText("");
		    hideSeekInfo.setText("");
		    // Bank Panel
		    deposit.setText("");
		    withdraw.setText("");
		    bankBalance.setText(Tools.formatMoney(gameSettings, bank.clientsBalance(player)));
		    bankInfo.setText("");
		    // Loanshark Panel
		    lend.setText("");
		    giveBack.setText("");
		    loansharkBalance.setText(Tools.formatMoney(gameSettings, loanShark.clientsBalance(player)));
		    loansharkInfo.setText("");
		    // Travel Panel
		    ticketPrice.setText(Tools.formatMoney(gameSettings, gameSettings.getTravelCosts()));
		    travelInfo1.setText("");
		    travelInfo2.setText("");
		    travelInfo3.setText("");
		    travelInterest.setText("");
		    // Info Panel
		    balanceSheet.setText(Tools.formatBalanceSheet(gameSettings, gameData));
		}
		catch(RuntimeException e) {
			error(e.getClass().getName() + " when updating content.", e);
		}
		finally {
            updating = false;
        }
	}
	
	private void addInputHandling() {
		try {
			buySelection.addActionListener(controller.createComboBoxListener(this, null, "Buy", buyQuantity, buySellInfo));
			sellSelection.addActionListener(controller.createComboBoxListener(this, null, "Sell", sellQuantity, buySellInfo));
			locationSelection.addActionListener(
					controller.createComboBoxListener(this, currentLocation, "Travel", locationSelection, travelInfo1, travelInfo2, travelInfo3, travelInterest));
	
			hideButton.addActionListener(controller.createButtonListener("Hide", null, null, hideButton, hideSeekInfo));
			seekButton.addActionListener(controller.createButtonListener("Seek", stash, seekQuantity, stash, hideSeekInfo));
			exitButton.addActionListener(controller.createButtonListener("Exit", null, null, exitButton, gameOverLabel));
			
			seekQuantity.addActionListener(controller.createTextFieldListener("Seek", stash, seekButton, hideSeekInfo));
			
			buyQuantity.addActionListener(controller.createTextFieldListener("Buy", buySelection, buySelection, buySellInfo));
			sellQuantity.addActionListener(controller.createTextFieldListener("Sell", sellSelection, sellSelection, buySellInfo));
			deposit.addActionListener(controller.createTextFieldListener("Deposit", null, deposit, bankInfo));
			withdraw.addActionListener(controller.createTextFieldListener("Withdraw", null, withdraw, bankInfo));
			lend.addActionListener(controller.createTextFieldListener("Lend", null, lend, loansharkInfo));
			giveBack.addActionListener(controller.createTextFieldListener("GiveMoneyBack", null, giveBack, loansharkInfo));
		}
		catch(RuntimeException e)  { error(e.getClass().getName() + " when adding input handling.", e); }
	}
	
	private void gameOverConfig() {
	    	try {
	    		gameOverLabel.setVisible(true);
	    		
	    		buySelection.setEnabled(false);
				sellSelection.setEnabled(false);
	
				seekQuantity.setEnabled(false);
				hideButton.setEnabled(false);
				seekButton.setEnabled(false);
	
				deposit.setEnabled(false);
			    withdraw.setEnabled(false);
	
			    lend.setEnabled(false);
			    giveBack.setEnabled(false);
	
				locationSelection.setEnabled(false);
	
				balanceSheet.setEnabled(false);
	    	}
	    	catch(RuntimeException e) { error(e.getClass().getName() + " when setting 'game over configuration'.", e); }
	    }

	/**
	 * @wbp.parser.entryPoint
	 */
	private void createMainFrame() {
		String sampleText = "Sample text for formatting purposes.";

		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(new Color(0, 102, 153));
		title1Label = new JLabel(sampleText);
		title1Label.setForeground(new Color(255, 255, 0));
		title1Label.setFont(new Font("Broadway", Font.BOLD, 24));
		title2Label = new JLabel(sampleText);
		title2Label.setForeground(new Color(255, 255, 0));
		title2Label.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		gameOverLabel = new JLabel(sampleText);
		gameOverLabel.setPreferredSize(new Dimension(300, 22));
		gameOverLabel.setMinimumSize(new Dimension(300, 22));
		gameOverLabel.setMaximumSize(new Dimension(300, 22));
		gameOverLabel.setBackground(new Color(240, 240, 240));
		gameOverLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		gameOverLabel.setForeground(new Color(255, 153, 51));
		gameOverLabel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 22));
		GroupLayout gl_titelPanel = new GroupLayout(titlePanel);
		gl_titelPanel.setHorizontalGroup(
			gl_titelPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_titelPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_titelPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_titelPanel.createSequentialGroup()
							.addComponent(title1Label)
							.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
							.addComponent(gameOverLabel, GroupLayout.PREFERRED_SIZE, 474, GroupLayout.PREFERRED_SIZE))
						.addComponent(title2Label))
					.addContainerGap())
		);
		gl_titelPanel.setVerticalGroup(
			gl_titelPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_titelPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_titelPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(title1Label)
						.addComponent(gameOverLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addComponent(title2Label)
					.addContainerGap())
		);
		titlePanel.setLayout(gl_titelPanel);
		
		JPanel currentPanel = new JPanel();
		currentPanel.setBackground(new Color(0, 102, 153));
		currentDayLabel = new JLabel(sampleText);
		currentDayLabel.setForeground(new Color(153, 204, 255));
		currentDayLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		currentDayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		currentDayLabel.setPreferredSize(new Dimension(300, 16));
		currentDayLabel.setMinimumSize(new Dimension(300, 16));
		currentDayLabel.setMaximumSize(new Dimension(300, 16));
		currentLocationLabel = new JLabel(sampleText);
		currentLocationLabel.setForeground(new Color(153, 204, 255));
		currentLocationLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		currentLocationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		currentLocationLabel.setPreferredSize(new Dimension(300, 16));
		currentLocationLabel.setMinimumSize(new Dimension(300, 16));
		currentLocationLabel.setMaximumSize(new Dimension(300, 16));
		cashLabel = new JLabel(sampleText);
		cashLabel.setForeground(new Color(153, 204, 255));
		cashLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		cashLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		cashLabel.setPreferredSize(new Dimension(300, 16));
		cashLabel.setMinimumSize(new Dimension(300, 16));
		cashLabel.setMaximumSize(new Dimension(300, 16));
		cash = new JLabel(sampleText);
		cash.setPreferredSize(new Dimension(178, 16));
		cash.setMinimumSize(new Dimension(178, 16));
		cash.setMaximumSize(new Dimension(178, 16));
		cash.setForeground(new Color(255, 255, 0));
		cash.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		cash.setAlignmentX(Component.CENTER_ALIGNMENT);
		pocketsLabel = new JLabel(sampleText);
		pocketsLabel.setForeground(new Color(153, 204, 255));
		pocketsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		pocketsLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		pocketsLabel.setPreferredSize(new Dimension(300, 16));
		pocketsLabel.setMinimumSize(new Dimension(300, 16));
		pocketsLabel.setMaximumSize(new Dimension(300, 16));
		currentLocation = new JLabel(sampleText);
		currentLocation.setPreferredSize(new Dimension(178, 16));
		currentLocation.setMinimumSize(new Dimension(178, 16));
		currentLocation.setMaximumSize(new Dimension(178, 16));
		currentLocation.setForeground(new Color(255, 255, 0));
		currentLocation.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		currentLocation.setAlignmentX(Component.CENTER_ALIGNMENT);
		currentDay = new JLabel(sampleText);
		currentDay.setPreferredSize(new Dimension(178, 16));
		currentDay.setMinimumSize(new Dimension(178, 16));
		currentDay.setMaximumSize(new Dimension(178, 16));
		currentDay.setForeground(new Color(255, 255, 0));
		currentDay.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		currentDay.setAlignmentX(Component.CENTER_ALIGNMENT);
		pockets = new JComboBox<String>();
		pockets.setPreferredSize(new Dimension(250, 22));
		pockets.setMaximumSize(new Dimension(250, 22));
		pockets.setBackground(new Color(51, 102, 153));
		pockets.setForeground(new Color(255, 255, 0));
		pockets.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		GroupLayout gl_currentPanel = new GroupLayout(currentPanel);
		gl_currentPanel.setHorizontalGroup(
			gl_currentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_currentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_currentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_currentPanel.createParallelGroup(Alignment.TRAILING)
							.addComponent(pocketsLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(cashLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(currentDayLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(currentLocationLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_currentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(cash, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(pockets, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(currentLocation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(currentDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_currentPanel.setVerticalGroup(
			gl_currentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_currentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_currentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(currentDayLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(currentDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_currentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(currentLocation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(currentLocationLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_currentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cash, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cashLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_currentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(pockets, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(pocketsLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		currentPanel.setLayout(gl_currentPanel);
		
		JPanel buySellPanel = new JPanel();
		buySellPanel.setBackground(new Color(102, 153, 204));
		buyTitle = new JLabel(sampleText);
		buyTitle.setForeground(new Color(255, 255, 0));
		buyTitle.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		buySelectionLabel = new JLabel(sampleText);
		buySelectionLabel.setPreferredSize(new Dimension(200, 16));
		buySelectionLabel.setMinimumSize(new Dimension(200, 16));
		buySelectionLabel.setMaximumSize(new Dimension(200, 16));
		buySelectionLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		buySelectionLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		sellTitel = new JLabel(sampleText);
		sellTitel.setForeground(new Color(255, 255, 0));
		sellTitel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		sellSelectionLabel = new JLabel(sampleText);
		sellSelectionLabel.setMinimumSize(new Dimension(200, 16));
		sellSelectionLabel.setMaximumSize(new Dimension(200, 16));
		sellSelectionLabel.setPreferredSize(new Dimension(200, 16));
		sellSelectionLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		sellSelectionLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		buySellInfo = new JLabel(sampleText);
		buySellInfo.setForeground(new Color(153, 255, 51));
		buySellInfo.setPreferredSize(new Dimension(178, 16));
		buySellInfo.setMinimumSize(new Dimension(178, 16));
		buySellInfo.setMaximumSize(new Dimension(178, 16));
		buySellInfo.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		buyQuantity = new JTextField(sampleText);
		buyQuantity.setPreferredSize(new Dimension(100, 20));
		buyQuantity.setMaximumSize(new Dimension(100, 20));
		buyQuantity.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		buyQuantity.setColumns(10);
		buyQuantity.setEnabled(false);
		sellQuantity = new JTextField(sampleText);
		sellQuantity.setPreferredSize(new Dimension(100, 20));
		sellQuantity.setMaximumSize(new Dimension(100, 20));
		sellQuantity.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		sellQuantity.setColumns(10);
		sellQuantity.setEnabled(false);
		buySelection = new JComboBox<String>();
		buySelection.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		buySelection.setPreferredSize(new Dimension(250, 22));
		buySelection.setMaximumSize(new Dimension(250, 22));
		sellSelection = new JComboBox<String>();
		sellSelection.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		sellSelection.setPreferredSize(new Dimension(250, 22));
		sellSelection.setMaximumSize(new Dimension(250, 22));
		GroupLayout gl_buySellPanel = new GroupLayout(buySellPanel);
		gl_buySellPanel.setHorizontalGroup(
			gl_buySellPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buySellPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_buySellPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(buyTitle)
						.addComponent(sellTitel)
						.addGroup(gl_buySellPanel.createSequentialGroup()
							.addComponent(buySelectionLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_buySellPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(buyQuantity, 100, 100, 100)
								.addComponent(buySelection, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_buySellPanel.createSequentialGroup()
							.addComponent(sellSelectionLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_buySellPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_buySellPanel.createSequentialGroup()
									.addComponent(sellQuantity, 100, 100, 100)
									.addGap(200)
									.addComponent(buySellInfo, GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE))
								.addComponent(sellSelection, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_buySellPanel.setVerticalGroup(
			gl_buySellPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_buySellPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(buyTitle)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_buySellPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(buySelectionLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(buySelection, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(buyQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(sellTitel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_buySellPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(sellSelectionLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(sellSelection, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_buySellPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(sellQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(buySellInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		buySellPanel.setLayout(gl_buySellPanel);

		JPanel balancePanel = new JPanel();
		balancePanel.setBackground(new Color(0, 102, 153));
		balanceSheet = new JTextArea(sampleText);
		balanceSheet.setEditable(false);
		balanceSheet.setDisabledTextColor(new Color(255, 153, 0));
		balanceSheet.setTabSize(6);
		balanceSheet.setBackground(new Color(0, 102, 153));
		balanceSheet.setForeground(new Color(153, 204, 255));
		balanceSheet.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		exitButton = new JButton(sampleText);
		exitButton.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		exitButton.setBackground(new Color(153, 204, 255));
		GroupLayout gl_balancePanel = new GroupLayout(balancePanel);
		gl_balancePanel.setHorizontalGroup(
			gl_balancePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_balancePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(balanceSheet, GroupLayout.PREFERRED_SIZE, 710, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
					.addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_balancePanel.setVerticalGroup(
			gl_balancePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_balancePanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_balancePanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(exitButton)
						.addComponent(balanceSheet, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		balancePanel.setLayout(gl_balancePanel);
		
		JPanel bankPanel = new JPanel();
		bankPanel.setBackground(new Color(102, 153, 204));
		bankTitle = new JLabel(sampleText);
		bankTitle.setForeground(Color.YELLOW);
		bankTitle.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		bankBalanceLabel = new JLabel(sampleText);
		bankBalanceLabel.setPreferredSize(new Dimension(300, 16));
		bankBalanceLabel.setMinimumSize(new Dimension(300, 16));
		bankBalanceLabel.setMaximumSize(new Dimension(300, 16));
		bankBalanceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		bankBalanceLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		bankBalanceLabel.setBackground(new Color(255, 255, 153));
		depositLabel = new JLabel(sampleText);
		depositLabel.setPreferredSize(new Dimension(300, 16));
		depositLabel.setMinimumSize(new Dimension(300, 16));
		depositLabel.setMaximumSize(new Dimension(300, 16));
		depositLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		depositLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		depositLabel.setBackground(new Color(255, 255, 153));
		withdrawLabel = new JLabel(sampleText);
		withdrawLabel.setPreferredSize(new Dimension(300, 16));
		withdrawLabel.setMinimumSize(new Dimension(300, 16));
		withdrawLabel.setMaximumSize(new Dimension(300, 16));
		withdrawLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		withdrawLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		withdrawLabel.setBackground(new Color(255, 255, 153));
		bankBalance = new JLabel(sampleText);
		bankBalance.setPreferredSize(new Dimension(100, 16));
		bankBalance.setMinimumSize(new Dimension(100, 16));
		bankBalance.setMaximumSize(new Dimension(100, 16));
		bankBalance.setForeground(Color.BLACK);
		bankBalance.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		bankBalance.setAlignmentX(0.5f);
		deposit = new JTextField(sampleText);
		deposit.setPreferredSize(new Dimension(100, 20));
		deposit.setMinimumSize(new Dimension(100, 20));
		deposit.setMaximumSize(new Dimension(100, 20));
		deposit.setForeground(Color.BLACK);
		deposit.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		deposit.setAlignmentX(0.5f);
		depositAnswer = new JLabel(sampleText);
		depositAnswer.setPreferredSize(new Dimension(200, 16));
		depositAnswer.setMinimumSize(new Dimension(200, 16));
		depositAnswer.setMaximumSize(new Dimension(200, 16));
		depositAnswer.setForeground(Color.BLACK);
		depositAnswer.setFont(new Font("Trebuchet MS", Font.ITALIC, 13));
		depositAnswer.setAlignmentX(0.5f);
		withdraw = new JTextField(sampleText);
		withdraw.setPreferredSize(new Dimension(100, 20));
		withdraw.setMinimumSize(new Dimension(100, 20));
		withdraw.setMaximumSize(new Dimension(100, 20));
		withdraw.setForeground(Color.BLACK);
		withdraw.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		withdraw.setAlignmentX(0.5f);
		withdrawAnswer = new JLabel(sampleText);
		withdrawAnswer.setPreferredSize(new Dimension(200, 16));
		withdrawAnswer.setMinimumSize(new Dimension(200, 16));
		withdrawAnswer.setMaximumSize(new Dimension(200, 16));
		withdrawAnswer.setForeground(Color.BLACK);
		withdrawAnswer.setFont(new Font("Trebuchet MS", Font.ITALIC, 13));
		withdrawAnswer.setAlignmentX(0.5f);
		bankInfo = new JLabel(sampleText);
		bankInfo.setForeground(new Color(153, 255, 51));
		bankInfo.setPreferredSize(new Dimension(250, 16));
		bankInfo.setMinimumSize(new Dimension(250, 16));
		bankInfo.setMaximumSize(new Dimension(250, 16));
		bankInfo.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		bankDispoHint = new JLabel(sampleText);
		bankDispoHint.setPreferredSize(new Dimension(178, 16));
		bankDispoHint.setMinimumSize(new Dimension(178, 16));
		bankDispoHint.setMaximumSize(new Dimension(178, 16));
		bankDispoHint.setFont(new Font("Trebuchet MS", Font.ITALIC, 13));
		bankInterestHint1 = new JLabel(sampleText);
		bankInterestHint1.setPreferredSize(new Dimension(178, 16));
		bankInterestHint1.setMinimumSize(new Dimension(178, 16));
		bankInterestHint1.setMaximumSize(new Dimension(178, 16));
		bankInterestHint1.setFont(new Font("Trebuchet MS", Font.ITALIC, 13));
		bankInterestHint2 = new JLabel(sampleText);
		bankInterestHint2.setPreferredSize(new Dimension(178, 16));
		bankInterestHint2.setMinimumSize(new Dimension(178, 16));
		bankInterestHint2.setMaximumSize(new Dimension(178, 16));
		bankInterestHint2.setFont(new Font("Trebuchet MS", Font.ITALIC, 13));
		
		GroupLayout gl_bankPanel = new GroupLayout(bankPanel);
		gl_bankPanel.setHorizontalGroup(
			gl_bankPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_bankPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_bankPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_bankPanel.createSequentialGroup()
							.addGroup(gl_bankPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(bankBalanceLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(depositLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(withdrawLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_bankPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_bankPanel.createSequentialGroup()
									.addGroup(gl_bankPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(deposit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(withdraw, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_bankPanel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(bankInfo, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
										.addComponent(withdrawAnswer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(depositAnswer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addComponent(bankBalance, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(bankTitle)
						.addGroup(gl_bankPanel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(bankDispoHint, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 751, GroupLayout.PREFERRED_SIZE)
							.addComponent(bankInterestHint1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 751, GroupLayout.PREFERRED_SIZE)
							.addComponent(bankInterestHint2, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 751, GroupLayout.PREFERRED_SIZE)))
					.addGap(666))
		);
		gl_bankPanel.setVerticalGroup(
			gl_bankPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_bankPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(bankTitle)
					.addGap(11)
					.addGroup(gl_bankPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(bankBalanceLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(bankBalance, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_bankPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(depositLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(deposit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(depositAnswer, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_bankPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(withdrawLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(withdraw, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(withdrawAnswer, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addGap(23)
					.addComponent(bankInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bankDispoHint, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bankInterestHint1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bankInterestHint2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		bankPanel.setLayout(gl_bankPanel);
		
		JPanel loansharkPanel = new JPanel();
		loansharkPanel.setBackground(new Color(102, 153, 204));
		loansharkTitle = new JLabel(sampleText);
		loansharkTitle.setForeground(Color.YELLOW);
		loansharkTitle.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		loansharkBalanceLabel = new JLabel(sampleText);
		loansharkBalanceLabel.setPreferredSize(new Dimension(300, 16));
		loansharkBalanceLabel.setMinimumSize(new Dimension(300, 16));
		loansharkBalanceLabel.setMaximumSize(new Dimension(300, 16));
		loansharkBalanceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		loansharkBalanceLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		loansharkBalanceLabel.setBackground(new Color(255, 255, 153));
		lendLabel = new JLabel(sampleText);
		lendLabel.setPreferredSize(new Dimension(300, 16));
		lendLabel.setMinimumSize(new Dimension(300, 16));
		lendLabel.setMaximumSize(new Dimension(300, 16));
		lendLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lendLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lendLabel.setBackground(new Color(255, 255, 153));
		giveBackLabel = new JLabel(sampleText);
		giveBackLabel.setPreferredSize(new Dimension(300, 16));
		giveBackLabel.setMinimumSize(new Dimension(300, 16));
		giveBackLabel.setMaximumSize(new Dimension(300, 16));
		giveBackLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		giveBackLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		giveBackLabel.setBackground(new Color(255, 255, 153));
		loansharkBalance = new JLabel(sampleText);
		loansharkBalance.setPreferredSize(new Dimension(100, 16));
		loansharkBalance.setMinimumSize(new Dimension(100, 16));
		loansharkBalance.setMaximumSize(new Dimension(100, 16));
		loansharkBalance.setForeground(Color.BLACK);
		loansharkBalance.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		loansharkBalance.setAlignmentX(0.5f);
		lend = new JTextField(sampleText);
		lend.setPreferredSize(new Dimension(100, 20));
		lend.setMinimumSize(new Dimension(100, 20));
		lend.setMaximumSize(new Dimension(100, 20));
		lend.setForeground(Color.BLACK);
		lend.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		lend.setAlignmentX(0.5f);
		giveBack = new JTextField(sampleText);
		giveBack.setMinimumSize(new Dimension(100, 20));
		giveBack.setPreferredSize(new Dimension(100, 20));
		giveBack.setMaximumSize(new Dimension(100, 20));
		giveBack.setForeground(Color.BLACK);
		giveBack.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		giveBack.setAlignmentX(0.5f);
		loansharkInfo = new JLabel(sampleText);
		loansharkInfo.setForeground(new Color(153, 255, 51));
		loansharkInfo.setPreferredSize(new Dimension(250, 16));
		loansharkInfo.setMinimumSize(new Dimension(250, 16));
		loansharkInfo.setMaximumSize(new Dimension(250, 16));
		loansharkInfo.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		loansharkInterestHint = new JLabel(sampleText);
		loansharkInterestHint.setPreferredSize(new Dimension(178, 16));
		loansharkInterestHint.setMinimumSize(new Dimension(178, 16));
		loansharkInterestHint.setMaximumSize(new Dimension(178, 16));
		loansharkInterestHint.setFont(new Font("Trebuchet MS", Font.ITALIC, 13));
		
		lendAnswer = new JLabel("Sample text for formatting purposes.");
		lendAnswer.setPreferredSize(new Dimension(200, 16));
		lendAnswer.setMinimumSize(new Dimension(200, 16));
		lendAnswer.setMaximumSize(new Dimension(200, 16));
		lendAnswer.setForeground(Color.BLACK);
		lendAnswer.setFont(new Font("Trebuchet MS", Font.ITALIC, 13));
		lendAnswer.setAlignmentX(0.5f);
		
		giveBackAnswer = new JLabel("Sample text for formatting purposes.");
		giveBackAnswer.setPreferredSize(new Dimension(200, 16));
		giveBackAnswer.setMinimumSize(new Dimension(200, 16));
		giveBackAnswer.setMaximumSize(new Dimension(200, 16));
		giveBackAnswer.setForeground(Color.BLACK);
		giveBackAnswer.setFont(new Font("Trebuchet MS", Font.ITALIC, 13));
		giveBackAnswer.setAlignmentX(0.5f);
		GroupLayout gl_loansharkPanel = new GroupLayout(loansharkPanel);
		gl_loansharkPanel.setHorizontalGroup(
			gl_loansharkPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_loansharkPanel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_loansharkPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_loansharkPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(loansharkInterestHint, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_loansharkPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_loansharkPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(loansharkTitle)
								.addGroup(gl_loansharkPanel.createSequentialGroup()
									.addGroup(gl_loansharkPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(loansharkBalanceLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lendLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(giveBackLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_loansharkPanel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(giveBack, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lend, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(loansharkBalance, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_loansharkPanel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(giveBackAnswer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lendAnswer, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
										.addComponent(loansharkInfo, GroupLayout.PREFERRED_SIZE, 345, GroupLayout.PREFERRED_SIZE))))))
					.addContainerGap(266, Short.MAX_VALUE))
		);
		gl_loansharkPanel.setVerticalGroup(
			gl_loansharkPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_loansharkPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(loansharkTitle)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_loansharkPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(loansharkBalanceLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(loansharkBalance, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_loansharkPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lendLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lend, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lendAnswer, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_loansharkPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(giveBackLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(giveBack, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(giveBackAnswer, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(loansharkInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(loansharkInterestHint, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12))
		);
		loansharkPanel.setLayout(gl_loansharkPanel);
		
		JPanel hideSeekPanel = new JPanel();
		hideSeekPanel.setBackground(new Color(102, 153, 204));
		hideSeekTitle = new JLabel(sampleText);
		hideSeekTitle.setForeground(Color.YELLOW);
		hideSeekTitle.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		stashLabel = new JLabel(sampleText);
		stashLabel.setPreferredSize(new Dimension(200, 14));
		stashLabel.setMinimumSize(new Dimension(200, 14));
		stashLabel.setMaximumSize(new Dimension(200, 14));
		stashLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		stashLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		stashLabel.setBackground(new Color(255, 255, 153));
		seekQuantityLabel = new JLabel(sampleText);
		seekQuantityLabel.setPreferredSize(new Dimension(200, 14));
		seekQuantityLabel.setMinimumSize(new Dimension(200, 14));
		seekQuantityLabel.setMaximumSize(new Dimension(200, 14));
		seekQuantityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		seekQuantityLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		hideButton = new JButton(sampleText);
		hideButton.setBorder(UIManager.getBorder("Button.border"));
		hideButton.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		hideButton.setBackground(new Color(153, 204, 255));
		stash = new JComboBox<String>();
		stash.setPreferredSize(new Dimension(250, 22));
		stash.setMaximumSize(new Dimension(250, 22));
		stash.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		seekQuantity = new JTextField(sampleText);
		seekQuantity.setPreferredSize(new Dimension(100, 20));
		seekQuantity.setMaximumSize(new Dimension(100, 20));
		seekQuantity.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		seekQuantity.setColumns(10);
		seekButton = new JButton(sampleText);
		seekButton.setBorder(UIManager.getBorder("Button.border"));
		seekButton.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		seekButton.setEnabled(false);
		seekButton.setBackground(new Color(153, 204, 255));
		seekButton.setAlignmentX(0.5f);
		hideSeekInfo = new JLabel(sampleText);
		hideSeekInfo.setForeground(new Color(153, 255, 51));
		hideSeekInfo.setPreferredSize(new Dimension(178, 16));
		hideSeekInfo.setMinimumSize(new Dimension(178, 16));
		hideSeekInfo.setMaximumSize(new Dimension(178, 16));
		hideSeekInfo.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		GroupLayout gl_hideSeekPanel = new GroupLayout(hideSeekPanel);
		gl_hideSeekPanel.setHorizontalGroup(
			gl_hideSeekPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_hideSeekPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_hideSeekPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(hideSeekTitle, GroupLayout.PREFERRED_SIZE, 589, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_hideSeekPanel.createSequentialGroup()
							.addGroup(gl_hideSeekPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(stashLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(seekQuantityLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_hideSeekPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(hideButton)
								.addComponent(stash, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(seekQuantity, 100, 100, 100)
								.addComponent(seekButton)
								.addComponent(hideSeekInfo, GroupLayout.PREFERRED_SIZE, 539, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(282, Short.MAX_VALUE))
		);
		gl_hideSeekPanel.setVerticalGroup(
			gl_hideSeekPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_hideSeekPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(hideSeekTitle)
					.addGap(26)
					.addComponent(hideButton)
					.addGap(20)
					.addGroup(gl_hideSeekPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(stashLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(stash, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addGroup(gl_hideSeekPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(seekQuantityLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(seekQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(seekButton)
					.addGap(18)
					.addComponent(hideSeekInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(25))
		);
		hideSeekPanel.setLayout(gl_hideSeekPanel);
		
		JPanel travelPanel = new JPanel();
		travelPanel.setBackground(new Color(102, 153, 204));
		travelTitle1 = new JLabel(sampleText);
		travelTitle1.setForeground(Color.YELLOW);
		travelTitle1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		travelTitle2 = new JLabel(sampleText);
		travelTitle2.setForeground(Color.YELLOW);
		travelTitle2.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		travelTitle2.setAlignmentX(0.5f);
		locationSelectionLabel = new JLabel(sampleText);
		locationSelectionLabel.setPreferredSize(new Dimension(400, 16));
		locationSelectionLabel.setMinimumSize(new Dimension(400, 16));
		locationSelectionLabel.setMaximumSize(new Dimension(400, 16));
		locationSelectionLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		locationSelectionLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		ticketLabel = new JLabel(sampleText);
		ticketLabel.setPreferredSize(new Dimension(400, 16));
		ticketLabel.setMinimumSize(new Dimension(400, 16));
		ticketLabel.setMaximumSize(new Dimension(400, 16));
		ticketLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		ticketLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		locationSelection = new JComboBox<String>();
		locationSelection.setPreferredSize(new Dimension(250, 22));
		locationSelection.setMaximumSize(new Dimension(250, 22));
		locationSelection.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		ticketPrice = new JLabel(sampleText);
		ticketPrice.setPreferredSize(new Dimension(178, 16));
		ticketPrice.setMinimumSize(new Dimension(178, 16));
		ticketPrice.setMaximumSize(new Dimension(178, 16));
		ticketPrice.setForeground(new Color(0, 0, 0));
		ticketPrice.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		ticketPrice.setAlignmentX(0.5f);
		travelInfo2 = new JLabel(sampleText);
		travelInfo2.setPreferredSize(new Dimension(178, 16));
		travelInfo2.setMinimumSize(new Dimension(178, 16));
		travelInfo2.setMaximumSize(new Dimension(178, 16));
		travelInfo2.setFont(new Font("Trebuchet MS", Font.ITALIC, 13));
		travelInfo3 = new JLabel(sampleText);
		travelInfo3.setPreferredSize(new Dimension(178, 16));
		travelInfo3.setMinimumSize(new Dimension(178, 16));
		travelInfo3.setMaximumSize(new Dimension(178, 16));
		travelInfo3.setFont(new Font("Trebuchet MS", Font.ITALIC, 13));
		travelInterest = new JLabel(sampleText);
		travelInterest.setPreferredSize(new Dimension(178, 16));
		travelInterest.setMinimumSize(new Dimension(178, 16));
		travelInterest.setMaximumSize(new Dimension(178, 16));
		travelInterest.setFont(new Font("Trebuchet MS", Font.ITALIC, 13));
		travelInfo1 = new JLabel(sampleText);
		travelInfo1.setPreferredSize(new Dimension(178, 16));
		travelInfo1.setMinimumSize(new Dimension(178, 16));
		travelInfo1.setMaximumSize(new Dimension(178, 16));
		travelInfo1.setForeground(new Color(153, 255, 51));
		travelInfo1.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		GroupLayout gl_travelPanel = new GroupLayout(travelPanel);
		gl_travelPanel.setHorizontalGroup(
			gl_travelPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_travelPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_travelPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(travelTitle2, GroupLayout.DEFAULT_SIZE, 1017, Short.MAX_VALUE)
						.addComponent(travelTitle1, GroupLayout.DEFAULT_SIZE, 1017, Short.MAX_VALUE)
						.addGroup(gl_travelPanel.createSequentialGroup()
							.addGroup(gl_travelPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(locationSelectionLabel, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE)
								.addComponent(ticketLabel, GroupLayout.PREFERRED_SIZE, 323, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_travelPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(travelInfo1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
								.addGroup(gl_travelPanel.createSequentialGroup()
									.addGroup(gl_travelPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(locationSelection, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
										.addComponent(ticketPrice, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_travelPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(travelInfo3, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
										.addComponent(travelInfo2, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
										.addComponent(travelInterest, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE))))))
					.addContainerGap())
		);
		gl_travelPanel.setVerticalGroup(
			gl_travelPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_travelPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_travelPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_travelPanel.createSequentialGroup()
							.addComponent(travelTitle1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(travelTitle2, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_travelPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_travelPanel.createParallelGroup(Alignment.BASELINE)
									.addComponent(locationSelectionLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(locationSelection, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
								.addComponent(travelInfo2, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_travelPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_travelPanel.createSequentialGroup()
									.addGap(20)
									.addComponent(travelInterest, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_travelPanel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_travelPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(ticketLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
										.addComponent(ticketPrice, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))))
							.addGap(11))
						.addGroup(gl_travelPanel.createSequentialGroup()
							.addComponent(travelInfo3, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addGap(33)))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(travelInfo1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		travelPanel.setLayout(gl_travelPanel);
		
		JPanel startScrollPaneViewPanel = new JPanel();
		startScrollPaneViewPanel.setBackground(new Color(0, 102, 153));
		GroupLayout gl_startScrollPaneViewPanel = new GroupLayout(startScrollPaneViewPanel);
		gl_startScrollPaneViewPanel.setHorizontalGroup(
			gl_startScrollPaneViewPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_startScrollPaneViewPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_startScrollPaneViewPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(travelPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE)
						.addComponent(titlePanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE)
						.addComponent(currentPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE)
						.addComponent(buySellPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE)
						.addComponent(balancePanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_startScrollPaneViewPanel.setVerticalGroup(
			gl_startScrollPaneViewPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_startScrollPaneViewPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(titlePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(currentPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(buySellPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(travelPanel, GroupLayout.PREFERRED_SIZE,  GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(balancePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		startScrollPaneViewPanel.setLayout(gl_startScrollPaneViewPanel);
		
		JPanel financesScrollPaneViewPanel = new JPanel();
		financesScrollPaneViewPanel.setBackground(new Color(0, 102, 153));
		GroupLayout gl_financesScrollPaneViewPanel = new GroupLayout(financesScrollPaneViewPanel);
		gl_financesScrollPaneViewPanel.setHorizontalGroup(
			gl_financesScrollPaneViewPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_financesScrollPaneViewPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_financesScrollPaneViewPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(loansharkPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE)
						.addComponent(bankPanel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 1020, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_financesScrollPaneViewPanel.setVerticalGroup(
			gl_financesScrollPaneViewPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_financesScrollPaneViewPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(bankPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(loansharkPanel, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		financesScrollPaneViewPanel.setLayout(gl_financesScrollPaneViewPanel);
		
		JPanel stashScrollPaneViewPanel = new JPanel();
		stashScrollPaneViewPanel.setBackground(new Color(0, 102, 153));
		GroupLayout gl_stashScrollPaneViewPanel = new GroupLayout(stashScrollPaneViewPanel);
		gl_stashScrollPaneViewPanel.setHorizontalGroup(
			gl_stashScrollPaneViewPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_stashScrollPaneViewPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(hideSeekPanel, GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_stashScrollPaneViewPanel.setVerticalGroup(
			gl_stashScrollPaneViewPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_stashScrollPaneViewPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(hideSeekPanel, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		stashScrollPaneViewPanel.setLayout(gl_stashScrollPaneViewPanel);
		
		JScrollPane startScrollPane = new JScrollPane();
		startScrollPane.setViewportView(startScrollPaneViewPanel);
		
		JScrollPane financesScrollPane = new JScrollPane();
		financesScrollPane.setViewportView(financesScrollPaneViewPanel);
		
		JScrollPane stashScrollPane = new JScrollPane();
		stashScrollPane.setViewportView(stashScrollPaneViewPanel);
			
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("START", null, startScrollPane, null);
		tabbedPane.addTab("GELD", null, financesScrollPane, null);
		tabbedPane.addTab("IM VERSTECK", null, stashScrollPane, null);
		
		mainFrame = new JFrame();
		mainFrame.setPreferredSize(new Dimension(1000, 700));
		mainFrame.getContentPane().setBackground(new Color(0, 102, 153));
		mainFrame.setTitle("Sweet Tooth");
		mainFrame.setBounds(100, 100, 1100, 815);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GroupLayout gl_contentPane = new GroupLayout(mainFrame.getContentPane());
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		mainFrame.getContentPane().setLayout(gl_contentPane);
	}
}
