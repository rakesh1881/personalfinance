package com.personalfinance.form;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.net.URL;
import java.security.CodeSource;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import com.personalfinance.main.ApplicationStart;

public class MainMenu extends JFrame{

	JPanel mainMenuPanel;
	JButton btnMaster,btnTransaction,btnReports,btnGraphs,btnStocks,btnNetworth,btnAccounts,btnLogout,btnexit;
	ImageIcon masterIcon,accountsIcon,graphsIcon,logoutIcon,reportsIcon,stocksIcon,tranIcon;
	
	FrmMaster frmmaster;
	Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	
	int btnWidth=(dimension.width-50)/3,btnHeight=(dimension.height-50)/3;
	int Xvalue=(dimension.width-50)/3,  btnXFirst=10,btnX2Row=Xvalue+20,btnX3Row=btnX2Row+Xvalue+10;
	int Yvalue=(dimension.height-50)/3,  btnYFirst=10,btnY2Row=Yvalue+20,btnY3Row=btnY2Row+Yvalue+10;
	
	public MainMenu() {
		
		setTitle("Main Menu");
		 setPreferredSize(new Dimension(dimension.width,dimension.height));
		 setResizable(false);
	     setBounds(0, 0, dimension.width,dimension.height);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);
		createMenus();
		actionlistners();
		setVisible(true);
		System.err.println(Xvalue+"   "+Yvalue);
		
	}

	private void actionlistners() {
		
		btnMaster.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					setVisible(false);
					frmmaster = new FrmMaster();
					frmmaster.setVisible(true);
				}
				
				super.keyPressed(e);
			}
		});
		btnMaster.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frmmaster = new FrmMaster();
				setVisible(false);
				
				frmmaster.setVisible(true);
				
			}
		});
	}

	private void createMenus() {
		Color customColor = new Color(221, 221, 221); 
		mainMenuPanel= new JPanel();
		mainMenuPanel.setBounds(0, 0,dimension.width,dimension.height);
		mainMenuPanel.setLayout(null);
		mainMenuPanel.setBackground(customColor);
		add(mainMenuPanel);
		
		
		String path = getpath();
		 masterIcon = new ImageIcon(path+"\\icons\\masterIcon.png");
		btnMaster = new JButton("");
		btnMaster.setBounds(btnXFirst, btnYFirst, btnWidth, btnHeight);
		btnMaster.setIcon(masterIcon);
		mainMenuPanel.add(btnMaster);
		
		tranIcon = new ImageIcon(path+"\\icons\\tranicon.png");
		btnTransaction = new JButton("");
		btnTransaction.setBounds(btnX2Row, btnYFirst, btnWidth, btnHeight);
		btnTransaction.setIcon(tranIcon);
		mainMenuPanel.add(btnTransaction);
		
		stocksIcon = new ImageIcon(path+"\\icons\\stocksicon.png");
		btnStocks = new JButton("");
		btnStocks.setBounds(btnX3Row, btnYFirst, btnWidth, btnHeight);
		btnStocks.setIcon(stocksIcon);
		

		
		mainMenuPanel.add(btnStocks);
		
		
		reportsIcon = new ImageIcon(path+"\\icons\\reportsicon.png");
		btnReports = new JButton("");
		btnReports.setBounds(btnXFirst, btnY2Row, btnWidth, btnHeight);
		btnReports.setIcon(reportsIcon);
		mainMenuPanel.add(btnReports);
		
		
		graphsIcon = new ImageIcon(path+"\\icons\\graphsicon.png");
		btnGraphs = new JButton("");
		btnGraphs.setBounds(btnX2Row, btnY2Row, btnWidth, btnHeight);
		btnGraphs.setIcon(graphsIcon);
		mainMenuPanel.add(btnGraphs);
		
		accountsIcon = new ImageIcon(path+"\\icons\\accountsicon.png");
		btnAccounts = new JButton("");
		btnAccounts.setBounds(btnX3Row, btnY2Row, btnWidth, btnHeight);
		btnAccounts.setIcon(accountsIcon);
		mainMenuPanel.add(btnAccounts);
		
		btnNetworth = new JButton("");
		btnNetworth.setBounds(btnXFirst, btnY3Row, btnWidth, btnHeight);
//		btnLogout.setIcon(logoutIcon);
		mainMenuPanel.add(btnNetworth);
		
		btnexit = new JButton("");
		btnexit.setBounds(btnX2Row, btnY3Row, btnWidth, btnHeight);
//		btnexit.setIcon(logoutIcon);
		mainMenuPanel.add(btnexit);
		
		logoutIcon = new ImageIcon(path+"\\icons\\logouticon.png");
		btnLogout = new JButton("");
		btnLogout.setBounds(btnX3Row, btnY3Row, btnWidth, btnHeight);
		btnLogout.setIcon(logoutIcon);
		mainMenuPanel.add(btnLogout);
		
		
	}

	private String getpath() {
		
		URL url = ApplicationStart.class.getProtectionDomain().getCodeSource().getLocation();
		File file =new File(url.getFile());
		return file.getAbsolutePath().substring(0,file.getAbsolutePath().length()-4);
		
	}

}
