package com.personalfinance.common;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class MasterMainIconPanel extends JPanel{
	Dimension dimension =Toolkit.getDefaultToolkit().getScreenSize();
	public RkButton incomeBtn,expenseBtn,accountsBtn,exitBtn,btnApp5;
	int x=10,y=50,btnwidth=100,btnheight=100;
	
	public MasterMainIconPanel(Color color) {
		
		setBounds(0, 0, 250, dimension.height);
		setBackground(color);
		setLayout(null);
		
		incomeBtn= new RkButton("ONE");
		incomeBtn.setBounds(x, y, btnwidth, btnheight);
		add(incomeBtn);
		
		expenseBtn= new RkButton("TWO");
		expenseBtn.setBounds(x+=120, y, btnwidth, btnheight);
		add(expenseBtn);
		
		accountsBtn= new RkButton("Three");
		accountsBtn.setBounds(x-=120, y+=btnwidth+10, btnwidth, btnheight);
		add(accountsBtn);
		
		exitBtn= new RkButton("Four");
		exitBtn.setBounds(x+=120, y, btnwidth, btnheight);
		add(exitBtn);
		
		btnApp5= new RkButton("Five");
		btnApp5.setBounds(x+=120, y, btnwidth, btnheight);
		add(btnApp5);
	
		
	}


}
