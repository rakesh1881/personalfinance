package com.personalfinance.common;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.personalfinance.form.MainMenu;

public class LoginPage extends JFrame {

	JPanel loginPanel,innerPanel;
	JLabel userNameLabel, passwordLabel,logo;
	RkButton loginButton, exitbutton;
	JTextField userNameText;
	JPasswordField passwordField;
	MainMenu mainmenu;
	Color bgColor= new Color(114,186,240);
	Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	Keylisteners keyLitener;

	public LoginPage() {

		setTitle("LOGIN PAGE");
		setPreferredSize(new Dimension(656, 383));
		setBounds(0, 0, 656, 383);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		loginformdetails();
		actionlisteners();
		//keyListeners();
		
		setVisible(true);

	}

	private void keyListeners() {
		
//		keyLitener = new Keylisteners(userNameText, passwordField);
//		keyLitener = new Keylisteners(passwordField, loginButton);
//		keyLitener = new Keylisteners(loginButton, exitbutton);
//		keyLitener = new Keylisteners(loginButton, userNameText);
		
		
		
	}

	private void actionlisteners() {

		userNameText.addKeyListener(new KeyAdapter() {

			@Override

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (userNameText.getText().equalsIgnoreCase("") || userNameText.getText().equalsIgnoreCase(null)) {
						JOptionPane.showMessageDialog(null, "Enter the User Name", "Invalid User Name",
								JOptionPane.ERROR_MESSAGE);
					} else {
						passwordField.requestFocus();
					}
				}

				super.keyPressed(e);
			}
		});

		passwordField.addKeyListener(new KeyAdapter() {

			@Override

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (passwordField.getPassword().length == 0) {
						JOptionPane.showMessageDialog(null, "Enter the Password", "Invalid Password",
								JOptionPane.ERROR_MESSAGE);
					} else {
						loginButton.requestFocus();
					}
				}

				super.keyPressed(e);
			}
		});

		loginButton.addKeyListener(new KeyAdapter() {

			@Override

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					if (userNameText.getText().equalsIgnoreCase("rak") && comparepassword()) {
						mainmenu = new MainMenu();
						setVisible(false);
						mainmenu.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Enter Valid Credentials", "Invalid Credentials",
								JOptionPane.ERROR_MESSAGE);
						userNameText.requestFocus();
					}
					
				}

				super.keyPressed(e);
			}
		});

		loginButton.addActionListener((ActionEvent a) -> {

			if (userNameText.getText().equalsIgnoreCase("rak") && comparepassword()) {
				
//				mainmenu = new MainMenu();
//				setVisible(false);
//				mainmenu.setVisible(true);
				try {
				ProcessBuilder processbuilder = new ProcessBuilder("java","-jar","C:/Users/Rocky/Desktop/FlightBook.jar");
				processbuilder.start();
				System.exit(0);
				}catch (Exception e) {
					// TODO: handle exception
				}
			} else {
				JOptionPane.showMessageDialog(null, "Enter Valid Credentials", "Invalid Credentials",
						JOptionPane.ERROR_MESSAGE);
				userNameText.requestFocus();
			}

		});
		
		
//		setVisible(false);
//		action = new Keylisteners(loginButton, mainmenu);
		
		
		exitbutton.addActionListener((ActionEvent a) -> {

		System.exit(0);

		});
		exitbutton.addKeyListener(new KeyAdapter() {
		
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					System.exit(0);
				super.keyPressed(e);
			}
		});
		
	}
	
	

	protected boolean comparepassword() {
		char[] a = passwordField.getPassword();
		String text = new String(a);

		if (text.equals("123"))
			return true;
		else
			return false;
	}

	private void loginformdetails() {

		int x = 85, y = 60,width=100,height=40;

		loginPanel = new JPanel();
		loginPanel.setBounds(0, 0, 656, 383);
		loginPanel.setLayout(null); 
		loginPanel.setBackground(bgColor);
		add(loginPanel);

		innerPanel = new JPanel();
		innerPanel.setBounds(120, 60, 416,263);
		innerPanel.setLayout(null);
//		innerPanel.setBorder();
		loginPanel.add(innerPanel);
		
		logo = new JLabel("RK FINANCE");
		
		logo.setBounds((656-70)/2, 10, 200, 20);
		logo.setForeground(Color.white);
		loginPanel.add(logo);
		userNameLabel = new JLabel("User Name");
		userNameLabel.setBounds(x, y, 100, 30);
		innerPanel.add(userNameLabel);

		userNameText = new JTextField();
		userNameText.setBounds(x + 100, y, width+50, height-10);
		userNameText.requestFocus();
		innerPanel.add(userNameText);

		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(x, y += 50, width, height);
		innerPanel.add(passwordLabel);

		passwordField = new JPasswordField();
		passwordField.setBounds(x + 100, y, width+50, height-10);
		innerPanel.add(passwordField);

	

		loginButton = new RkButton("Login");
		loginButton.setBounds(x + 150, y+=50, width, height);
		innerPanel.add(loginButton);
		
		exitbutton = new RkButton("Exit");
		exitbutton.setBounds(x, y , 100, 40);
		innerPanel.add(exitbutton);

	}

}
