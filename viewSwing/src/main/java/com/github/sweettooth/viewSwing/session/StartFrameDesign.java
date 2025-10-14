package com.github.sweettooth.viewSwing.session;

import javax.swing.JFrame;
import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class StartFrameDesign {
	JLabel titleLabel;
	JButton playButton;
	JTable table;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	JFrame createFrame(ScoreTableModel scoreTableModel) {
		String sampleText = "Sample text for formatting purposes.";
		
		titleLabel = new JLabel(sampleText);
		titleLabel.setForeground(new Color(255, 255, 0));
		titleLabel.setFont(new Font("Broadway", Font.BOLD, 36));
		
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
		scrollPane.setMinimumSize(new Dimension(650, 200));
		scrollPane.setPreferredSize(new Dimension(650, 500));
		scrollPane.setViewportView(table);
		
		JFrame frame = new JFrame();
		frame.setMinimumSize(new Dimension(775, 750));
		frame.setPreferredSize(new Dimension(775, 750));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(0, 102, 153));
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(50)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(titleLabel)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(playButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(50)))
					.addGap(50))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addComponent(titleLabel)
					.addGap(30)
					.addComponent(playButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(50))
		);
		frame.getContentPane().setLayout(groupLayout);
		return frame;
	}
}
