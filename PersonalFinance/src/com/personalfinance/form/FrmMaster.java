package com.personalfinance.form;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.personalfinance.common.CommonValues;
import com.personalfinance.common.MasterMainIconPanel;
import com.personalfinance.form.accountmaster.AccountCommonScreen;
import com.personalfinance.form.expensemaster.ExpenseAddScreen;
import com.personalfinance.form.expensemaster.ExpenseCommonScreen;
import com.personalfinance.form.incomemaster.IncomeCommonScreen;
import com.personalfinance.form.incomemaster.IncomeViewScreen;

public class FrmMaster extends JFrame {
	
	JPanel masterPanel;

	MasterMainIconPanel masterMainIconPanel;
    IncomeCommonScreen incomeCommonScreen;
    ExpenseCommonScreen expenseCommonScreen;
    AccountCommonScreen accountCommonScreen;
    MainMenu mainmenu;
    IncomeViewScreen incomeViewScreen;

	public FrmMaster() {
		setTitle("MASTER");
		setPreferredSize(new Dimension(CommonValues.screenWidth, CommonValues.screenHeight));
		setResizable(false);
		setBounds(0, 0, CommonValues.screenWidth, CommonValues.screenHeight);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		createMenus();
		listeners();
		setUndecorated(true);
		setVisible(true);
	}

	private void listeners() {
		
		incomeCommonScreen.closeThisPanelOpenTheRequestedPanel(masterMainIconPanel);
		expenseCommonScreen.closeThisPanelOpenTheRequestedPanel(masterMainIconPanel);
		accountCommonScreen.closeThisPanelOpenTheRequestedPanel(masterMainIconPanel);
		
		masterMainIconPanel.incomeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				masterMainIconPanel.setVisible(false);
				incomeCommonScreen.setVisible(true);
								
			}
		});

		masterMainIconPanel.expenseBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				masterMainIconPanel.setVisible(false);
				expenseCommonScreen.setVisible(true);

			}
		});
		
		masterMainIconPanel.accountsBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				masterMainIconPanel.setVisible(false);
				accountCommonScreen.setVisible(true);

			}
		});
		

		masterMainIconPanel.exitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				mainmenu = new MainMenu();
				setVisible(false);
				mainmenu.setVisible(true);
				

			}
		});

	}

	private void createMenus() {

		masterPanel = new JPanel();
		masterPanel.setBounds(0, 0, CommonValues.screenWidth, CommonValues.screenHeight);
		masterPanel.setLayout(null);
		add(masterPanel);

		masterMainIconPanel = new MasterMainIconPanel(Color.cyan);
		masterMainIconPanel.incomeBtn.setText("Income");
		masterMainIconPanel.expenseBtn.setText("Expense");
		masterMainIconPanel.accountsBtn.setText("Account");
		masterMainIconPanel.exitBtn.setText("Exit");
		masterPanel.add(masterMainIconPanel);
		
		incomeCommonScreen = new IncomeCommonScreen();
		incomeCommonScreen.setLayout(null);
		incomeCommonScreen.setVisible(false);
		masterPanel.add(incomeCommonScreen);
		
		expenseCommonScreen = new ExpenseCommonScreen();
		expenseCommonScreen.setLayout(null);
		expenseCommonScreen.setVisible(false);
		masterPanel.add(expenseCommonScreen);
		
		accountCommonScreen = new AccountCommonScreen();
		accountCommonScreen.setLayout(null);
		accountCommonScreen.setVisible(false);
		masterPanel.add(accountCommonScreen);
		
//		incomeViewScreen = new IncomeViewScreen();
//		incomeViewScreen.setLayout(null);
//		incomeViewScreen.setVisible(false);
//		masterPanel.add(incomeViewScreen);
		

	}

}
