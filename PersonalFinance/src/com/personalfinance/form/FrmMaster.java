package com.personalfinance.form;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrmMaster extends JFrame {
	JPanel masterPanel,topPanel,bottomPanel;
	JButton btn;
	JComboBox<Object> administrationCombo,transactionCombo;
	Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	

	public FrmMaster() {
		setTitle("MASTER");
		setPreferredSize(new Dimension(dimension.width, dimension.height));
		setResizable(false);
		setBounds(0, 0, dimension.width, dimension.height);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		createMenus();
		setUndecorated(true);
		setVisible(true);

	}

	private void createMenus() {

		topPanel = new JPanel();
		topPanel.setBounds(0, 0, dimension.width,50);
		topPanel.setLayout(null);
		topPanel.setBackground(Color.CYAN);
		add(topPanel);
		
		masterPanel = new JPanel();
		masterPanel.setBounds(0, 50, dimension.width, dimension.height);
		masterPanel.setLayout(null);
		add(masterPanel);

		administrationCombo = new JComboBox<>();
		administrationCombo.setName("Adminstration");
		String abc[] = { "one", "Two", "Three", "Four", "Five" };
		int a = abc.length;
		System.out.println(a);
		for (int i = 0; i < abc.length; i++) {
			administrationCombo.addItem(abc[i]);
		}
		administrationCombo.setBounds(0, 0, 100, 25);
		masterPanel.add(administrationCombo);
		
		

	}

}
