package com.personalfinance.common;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BottomPanel extends JPanel {
	
    JPanel panel = new JPanel();
	RkButton btnAdd, btnView, btnEdit, btnCancel, btnClose;
	Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

	public BottomPanel(JFrame frame) {

		setBackground(Color.cyan);
		
	    

	}
}
