package com.personalfinance.common;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BottomPanel extends JPanel {
	
    JPanel panel = new JPanel();
	public RkButton btnAdd, btnView, btnEdit, btnClear, btnClose,btnSave;
	int x=200,y=20,buttonwidth=100,buttonHeight=30,spaceBetweenBtn=200;

	public BottomPanel() {
		
//		setBackground();
//		setBounds(0,dimension.height-70,width,70);
		
		btnAdd=new RkButton("ADD");
		btnAdd.setBounds(x, y, buttonwidth, buttonHeight);
		add(btnAdd);
		
		btnView=new RkButton("VIEW");
		btnView.setBounds(x+=spaceBetweenBtn, y, buttonwidth, buttonHeight);
		add(btnView);
		
		btnSave=new RkButton("SAVE");
		btnSave.setBounds(x+=spaceBetweenBtn, y, buttonwidth, buttonHeight);
		add(btnSave);
		
		btnEdit=new RkButton("EDIT");
		btnEdit.setBounds(x+=spaceBetweenBtn, y, buttonwidth, buttonHeight);
		add(btnEdit);
		
		btnClear=new RkButton("CLEAR");
		btnClear.setBounds(x+=spaceBetweenBtn, y, buttonwidth, buttonHeight);
		add(btnClear);
		
		btnClose=new RkButton("CLOSE");
		btnClose.setBounds(x+=spaceBetweenBtn, y, buttonwidth, buttonHeight);
		add(btnClose);
		
	}
}
