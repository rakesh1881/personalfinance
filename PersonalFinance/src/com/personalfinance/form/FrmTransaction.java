package com.personalfinance.form;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.personalfinance.common.CommonValues;
import com.personalfinance.common.MasterMainIconPanel;
import com.personalfinance.form.Accounttran.AccountTranCommonScreen;
import com.personalfinance.form.accountmaster.AccountCommonScreen;
import com.personalfinance.form.expensetran.ExpenseTranCommonScreen;
import com.personalfinance.form.incomemaster.IncomeViewScreen;
import com.personalfinance.form.incometran.IncomeTranCommonScreen;

public class FrmTransaction extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JPanel masterPanel;

	MasterMainIconPanel masterMainIconPanel;
    IncomeTranCommonScreen incomeCommonScreen;
    ExpenseTranCommonScreen expenseTranCommonScreen;
    AccountTranCommonScreen accountTranCommonScreen;
    MainMenu mainmenu;
    IncomeViewScreen incomeViewScreen;

	public FrmTransaction() {
		setTitle("TRANSACTION");
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
		expenseTranCommonScreen.closeThisPanelOpenTheRequestedPanel(masterMainIconPanel);
		accountTranCommonScreen.closeThisPanelOpenTheRequestedPanel(masterMainIconPanel);
		
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
				expenseTranCommonScreen.setVisible(true);

			}
		});
		
		masterMainIconPanel.accountsBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				masterMainIconPanel.setVisible(false);
				accountTranCommonScreen.setVisible(true);

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
		
		incomeCommonScreen = new IncomeTranCommonScreen();
		incomeCommonScreen.setLayout(null);
		incomeCommonScreen.setVisible(false);
		masterPanel.add(incomeCommonScreen);
		
		expenseTranCommonScreen = new ExpenseTranCommonScreen();
		expenseTranCommonScreen.setLayout(null);
		expenseTranCommonScreen.setVisible(false);
		masterPanel.add(expenseTranCommonScreen);
		
		accountTranCommonScreen = new AccountTranCommonScreen();
		accountTranCommonScreen.setLayout(null);
		accountTranCommonScreen.setVisible(false);
		masterPanel.add(accountTranCommonScreen);
		
//		incomeViewScreen = new IncomeViewScreen();
//		incomeViewScreen.setLayout(null);
//		incomeViewScreen.setVisible(false);
//		masterPanel.add(incomeViewScreen);
		

	}

}
