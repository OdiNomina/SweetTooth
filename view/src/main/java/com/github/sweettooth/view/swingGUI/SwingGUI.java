package com.github.sweettooth.view.swingGUI;

import com.github.sweettooth.controller.api.ControllerInterface;
import com.github.sweettooth.model.api.GameSettings;
import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.ILocation;
import com.github.sweettooth.model.api.viewAPI.IMoneyDealer;
import com.github.sweettooth.model.api.viewAPI.IPlayer;
import com.github.sweettooth.model.api.viewAPI.Observer;
import com.github.sweettooth.shared.api.Loggable;
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

public class SwingGUI implements Observer, DisplayElement, Loggable  {
	private final Logger logger;
	
	private ControllerInterface controller;
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
	// Hide Seek Panel
	private JLabel hideSeekTitel;
	private JButton hideButton;
	private JLabel stashLabel;
	private JComboBox<String> stash;
	private JLabel seekQuantityLabel;
	private JTextField seekQuantity;
	private JButton seekButton;
	private JLabel hideSeekInfo;
	// Bank Panel
	private JLabel bankTitle;
	private JLabel bankBalanceLabel;
	private JLabel bankBalance;
	private JLabel depositLabel;
	private JTextField deposit;
	private String initTextDeposit = "Natürlich, welchen Betrag?";
	private JLabel withdrawLabel;
	private JTextField withdraw;
	private String initTextWithdraw = "Gerne, wie viel?";
	private JLabel bankInfo;
	private JLabel bankDispoHint;
	private JLabel bankInterestHint;
	// Loanshark Panel
	private JLabel loansharkTitle;
	private JLabel loansharkBalanceLabel;
	private JLabel loansharkBalance;
	private JLabel lendLabel;
	private JTextField lend;
	private String initTextLend = "Wie viel willst du?!";
	private JLabel giveBackLabel;
	private JTextField giveBack;
	private String initTextGiveBack = "Lass sehn...";
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
	// Info Panel
	private JTextField balanceSheet;
	private JButton exitButton;

	public SwingGUI() {
		logger = Logger.getLogger(SwingGUI.class.getName());
	}
	
    private void gameOverConfig() {
    	try {
    		// Title Panel
    		gameOverLabel.setVisible(true);
    		// Buy Sell Panel
    		buySelection.setEnabled(false);
			sellSelection.setEnabled(false);
			// Hide Seek Panel
			seekQuantity.setEnabled(false);
			hideButton.setEnabled(false);
			seekButton.setEnabled(false);
			// Bank Panel
			deposit.setEnabled(false);
		    withdraw.setEnabled(false);
		    // Loanshark Panel
		    lend.setEnabled(false);
		    giveBack.setEnabled(false);
			// Travel Panel
			locationSelection.setEnabled(false);
			// Info Panel
//			balanceSheet.setTheme(new SimpleTheme(new RGB(0, 0, 0), new RGB(255, 240, 140), SGR.BOLD));
    	}
    	catch(RuntimeException e) { error(e.getClass().getName() + " when setting 'game over configuration'.", e); }
    }
	
	@Override
	public Logger getLogger() {	
		return logger;
	}
	
	@Override
	public void update() {
		if(gameData.isGameOver())
    		gameOverConfig();
    	else
    		updateContent();
	}
	
	private void updateContent() {
    	try {
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
		    for(String as : availableSnacks) { // Will be updated because prices change.
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
//		    // Bank Panel
//		    deposit.setText(initTextDeposit);
//		    withdraw.setText(initTextWithdraw);
//		    bankBalance.setText(Tools.formatMoney(gameSettings, bank.clientsBalance(player)));
//		    bankInfo.setText("");
//		    // Loanshark Panel
//		    lend.setText(initTextLend);
//		    giveBack.setText(initTextGiveBack);
//		    loansharkBalance.setText(Tools.formatMoney(gameSettings, loanShark.clientsBalance(player)));
//		    loansharkInfo.setText("");
//		    // Travel Panel
//		    ticketPrice.setText(Tools.formatMoney(gameSettings, gameSettings.getTravelCosts()));
//		    travelInfo1.setText("");
//		    travelInfo2.setText("");
//		    travelInfo3.setText("");
//		    travelInterest.setText("");
//		    // Info Panel
//		    balanceSheet.setText(Tools.formatBalanceSheet(gameSettings, gameData));   
    	}
    	catch(RuntimeException e) { error(e.getClass().getName() + " when updating content.", e); }
	}
	
	@Override
	public void run() {
		Instant start = Instant.now();
		try {
			createMainFrame();
			initializeContent();
			updateContent();
			mainFrame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		info(String.format(Thread.currentThread().getName() + " thread stopped: Runtime %s ms", start.until(Instant.now(), ChronoUnit.MILLIS)));
	}

	@Override
	public DisplayElement initialize(IGameData gameData, ControllerInterface controller, GameSettings gameSettings) throws NullPointerException {
		this.gameData = Objects.requireNonNull(gameData);
		this.controller = Objects.requireNonNull(controller);
		this.gameSettings = Objects.requireNonNull(gameSettings);
		
		player = gameData.player();
        loanShark = gameData.loanShark();
        bank = gameData.bank();
		
		gameData.registerObserver(this);
		return this;
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
			hideSeekTitel.setText("Du hast ein echt gutes Versteck für deine Süßis, da sind sie sicher!");
			stashLabel.setText("Was liegt schon im Versteck?");
			seekQuantityLabel.setText("hmm... wie viel");
			hideButton.setText("Alles Verstecken");
		    seekButton.setText("Aus dem Versteck holen");
//			// Bank Panel
//			bankTitle.setText("BANK:");
//		    bankBalanceLabel.setText("Kontostand:");
//		    depositLabel.setText("Ich möchte Geld einzahlen.");
//		    deposit.setText(initTextDeposit);
//		    withdrawLabel.setText("Ich würde gerne Geld abheben.");
//		    withdraw.setText(initTextWithdraw);
//		    bankInterestHint.setText(bank.getInterestHint());
//		    bankDispoHint.setText(bank.getDispoHint());
//		    // Loanshark Panel
//		    loansharkTitle.setText("KREDITHAI:");
//		    loansharkBalanceLabel.setText("Schulden:");
//		    lendLabel.setText("Ich brauch Geld.");
//		    lend.setText(initTextLend);
//		    giveBackLabel.setText("Hier, ich hab dein Geld dabei.");
//		    giveBack.setText(initTextGiveBack);
//		    loansharkInterestHint.setText(loanShark.getInterestHint());
//		    // Travel Panel
//		    travelTitle1.setText("Du willst dich mal umschauen?");
//		    travelTitle2.setText("Klar, aber du wirst den ganzen Tag unterwegs sein.");
//		    ticketLabel.setText("Eine Fahrt mit deinem EasyTicket kostet pauschal:");
//		    locationSelectionLabel.setText("Wohin gehts?");
//		    locationSelection.removeAllItems();
//		    ArrayList<String> locations = new ArrayList<>();
//		    for(ILocation l : ILocation.values())
//		    	locations.add(l.getOfficialName());
//		    for(String s : locations)
//		    	locationSelection.addItem(s);
//		    // Info Panel
//		    balanceSheet.setEnabled(false);
//		    exitButton.setText("Ich hau ab, kein Bock mehr...");
    	}
    	catch(RuntimeException e) { error(e.getClass().getName() + " when initializing content.", e); }
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	private void createMainFrame() {
		String sampleText = "Sample text for formatting purposes.";
		
		mainFrame = new JFrame();
		mainFrame.setPreferredSize(new Dimension(450, 0));
		mainFrame.getContentPane().setBackground(new Color(0, 102, 153));
		mainFrame.setTitle("Sweet Tooth");
		mainFrame.setBounds(100, 100, 940, 730);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Title Panel
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(new Color(0, 102, 153));
		
		title1Label = new JLabel(sampleText);
		title1Label.setForeground(new Color(255, 255, 0));
		title1Label.setFont(new Font("Broadway", Font.BOLD, 24));
		title2Label = new JLabel(sampleText);
		title2Label.setForeground(new Color(255, 255, 0));
		title2Label.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		gameOverLabel = new JLabel(sampleText);
		gameOverLabel.setBackground(new Color(240, 240, 240));
		gameOverLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		gameOverLabel.setForeground(new Color(255, 153, 51));
		gameOverLabel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 22));
		
		// Current Panel
		JPanel currentPanel = new JPanel();
		currentPanel.setBackground(new Color(0, 102, 153));
		
		currentDayLabel = new JLabel(sampleText);
		currentDayLabel.setForeground(new Color(153, 204, 255));
		currentDayLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		currentDayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		currentDayLabel.setPreferredSize(new Dimension(300, 14));
		currentDayLabel.setMinimumSize(new Dimension(300, 14));
		currentDayLabel.setMaximumSize(new Dimension(300, 14));
		currentLocationLabel = new JLabel(sampleText);
		currentLocationLabel.setForeground(new Color(153, 204, 255));
		currentLocationLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		currentLocationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		currentLocationLabel.setPreferredSize(new Dimension(300, 14));
		currentLocationLabel.setMinimumSize(new Dimension(300, 14));
		currentLocationLabel.setMaximumSize(new Dimension(300, 14));
		cashLabel = new JLabel(sampleText);
		cashLabel.setForeground(new Color(153, 204, 255));
		cashLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		cashLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		cashLabel.setPreferredSize(new Dimension(300, 14));
		cashLabel.setMinimumSize(new Dimension(300, 14));
		cashLabel.setMaximumSize(new Dimension(300, 14));
		cash = new JLabel(sampleText);
		cash.setForeground(new Color(255, 255, 0));
		cash.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		cash.setAlignmentX(Component.CENTER_ALIGNMENT);
		pocketsLabel = new JLabel(sampleText);
		pocketsLabel.setForeground(new Color(153, 204, 255));
		pocketsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		pocketsLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		pocketsLabel.setPreferredSize(new Dimension(300, 14));
		pocketsLabel.setMinimumSize(new Dimension(300, 14));
		pocketsLabel.setMaximumSize(new Dimension(300, 14));
		currentLocation = new JLabel(sampleText);
		currentLocation.setForeground(new Color(255, 255, 0));
		currentLocation.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		currentLocation.setAlignmentX(Component.CENTER_ALIGNMENT);
		currentDay = new JLabel();
		currentDay.setForeground(new Color(255, 255, 0));
		currentDay.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		currentDay.setAlignmentX(Component.CENTER_ALIGNMENT);
		pockets = new JComboBox<String>();
		pockets.setPreferredSize(new Dimension(250, 22));
		pockets.setMaximumSize(new Dimension(250, 22));
		pockets.setBackground(new Color(51, 102, 153));
		pockets.setForeground(new Color(255, 255, 0));
		pockets.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		
		// Buy Sell Panel
		JPanel buySellPanel = new JPanel();
		buySellPanel.setBackground(new Color(102, 153, 204));
		
		buyTitle = new JLabel(sampleText);
		buyTitle.setForeground(new Color(255, 255, 0));
		buyTitle.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		buySelectionLabel = new JLabel(sampleText);
		buySelectionLabel.setPreferredSize(new Dimension(200, 14));
		buySelectionLabel.setMinimumSize(new Dimension(200, 14));
		buySelectionLabel.setMaximumSize(new Dimension(200, 14));
		buySelectionLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		buySelectionLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		sellTitel = new JLabel(sampleText);
		sellTitel.setForeground(new Color(255, 255, 0));
		sellTitel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		sellSelectionLabel = new JLabel(sampleText);
		sellSelectionLabel.setMinimumSize(new Dimension(200, 14));
		sellSelectionLabel.setMaximumSize(new Dimension(200, 14));
		sellSelectionLabel.setPreferredSize(new Dimension(200, 14));
		sellSelectionLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		sellSelectionLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		buySellInfo = new JLabel(sampleText);
		buySellInfo.setFont(new Font("Trebuchet MS", Font.ITALIC, 13));
		buyQuantity = new JTextField();
		buyQuantity.setPreferredSize(new Dimension(100, 20));
		buyQuantity.setMaximumSize(new Dimension(100, 20));
		buyQuantity.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		buyQuantity.setColumns(10);
		buyQuantity.setEnabled(false);
		sellQuantity = new JTextField();
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
		
		// Hide Seek Panel
		JPanel hideSeekPanel = new JPanel();
		hideSeekPanel.setBackground(new Color(102, 153, 204));
		
		hideSeekTitel = new JLabel(sampleText);
		hideSeekTitel.setForeground(new Color(255, 255, 0));
		hideSeekTitel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		stashLabel = new JLabel(sampleText);
		stashLabel.setBackground(new Color(255, 255, 153));
		stashLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		stashLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		stashLabel.setMinimumSize(new Dimension(200, 14));
		stashLabel.setMaximumSize(new Dimension(200, 14));
		stashLabel.setPreferredSize(new Dimension(200, 14));
		seekQuantityLabel = new JLabel(sampleText);
		seekQuantityLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		seekQuantityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		seekQuantityLabel.setMinimumSize(new Dimension(200, 14));
		seekQuantityLabel.setMaximumSize(new Dimension(200, 14));
		seekQuantityLabel.setPreferredSize(new Dimension(200, 14));
		hideSeekInfo = new JLabel(sampleText);
		hideSeekInfo.setFont(new Font("Trebuchet MS", Font.ITALIC, 13));
		seekQuantity = new JTextField();
		seekQuantity.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		seekQuantity.setColumns(10);
		seekQuantity.setPreferredSize(new Dimension(100, 20));
		seekQuantity.setMaximumSize(new Dimension(100, 20));
		stash = new JComboBox<String>();
		stash.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		stash.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		stash.setPreferredSize(new Dimension(250, 22));
		stash.setMaximumSize(new Dimension(250, 22));
		hideButton = new JButton(sampleText);
		hideButton.setBackground(new Color(102, 153, 204));
		hideButton.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		seekButton = new JButton(sampleText);
		seekButton.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		seekButton.setBackground(new Color(102, 153, 204));
		seekButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		seekButton.setEnabled(false);
		
		// Grouping and positioning
		GroupLayout gl_titelPanel = new GroupLayout(titlePanel);
		gl_titelPanel.setHorizontalGroup(
			gl_titelPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_titelPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_titelPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_titelPanel.createSequentialGroup()
							.addComponent(title1Label)
							.addPreferredGap(ComponentPlacement.RELATED, 490, Short.MAX_VALUE)
							.addComponent(gameOverLabel))
						.addComponent(title2Label))
					.addContainerGap())
		);
		gl_titelPanel.setVerticalGroup(
			gl_titelPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_titelPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_titelPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(title1Label)
						.addComponent(gameOverLabel))
					.addComponent(title2Label)
					.addContainerGap())
		);
		titlePanel.setLayout(gl_titelPanel);
		
		GroupLayout gl_currentPanel = new GroupLayout(currentPanel);
		gl_currentPanel.setHorizontalGroup(
			gl_currentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_currentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_currentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(pocketsLabel)
						.addComponent(currentLocationLabel)
						.addComponent(cashLabel)
						.addComponent(currentDayLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_currentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(cash)
						.addComponent(pockets)
						.addComponent(currentLocation)
						.addComponent(currentDay))
					.addContainerGap())
		);
		gl_currentPanel.setVerticalGroup(
			gl_currentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_currentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_currentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(currentDay)
						.addComponent(currentDayLabel))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_currentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(currentLocationLabel)
						.addComponent(currentLocation))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_currentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cash)
						.addComponent(cashLabel))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_currentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(pockets)
						.addComponent(pocketsLabel))
					.addContainerGap())
		);
		currentPanel.setLayout(gl_currentPanel);
		
		GroupLayout gl_buySellPanel = new GroupLayout(buySellPanel);
		gl_buySellPanel.setHorizontalGroup(
			gl_buySellPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buySellPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_buySellPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(buyTitle)
						.addComponent(sellTitel)
						.addGroup(gl_buySellPanel.createSequentialGroup()
							.addComponent(buySelectionLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_buySellPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(buyQuantity)
								.addComponent(buySelection)))
						.addGroup(gl_buySellPanel.createSequentialGroup()
							.addComponent(sellSelectionLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_buySellPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_buySellPanel.createSequentialGroup()
									.addComponent(sellQuantity)
									.addGap(200)
									.addComponent(buySellInfo))
								.addComponent(sellSelection))))
					.addContainerGap())
		);
		gl_buySellPanel.setVerticalGroup(
			gl_buySellPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_buySellPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(buyTitle)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_buySellPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(buySelectionLabel)
						.addComponent(buySelection))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(buyQuantity)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(sellTitel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_buySellPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(sellSelectionLabel)
						.addComponent(sellSelection))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_buySellPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(sellQuantity)
						.addComponent(buySellInfo))
					.addContainerGap())
		);
		buySellPanel.setLayout(gl_buySellPanel);
		
		GroupLayout gl_hideSeekPanel = new GroupLayout(hideSeekPanel);
		gl_hideSeekPanel.setHorizontalGroup(
			gl_hideSeekPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_hideSeekPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_hideSeekPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(hideSeekTitel)
						.addGroup(gl_hideSeekPanel.createSequentialGroup()
							.addGroup(gl_hideSeekPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(stashLabel)
								.addComponent(seekQuantityLabel))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_hideSeekPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(hideButton)
								.addComponent(stash)
								.addComponent(seekQuantity, 100, 100, 100)
								.addComponent(seekButton))))
					.addContainerGap())
				.addGroup(gl_hideSeekPanel.createSequentialGroup()
					.addGap(500)
					.addComponent(hideSeekInfo)
					.addContainerGap())
		);
		gl_hideSeekPanel.setVerticalGroup(
			gl_hideSeekPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_hideSeekPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(hideSeekTitel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(hideButton)
					.addGap(20)
					.addGroup(gl_hideSeekPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(stashLabel)
						.addComponent(stash))
					.addGap(20)
					.addGroup(gl_hideSeekPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(seekQuantityLabel)
						.addComponent(seekQuantity))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(seekButton)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(hideSeekInfo)
					.addContainerGap())
		);
		hideSeekPanel.setLayout(gl_hideSeekPanel);
		
		GroupLayout gl_contentPane = new GroupLayout(mainFrame.getContentPane());
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(titlePanel, GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
						.addComponent(currentPanel, GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
						.addComponent(buySellPanel, GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
						.addComponent(hideSeekPanel, GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(titlePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(currentPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(buySellPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(hideSeekPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		mainFrame.getContentPane().setLayout(gl_contentPane);
	}
}
