package com.github.sweettooth.view.swingGUI;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import com.github.sweettooth.controller.api.ControllerInterface;
import com.github.sweettooth.model.api.GameSettings;
import com.github.sweettooth.model.api.IGameData;
import com.github.sweettooth.model.api.viewAPI.Observer;
import com.github.sweettooth.shared.api.Loggable;
import com.github.sweettooth.view.api.DisplayElement;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.logging.Logger;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.ComponentOrientation;

public class SwingGUI implements Observer, DisplayElement, Loggable  {
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					BingFrame window = new BingFrame();
//					window.frmBing.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	private final Logger logger;
	
	private IGameData gameModel;
	private ControllerInterface controller;
	private GameSettings gameSettings;
	
	private JFrame mainFrame;
	private JTextField buyQuantity;
	private JTextField sellQuantity;
	private JTextField seekQuantity;

	public SwingGUI() {
		logger = Logger.getLogger(SwingGUI.class.getName());
	}
	
	@Override
	public Logger getLogger() {	
		return logger;
	}
	
	@Override
	public void update() {
//		if(gameModel.isGameOver())
//    		mainViewPanel.gameOverConfig();
//    	if(gameModel.isExitButtonClicked())
//    		interruptGuiThread();
//    	else
//    		mainViewPanel.updateContent();
	}
	
	@Override
	public void run() {
		Instant start = Instant.now();
		try {
			createMainFrame();
			mainFrame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		info(String.format(Thread.currentThread().getName() + " thread stopped: Runtime %s ms", start.until(Instant.now(), ChronoUnit.MILLIS)));
	}

	@Override
	public DisplayElement initialize(IGameData gameModel, ControllerInterface controller, GameSettings gameSettings) throws NullPointerException {
		this.gameModel = Objects.requireNonNull(gameModel);
		this.controller = Objects.requireNonNull(controller);
		this.gameSettings = Objects.requireNonNull(gameSettings);
		
		gameModel.registerObserver(this);
		return this;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	private void createMainFrame() {
		mainFrame = new JFrame();
		mainFrame.setPreferredSize(new Dimension(450, 0));
		mainFrame.getContentPane().setBackground(new Color(0, 102, 153));
		mainFrame.setTitle("Sweet Tooth");
		mainFrame.setBounds(100, 100, 942, 752);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Title Panel
		JPanel titelPanel = new JPanel();
		titelPanel.setBackground(new Color(0, 102, 153));
		
		JLabel titel1Label = new JLabel("Du dealst mit Süßis?");
		titel1Label.setForeground(new Color(255, 255, 0));
		titel1Label.setFont(new Font("Broadway", Font.BOLD, 24));
		JLabel titel2Label = new JLabel("Mal sehen was du in einem Monat verdienst...");
		titel2Label.setForeground(new Color(255, 255, 0));
		titel2Label.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		JLabel gameOverLabel = new JLabel("Game Over ");
		gameOverLabel.setBackground(new Color(240, 240, 240));
		gameOverLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		gameOverLabel.setForeground(new Color(255, 153, 51));
		gameOverLabel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 22));
		
		// Current Panel
		JPanel currentPanel = new JPanel();
		currentPanel.setBackground(new Color(0, 102, 153));
		
		JLabel currentDayLabel = new JLabel("Tag:");
		currentDayLabel.setForeground(new Color(153, 204, 255));
		currentDayLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		currentDayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		currentDayLabel.setPreferredSize(new Dimension(300, 14));
		currentDayLabel.setMinimumSize(new Dimension(300, 14));
		currentDayLabel.setMaximumSize(new Dimension(300, 14));
		JLabel currentLocationLabel = new JLabel("Wo bin ich eigentlich...?");
		currentLocationLabel.setForeground(new Color(153, 204, 255));
		currentLocationLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		currentLocationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		currentLocationLabel.setPreferredSize(new Dimension(300, 14));
		currentLocationLabel.setMinimumSize(new Dimension(300, 14));
		currentLocationLabel.setMaximumSize(new Dimension(300, 14));
		JLabel cashLabel = new JLabel("Cash dabei:");
		cashLabel.setForeground(new Color(153, 204, 255));
		cashLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		cashLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		cashLabel.setPreferredSize(new Dimension(300, 14));
		cashLabel.setMinimumSize(new Dimension(300, 14));
		cashLabel.setMaximumSize(new Dimension(300, 14));
		JLabel cash = new JLabel("cash");
		cash.setForeground(new Color(255, 255, 0));
		cash.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		cash.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel pocketsLabel = new JLabel("Was hab ich in den Taschen?");
		pocketsLabel.setForeground(new Color(153, 204, 255));
		pocketsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		pocketsLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		pocketsLabel.setPreferredSize(new Dimension(300, 14));
		pocketsLabel.setMinimumSize(new Dimension(300, 14));
		pocketsLabel.setMaximumSize(new Dimension(300, 14));
		JLabel currentLocation = new JLabel("currentLocation");
		currentLocation.setForeground(new Color(255, 255, 0));
		currentLocation.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		currentLocation.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel currentDay = new JLabel("currentDay");
		currentDay.setForeground(new Color(255, 255, 0));
		currentDay.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		currentDay.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JComboBox<String> sweetsInPockets = new JComboBox<String>();
		sweetsInPockets.setPreferredSize(new Dimension(250, 22));
		sweetsInPockets.setMaximumSize(new Dimension(250, 22));
		sweetsInPockets.setBackground(new Color(51, 102, 153));
		sweetsInPockets.setForeground(new Color(255, 255, 0));
		sweetsInPockets.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		
		// Buy Sell Panel
		JPanel buySellPanel = new JPanel();
		buySellPanel.setBackground(new Color(102, 153, 204));
		
		JLabel buyTitel = new JLabel("Hast du was für mich?");
		buyTitel.setForeground(new Color(255, 255, 0));
		buyTitel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		JLabel buySelectionLabel = new JLabel("Ich mag...");
		buySelectionLabel.setPreferredSize(new Dimension(200, 14));
		buySelectionLabel.setMinimumSize(new Dimension(200, 14));
		buySelectionLabel.setMaximumSize(new Dimension(200, 14));
		buySelectionLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		buySelectionLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		JLabel sellTitel = new JLabel("Hey! Willst du was Süßes?");
		sellTitel.setForeground(new Color(255, 255, 0));
		sellTitel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		JLabel sellSelectionLabel = new JLabel("Ich verkaufe dir...");
		sellSelectionLabel.setMinimumSize(new Dimension(200, 14));
		sellSelectionLabel.setMaximumSize(new Dimension(200, 14));
		sellSelectionLabel.setPreferredSize(new Dimension(200, 14));
		sellSelectionLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		sellSelectionLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		JLabel buySellInfo = new JLabel("buySellInfo");
		buySellInfo.setFont(new Font("Trebuchet MS", Font.ITALIC, 13));
		
		buyQuantity = new JTextField();
		buyQuantity.setPreferredSize(new Dimension(100, 20));
		buyQuantity.setMaximumSize(new Dimension(100, 20));
		buyQuantity.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		buyQuantity.setColumns(10);
		sellQuantity = new JTextField();
		sellQuantity.setPreferredSize(new Dimension(100, 20));
		sellQuantity.setMaximumSize(new Dimension(100, 20));
		sellQuantity.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		sellQuantity.setColumns(10);
		
		JComboBox<String> buySelection = new JComboBox<String>();
		buySelection.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		buySelection.setPreferredSize(new Dimension(250, 22));
		buySelection.setMaximumSize(new Dimension(250, 22));
		JComboBox<String> sellSelection = new JComboBox<String>();
		sellSelection.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		sellSelection.setPreferredSize(new Dimension(250, 22));
		sellSelection.setMaximumSize(new Dimension(250, 22));
		
		// Hide Seek Panel
		JPanel hideSeekPanel = new JPanel();
		hideSeekPanel.setBackground(new Color(102, 153, 204));
		
		JLabel hideSeekTitel = new JLabel("Du hast ein echt gutes Versteck für deine Süßis, da sind sie sicher!");
		hideSeekTitel.setForeground(new Color(255, 255, 0));
		hideSeekTitel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		JLabel stashLabel = new JLabel("Was liegt schon im Versteck?");
		stashLabel.setBackground(new Color(255, 255, 153));
		stashLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		stashLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		stashLabel.setMinimumSize(new Dimension(200, 14));
		stashLabel.setMaximumSize(new Dimension(200, 14));
		stashLabel.setPreferredSize(new Dimension(200, 14));
		JLabel seekQuantityLabel = new JLabel("hm... wie viel nehm ich mit...");
		seekQuantityLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		seekQuantityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		seekQuantityLabel.setMinimumSize(new Dimension(200, 14));
		seekQuantityLabel.setMaximumSize(new Dimension(200, 14));
		seekQuantityLabel.setPreferredSize(new Dimension(200, 14));
		JLabel hideSeekInfo = new JLabel("hideSeekInfo");
		hideSeekInfo.setFont(new Font("Trebuchet MS", Font.ITALIC, 13));
		
		seekQuantity = new JTextField();
		seekQuantity.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		seekQuantity.setColumns(10);
		seekQuantity.setPreferredSize(new Dimension(100, 20));
		seekQuantity.setMaximumSize(new Dimension(100, 20));
		
		JComboBox<String> stash = new JComboBox<String>();
		stash.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		stash.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		stash.setPreferredSize(new Dimension(250, 22));
		stash.setMaximumSize(new Dimension(250, 22));
		
		JButton hideButton = new JButton("Alles verstecken");
		hideButton.setBackground(new Color(102, 153, 204));
		hideButton.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		JButton seekButton = new JButton("Aus dem Versteck holen");
		seekButton.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		seekButton.setBackground(new Color(102, 153, 204));
		seekButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		GroupLayout gl_titelPanel = new GroupLayout(titelPanel);
		gl_titelPanel.setHorizontalGroup(
			gl_titelPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_titelPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_titelPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_titelPanel.createSequentialGroup()
							.addComponent(titel1Label)
							.addPreferredGap(ComponentPlacement.RELATED, 490, Short.MAX_VALUE)
							.addComponent(gameOverLabel))
						.addComponent(titel2Label))
					.addContainerGap())
		);
		gl_titelPanel.setVerticalGroup(
			gl_titelPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_titelPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_titelPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(titel1Label)
						.addComponent(gameOverLabel))
					.addComponent(titel2Label)
					.addContainerGap())
		);
		titelPanel.setLayout(gl_titelPanel);
		
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
						.addComponent(sweetsInPockets)
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
						.addComponent(sweetsInPockets)
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
						.addComponent(buyTitel)
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
					.addComponent(buyTitel)
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
						.addComponent(titelPanel, GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
						.addComponent(currentPanel, GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
						.addComponent(buySellPanel, GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
						.addComponent(hideSeekPanel, GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(titelPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
