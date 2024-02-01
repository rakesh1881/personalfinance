package com.personalfinance.common;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
	
import javax.swing.JTextField;

public class Keylisteners {
	
	JButton buttonToListen, buttonTochangeFocus;
	JTextField textToListen, textToChangeFocus;


	
	public void buttonToTextChangeFocus(JButton buttonToListen,JTextField textToChangeFocus) {
		
		buttonToListen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					textToChangeFocus.requestFocus();;
				super.keyPressed(e);
			}
		});
		
	}
	
	

	public void textToTextChangeFocus(JTextField textToListen,JTextField textToChangeFocus) {
		
		textToListen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					textToChangeFocus.requestFocus();
				super.keyPressed(e);
			}
		});
		
	}

	public void keyListenersForButtonToButton(JButton buttonToListen,JButton buttonTochangeFocus) {

		buttonToListen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT)
					buttonTochangeFocus.requestFocus();

				super.keyPressed(e);
			}
		});
	}

	public void keyListenersForTextToButton(JTextField textToListen,JButton buttonTochangeFocus) {

		textToListen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					buttonTochangeFocus.requestFocus();

				super.keyPressed(e);
			}
		});
	}
}
