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
		mainFrame.setBounds(100, 100, 942, 675);
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
		gameOverLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		gameOverLabel.setForeground(new Color(255, 153, 51));
		gameOverLabel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 22));
		
		// Current Panel
		JPanel currentPanel = new JPanel();
		currentPanel.setBackground(new Color(0, 102, 153));
		
		JLabel currentDayLabel = new JLabel("Tag:");
		currentDayLabel.setForeground(new Color(153, 204, 255));
		currentDayLabel.setPreferredSize(new Dimension(30, 17));
		currentDayLabel.setMinimumSize(new Dimension(30, 17));
		currentDayLabel.setMaximumSize(new Dimension(30, 17));
		currentDayLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		currentDayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel currentLocationLabel = new JLabel("Wo bin ich eigentlich...?");
		currentLocationLabel.setForeground(new Color(153, 204, 255));
		currentLocationLabel.setPreferredSize(new Dimension(0, 17));
		currentLocationLabel.setMinimumSize(new Dimension(0, 17));
		currentLocationLabel.setMaximumSize(new Dimension(30, 17));
		currentLocationLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		currentLocationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel cashLabel = new JLabel("Cash dabei:");
		cashLabel.setForeground(new Color(153, 204, 255));
		cashLabel.setPreferredSize(new Dimension(0, 17));
		cashLabel.setMinimumSize(new Dimension(0, 17));
		cashLabel.setMaximumSize(new Dimension(0, 17));
		cashLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		cashLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel cash = new JLabel("cash");
		cash.setForeground(new Color(255, 255, 0));
		cash.setPreferredSize(new Dimension(50, 17));
		cash.setMinimumSize(new Dimension(50, 17));
		cash.setMaximumSize(new Dimension(50, 17));
		cash.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		cash.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel pocketsLabel = new JLabel("Was hab ich in den Taschen?");
		pocketsLabel.setForeground(new Color(153, 204, 255));
		pocketsLabel.setPreferredSize(new Dimension(0, 17));
		pocketsLabel.setMinimumSize(new Dimension(0, 17));
		pocketsLabel.setMaximumSize(new Dimension(0, 17));
		pocketsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		pocketsLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		JLabel currentLocation = new JLabel("currentLocation");
		currentLocation.setForeground(new Color(255, 255, 0));
		currentLocation.setPreferredSize(new Dimension(50, 17));
		currentLocation.setMinimumSize(new Dimension(50, 17));
		currentLocation.setMaximumSize(new Dimension(50, 17));
		currentLocation.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		currentLocation.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel currentDay = new JLabel("currentDay");
		currentDay.setForeground(new Color(255, 255, 0));
		currentDay.setPreferredSize(new Dimension(50, 17));
		currentDay.setMinimumSize(new Dimension(50, 17));
		currentDay.setMaximumSize(new Dimension(50, 17));
		currentDay.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		currentDay.setAlignmentX(Component.CENTER_ALIGNMENT);
		JComboBox<String> sweetsInPockets = new JComboBox<String>();
		sweetsInPockets.setBackground(new Color(51, 102, 153));
		sweetsInPockets.setForeground(new Color(255, 255, 0));
		sweetsInPockets.setPreferredSize(new Dimension(50, 22));
		sweetsInPockets.setMinimumSize(new Dimension(50, 22));
		sweetsInPockets.setMaximumSize(new Dimension(50, 22));
		sweetsInPockets.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		
		// Buy Sell Panel
		JPanel buySellPanel = new JPanel();
		buySellPanel.setBackground(new Color(102, 153, 204));
		
		JLabel buyTitel = new JLabel("Hast du was für mich?");
		buyTitel.setForeground(new Color(255, 255, 0));
		buyTitel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		JLabel buySelectionLabel = new JLabel("Ich mag...");
		buySelectionLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		buySelectionLabel.setPreferredSize(new Dimension(90, 14));
		buySelectionLabel.setMinimumSize(new Dimension(90, 14));
		buySelectionLabel.setMaximumSize(new Dimension(90, 14));
		buySelectionLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		JLabel sellTitel = new JLabel("Hey! Willst du was Süßes?");
		sellTitel.setForeground(new Color(255, 255, 0));
		sellTitel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		JLabel sellSelectionLabel = new JLabel("Ich verkaufe dir...");
		sellSelectionLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		sellSelectionLabel.setPreferredSize(new Dimension(90, 14));
		sellSelectionLabel.setMinimumSize(new Dimension(90, 14));
		sellSelectionLabel.setMaximumSize(new Dimension(90, 14));
		sellSelectionLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		JLabel buySellInfo = new JLabel("buySellInfo");
		buySellInfo.setPreferredSize(new Dimension(50, 14));
		buySellInfo.setMinimumSize(new Dimension(50, 14));
		buySellInfo.setMaximumSize(new Dimension(50, 14));
		buySellInfo.setFont(new Font("Trebuchet MS", Font.ITALIC, 13));
		buyQuantity = new JTextField();
		buyQuantity.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		buyQuantity.setColumns(10);
		sellQuantity = new JTextField();
		sellQuantity.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		sellQuantity.setColumns(10);
		JComboBox<String> buySelection = new JComboBox<String>();
		buySelection.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		JComboBox<String> sellSelection = new JComboBox<String>();
		sellSelection.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		
		// Hide Seek Panel
		JPanel hideSeekPanel = new JPanel();
		hideSeekPanel.setBackground(new Color(102, 153, 204));
		
		JLabel hideSeekTitel = new JLabel("Du hast ein echt gutes Versteck für deine Süßis, da sind sie sicher!");
		hideSeekTitel.setForeground(new Color(255, 255, 0));
		hideSeekTitel.setPreferredSize(new Dimension(100, 14));
		hideSeekTitel.setMinimumSize(new Dimension(100, 14));
		hideSeekTitel.setMaximumSize(new Dimension(100, 14));
		hideSeekTitel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		JLabel stashLabel = new JLabel("Was liegt schon im Versteck?");
		stashLabel.setBackground(new Color(255, 255, 153));
		stashLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		stashLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		JLabel seekQuantityLabel = new JLabel("hm... wie viel nehm ich mit...");
		seekQuantityLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		seekQuantityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel hideSeekInfo = new JLabel("hideSeekInfo");
		hideSeekInfo.setPreferredSize(new Dimension(50, 14));
		hideSeekInfo.setMinimumSize(new Dimension(50, 14));
		hideSeekInfo.setMaximumSize(new Dimension(50, 14));
		hideSeekInfo.setFont(new Font("Trebuchet MS", Font.ITALIC, 13));
		seekQuantity = new JTextField();
		seekQuantity.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		seekQuantity.setColumns(10);
		JComboBox<String> stash = new JComboBox<String>();
		stash.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
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
						.addComponent(titel2Label, GroupLayout.DEFAULT_SIZE, 886, Short.MAX_VALUE)
						.addGroup(gl_titelPanel.createSequentialGroup()
							.addComponent(titel1Label, GroupLayout.PREFERRED_SIZE, 435, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
							.addComponent(gameOverLabel, GroupLayout.PREFERRED_SIZE, 431, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_titelPanel.setVerticalGroup(
			gl_titelPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_titelPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_titelPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(titel1Label)
						.addComponent(gameOverLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(titel2Label, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
		);
		titelPanel.setLayout(gl_titelPanel);
		
		GroupLayout gl_currentPanel = new GroupLayout(currentPanel);
		gl_currentPanel.setHorizontalGroup(
			gl_currentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_currentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_currentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(pocketsLabel, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
						.addComponent(cashLabel, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
						.addComponent(currentDayLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
						.addComponent(currentLocationLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_currentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(cash, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
						.addGroup(gl_currentPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(sweetsInPockets, 0, 263, Short.MAX_VALUE))
						.addComponent(currentLocation, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE)
						.addComponent(currentDay, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
					.addGap(301))
		);
		gl_currentPanel.setVerticalGroup(
			gl_currentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_currentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_currentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(currentDayLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(currentDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_currentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(currentLocationLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(currentLocation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_currentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cashLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cash, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_currentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(sweetsInPockets, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(pocketsLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(31, Short.MAX_VALUE))
		);
		currentPanel.setLayout(gl_currentPanel);
		
		GroupLayout gl_buySellPanel = new GroupLayout(buySellPanel);
		gl_buySellPanel.setHorizontalGroup(
			gl_buySellPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buySellPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_buySellPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_buySellPanel.createSequentialGroup()
							.addGap(10)
							.addComponent(sellSelectionLabel, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_buySellPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_buySellPanel.createSequentialGroup()
									.addComponent(sellQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(218)
									.addComponent(buySellInfo, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE))
								.addComponent(sellSelection, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE)))
						.addComponent(sellTitel, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
				.addGroup(gl_buySellPanel.createSequentialGroup()
					.addGroup(gl_buySellPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_buySellPanel.createSequentialGroup()
							.addGap(20)
							.addComponent(buySelectionLabel, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_buySellPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(buyQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(buySelection, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_buySellPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(buyTitel, GroupLayout.PREFERRED_SIZE, 387, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(353, Short.MAX_VALUE))
		);
		gl_buySellPanel.setVerticalGroup(
			gl_buySellPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_buySellPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_buySellPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(buySellInfo, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_buySellPanel.createSequentialGroup()
							.addComponent(buyTitel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_buySellPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(buySelectionLabel, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(buySelection, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(buyQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(sellTitel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_buySellPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(sellSelectionLabel, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(sellSelection, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(sellQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(24))
		);
		buySellPanel.setLayout(gl_buySellPanel);
		
		GroupLayout gl_hideSeekPanel = new GroupLayout(hideSeekPanel);
		gl_hideSeekPanel.setHorizontalGroup(
			gl_hideSeekPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_hideSeekPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_hideSeekPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_hideSeekPanel.createSequentialGroup()
							.addComponent(hideSeekTitel, GroupLayout.DEFAULT_SIZE, 886, Short.MAX_VALUE)
							.addGap(10))
						.addGroup(gl_hideSeekPanel.createSequentialGroup()
							.addGroup(gl_hideSeekPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(seekQuantityLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(stashLabel, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_hideSeekPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_hideSeekPanel.createSequentialGroup()
									.addComponent(seekQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(seekButton, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
									.addGap(35)
									.addComponent(hideSeekInfo, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE))
								.addComponent(stash, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())))
				.addGroup(Alignment.LEADING, gl_hideSeekPanel.createSequentialGroup()
					.addGap(252)
					.addComponent(hideButton, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(519, Short.MAX_VALUE))
		);
		gl_hideSeekPanel.setVerticalGroup(
			gl_hideSeekPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_hideSeekPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(hideSeekTitel, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(hideButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_hideSeekPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(stashLabel)
						.addComponent(stash, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_hideSeekPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(seekQuantityLabel)
						.addComponent(seekQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(seekButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(hideSeekInfo, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		hideSeekPanel.setLayout(gl_hideSeekPanel);
		
		GroupLayout gl_contentPane = new GroupLayout(mainFrame.getContentPane());
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(hideSeekPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 906, Short.MAX_VALUE)
						.addComponent(buySellPanel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 906, Short.MAX_VALUE)
						.addComponent(currentPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 906, Short.MAX_VALUE)
						.addComponent(titelPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 906, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(titelPanel, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(currentPanel, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buySellPanel, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(hideSeekPanel, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(85, Short.MAX_VALUE))
		);
		mainFrame.getContentPane().setLayout(gl_contentPane);
	}
}
