package com.github.sweettooth.viewSwing.session;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import com.github.sweettooth.viewSwing.commons.Tools;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class StartFrameDesign {
	JFrame frame;
	JLabel titleLabel;
	JLabel namePlayerLabel;
	JTextField namePlayer;
	JButton playButton;
	JTable table;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	JFrame createFrame(ScoreTableModel scoreTableModel) {
		Tools.runAndWaitOnEDT( () -> {
			String sampleText = "Sample text for formatting purposes.";
			
			titleLabel = new JLabel(sampleText);
			titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
			titleLabel.setForeground(new Color(255, 255, 0));
			titleLabel.setFont(new Font("Broadway", Font.BOLD, 36));
			
			namePlayer = new JTextField(sampleText);
			namePlayer.setColumns(10);
			namePlayer.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
			namePlayer.setPreferredSize(new Dimension(100, 20));
			
			namePlayerLabel = new JLabel(sampleText);
			namePlayerLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
			namePlayerLabel.setForeground(new Color(255, 255, 0));
			
			playButton = new JButton(sampleText);
			playButton.setPreferredSize(new Dimension(160, 40));
			playButton.setMaximumSize(new Dimension(160, 40));
			playButton.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
			playButton.setMinimumSize(new Dimension(100, 25));
			
			table = new JTable(scoreTableModel);
			table.setMinimumSize(new Dimension(650, 200));
			table.setPreferredScrollableViewportSize(new Dimension(650, 500));
			table.setShowHorizontalLines(false);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			table.setRowHeight(20);
			table.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
			TableColumnModel columnModel = table.getColumnModel();
			columnModel.getColumn(0).setPreferredWidth(50);
			columnModel.getColumn(1).setPreferredWidth(300);
			columnModel.getColumn(2).setPreferredWidth(100);
			columnModel.getColumn(0).setMinWidth(50);
			columnModel.getColumn(1).setMinWidth(200);
			columnModel.getColumn(2).setMinWidth(50);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
			scrollPane.setMinimumSize(new Dimension(650, 200));
			scrollPane.setPreferredSize(new Dimension(650, 500));
			scrollPane.setViewportView(table);
			
			frame = new JFrame();
			frame.setMinimumSize(new Dimension(775, 750));
			frame.setPreferredSize(new Dimension(775, 750));
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setBackground(new Color(0, 102, 153));
			
			GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(50)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(titleLabel, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(namePlayerLabel, Alignment.LEADING)
									.addComponent(namePlayer, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE))
								.addGap(50)
								.addComponent(playButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED))
							.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(50))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(30)
						.addComponent(titleLabel)
						.addGap(20)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(namePlayerLabel)
								.addGap(40))
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(playButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(namePlayer, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
						.addGap(30)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(50, Short.MAX_VALUE))
			);
			frame.getContentPane().setLayout(groupLayout);
		});
			
		return frame;
	}
}
