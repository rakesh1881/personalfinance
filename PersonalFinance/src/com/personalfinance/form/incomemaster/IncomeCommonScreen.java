package com.personalfinance.form.incomemaster;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.personalfinance.common.CommonValues;
import com.personalfinance.common.RkButton;
import com.personalfinance.model.IncomeMasterModel;

public class IncomeCommonScreen extends JPanel {

	private static final long serialVersionUID = 1L; 

	JPanel topPanel;
	JPanel bottomPanel;
	IncomeAddScreen incomeAddScreen;
	IncomeViewScreen incomeViewScreen;
	IncomeEditScreen incomeEditScreen;
	IncomeMasterModel incomeModel;
	ArrayList<IncomeMasterModel> lstincomeModel = new  ArrayList<IncomeMasterModel>();
	int x = 500, y = 200, lblwidth = 100, height = 20, txtwidth = 200, combowidth = 75;
	
	public RkButton btnAdd, btnView, btnEdit, btnClear, btnClose,btnSave;
	int bottombtnX=200,bottombtnY=20,buttonwidth=100,buttonHeight=30,spaceBetweenBtn=200;

	public IncomeCommonScreen() {
		setBounds(0, 0, CommonValues.screenWidth, CommonValues.screenHeight);
		createMenus();
		btnListeners();
		
	}
	

	public void closeThisPanelOpenTheRequestedPanel(JPanel panel) {
		
		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);		
				incomeAddScreen.setVisible(false);
				incomeEditScreen.setVisible(false);
				incomeViewScreen.setVisible(false);
				panel.setVisible(true);
				

			}
		});
		
	}

	private void btnListeners() {
		
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				incomeAddScreen.setVisible(true);
				incomeEditScreen.setVisible(false);
				incomeViewScreen.setVisible(false);
				btnSave.setEnabled(true);
			}
		});
		
		btnClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				btnAdd.requestFocus();
				incomeViewScreen.setVisible(false);
				incomeAddScreen.clearDetailsReqFocusToText();
				btnSave.setEnabled(false);
				

			}
		});
		 
		btnView.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				incomeAddScreen.setVisible(false);
				incomeEditScreen.setVisible(false);
				incomeViewScreen.getIncomeView();
				btnEdit.setEnabled(true);
				incomeViewScreen.setVisible(true);
				btnSave.setEnabled(false);

			}
		});
		
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!CommonValues.isUpdate)
				incomeAddScreen.saveIncome();
				else {
					incomeEditScreen.updateValues();
					CommonValues.isUpdate=false;
					incomeEditScreen.setVisible(false);
					incomeViewScreen.getIncomeView();
				}
			
			}
		});
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				btnSave.setEnabled(true);
				CommonValues.isUpdate=true;
				incomeEditScreen.setValuestoUpdateIncome(incomeViewScreen.getSelectedKeycode());
				incomeEditScreen.setVisible(true);
				
				
		
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

		
		incomeAddScreen = new IncomeAddScreen();
		incomeAddScreen.setLayout(null);
		incomeAddScreen.setVisible(false);
		add(incomeAddScreen);
		
		incomeEditScreen = new IncomeEditScreen();
		incomeEditScreen.setLayout(null);
		incomeEditScreen.setVisible(false);
		add(incomeEditScreen);
		
		incomeViewScreen = new IncomeViewScreen();
		incomeViewScreen.setLayout(null);
		incomeViewScreen.setVisible(false);
		add(incomeViewScreen);

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
