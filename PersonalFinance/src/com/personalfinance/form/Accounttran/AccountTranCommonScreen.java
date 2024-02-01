package com.personalfinance.form.Accounttran;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.personalfinance.common.CommonValues;
import com.personalfinance.common.RkButton;
import com.personalfinance.model.AccountMasterModel;

public class AccountTranCommonScreen extends JPanel {

	private static final long serialVersionUID = 1L; 

	JPanel topPanel;
	JPanel bottomPanel;
	AccountTranScreen accountAddScreen;
	AccountTranViewScreen accountViewScreen;
	AccountTranEditScreen accountEditScreen;
	AccountMasterModel accountModel;
	ArrayList<AccountMasterModel> lstaccountModel = new  ArrayList<AccountMasterModel>();
	int x = 500, y = 200, lblwidth = 100, height = 20, txtwidth = 200, combowidth = 75;
	
	public RkButton btnAdd, btnView, btnEdit, btnClear, btnClose,btnSave;
	int bottombtnX=200,bottombtnY=20,buttonwidth=100,buttonHeight=30,spaceBetweenBtn=200;

	public AccountTranCommonScreen() {
		setBounds(0, 0, CommonValues.screenWidth, CommonValues.screenHeight);
		createMenus();
		btnListeners();
		
	}
	

	public void closeThisPanelOpenTheRequestedPanel(JPanel panel) {
		
		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);		
				accountAddScreen.setVisible(false);
				accountEditScreen.setVisible(false);
				accountViewScreen.setVisible(false);
				panel.setVisible(true);
				

			}
		});
		
	}

	private void btnListeners() {
		
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				accountAddScreen.setVisible(true);
				accountEditScreen.setVisible(false);
				accountViewScreen.setVisible(false);
				btnSave.setEnabled(true);
			}
		});
		
		btnClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				btnAdd.requestFocus();
				accountViewScreen.setVisible(false);
				accountAddScreen.clearDetailsReqFocusToText();
				btnSave.setEnabled(false);
				

			}
		});
		 
		btnView.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				accountAddScreen.setVisible(false);
				accountEditScreen.setVisible(false);
				accountViewScreen.getAccountView();
				btnEdit.setEnabled(true);
				accountViewScreen.setVisible(true);
				btnSave.setEnabled(false);

			}
		});
		
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!CommonValues.isUpdate)
				accountAddScreen.saveAccount();
				else {
					accountEditScreen.updateValues();
					CommonValues.isUpdate=false;
					accountEditScreen.setVisible(false);
					
					accountViewScreen.getAccountView();
				}
			
			}
		});
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				btnSave.setEnabled(true);
				CommonValues.isUpdate=true;
				accountEditScreen.setValuestoUpdateAccount(accountViewScreen.getSelectedKeycode());
				accountEditScreen.setVisible(true);
				
				
		
			}
		});
		
		
	}

	public void createMenus() {
		
		topPanel = new JPanel();
		topPanel.setBackground(Color.cyan);
		topPanel.setBounds(CommonValues.topPanelX,CommonValues.topPanelY,CommonValues.screenWidth,CommonValues.topPanelHeight);
		topPanel.setLayout(null);
		add(topPanel);

		bottomPanel = new JPanel();
		addButtonsInBottomPanel(bottomPanel);
		bottomPanel.setLayout(null);
		add(bottomPanel);

		
		accountAddScreen = new AccountTranScreen();
		accountAddScreen.setLayout(null);
		accountAddScreen.setVisible(false);
		add(accountAddScreen);
		
		accountEditScreen = new AccountTranEditScreen();
		accountEditScreen.setLayout(null);
		accountEditScreen.setVisible(false);
		add(accountEditScreen);
		
		accountViewScreen = new AccountTranViewScreen();
		accountViewScreen.setLayout(null);
		accountViewScreen.setVisible(false);
		add(accountViewScreen);

	}

	private void addButtonsInBottomPanel(JPanel bottomPanel) {
		
		
		bottomPanel.setBackground(Color.cyan);
		bottomPanel.setBounds(0,CommonValues.screenHeight-70,CommonValues.screenWidth,70);
		
		btnAdd=new RkButton("ADD");
		btnAdd.setBounds(bottombtnX, bottombtnY, buttonwidth, buttonHeight);
		bottomPanel.add(btnAdd);
		
		btnView=new RkButton("VIEW");
		btnView.setBounds(bottombtnX+=spaceBetweenBtn, bottombtnY, buttonwidth, buttonHeight);
		bottomPanel.add(btnView);
		
		btnSave=new RkButton("SAVE");
		btnSave.setBounds(bottombtnX+=spaceBetweenBtn, bottombtnY, buttonwidth, buttonHeight);
		btnSave.setEnabled(false);
		bottomPanel.add(btnSave);
		
		btnEdit=new RkButton("EDIT");
		btnEdit.setBounds(bottombtnX+=spaceBetweenBtn, bottombtnY, buttonwidth, buttonHeight);
		btnEdit.setEnabled(false);
		bottomPanel.add(btnEdit);
		
		btnClear=new RkButton("CLEAR");
		btnClear.setBounds(bottombtnX+=spaceBetweenBtn, bottombtnY, buttonwidth, buttonHeight);
		bottomPanel.add(btnClear);
		
		btnClose=new RkButton("CLOSE");
		btnClose.setBounds(bottombtnX+=spaceBetweenBtn, bottombtnY, buttonwidth, buttonHeight);
		bottomPanel.add(btnClose);
		
	}

}
