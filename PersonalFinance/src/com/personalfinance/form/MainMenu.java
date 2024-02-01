package com.personalfinance.form;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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

import com.personalfinance.common.LoginPage;
import com.personalfinance.common.RkButton;
import com.personalfinance.main.ApplicationStart;

public class MainMenu extends JFrame {

	JPanel mainMenuPanel;
	JButton btnMaster, btnTransaction, btnReports, btnGraphs, btnStocks, btnNetworth, btnAccounts, btnLogout, btnexit;
	ImageIcon masterIcon, accountsIcon, graphsIcon, logoutIcon, reportsIcon, stocksIcon, tranIcon;
	LoginPage loginpage;
	FrmMaster frmmaster;
	FrmTransaction frmTransaction;
	FrmReports frmReports;
	Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

	int distance=500;
	int btnWidth = 100, btnHeight = 100;
	int Xvalue =(dimension.width- 520)/2 , btnXFirst = Xvalue, btnX2Row = Xvalue+btnWidth + 40, btnX3Row = btnX2Row +btnWidth  + 40,btnX4Row=btnX3Row+btnWidth+40;
	int Yvalue = (dimension.height- 100)/2, btnYFirst = Yvalue, btnY2Row = Yvalue+btnHeight + 40, btnY3Row = btnY2Row + btnHeight + 40;

	public MainMenu() {

		setTitle("Main Menu");
		setPreferredSize(new Dimension(dimension.width, dimension.height));
		setResizable(false);
		setBounds(0, 0, dimension.width, dimension.height);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);
		createMenus();
		actionlistners();
		setVisible(true);
		

	}

	private void actionlistners() {

		btnMaster.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
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
		
		
		btnTransaction.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					setVisible(false);
					frmTransaction = new FrmTransaction();
					frmTransaction.setVisible(true);
				}

				super.keyPressed(e);
			}
		});
		btnTransaction.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frmTransaction = new FrmTransaction();
				setVisible(false);

				frmTransaction.setVisible(true);

			}
		});
		
		
		
		
		btnReports.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					setVisible(false);
					frmReports = new FrmReports();
					frmReports.setVisible(true);
				}

				super.keyPressed(e);
			}
		});
		btnReports.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frmReports = new FrmReports();
				setVisible(false);

				frmReports.setVisible(true);

			}
		});
		
		

		btnLogout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loginpage= new LoginPage();
				setVisible(false);

				loginpage.setVisible(true);

			}
		});
	}

	private void createMenus() {
		Color customColor = new Color(221, 221, 221);
		mainMenuPanel = new JPanel();
		mainMenuPanel.setBounds(0, 0, dimension.width, dimension.height);
		mainMenuPanel.setLayout(null);
		mainMenuPanel.setBackground(customColor);
		add(mainMenuPanel);

		String path = getpath();
//		masterIcon = new ImageIcon(path + "\\icons\\masterIcon.png");
		btnMaster = new RkButton(new Color(255,215,0),new Color(153,153,0),new Color(153,153,0),"Master");
		btnMaster.setBounds(btnXFirst, btnYFirst, btnWidth, btnHeight);
		btnMaster.setIcon(masterIcon);
		mainMenuPanel.add(btnMaster);

//		tranIcon = new ImageIcon(path + "\\icons\\tranicon.png");
		btnTransaction = new RkButton(new Color(127,255,0),new Color(0,153,76),new Color(0,153,76),"Tran");
		btnTransaction.setBounds(btnX2Row, btnYFirst, btnWidth, btnHeight);
		btnTransaction.setIcon(tranIcon);
		mainMenuPanel.add(btnTransaction);
		
//		reportsIcon = new ImageIcon(path + "\\icons\\reportsicon.png");
		btnReports = new RkButton(new Color(0,255,255),new Color(0,153,153),new Color(0,153,153),"Report");
		btnReports.setBounds(btnX3Row, btnYFirst, btnWidth, btnHeight);
		btnReports.setIcon(reportsIcon);
		mainMenuPanel.add(btnReports);

//		stocksIcon = new ImageIcon(path + "\\icons\\stocksicon.png");
//		btnStocks = new JButton("");
//		btnStocks.setBounds(btnX3Row, btnYFirst, btnWidth, btnHeight);
//		btnStocks.setIcon(stocksIcon);
//		mainMenuPanel.add(btnStocks);

//		reportsIcon = new ImageIcon(path + "\\icons\\reportsicon.png");
//		btnReports = new RkButton("");
//		btnReports.setBounds(btnXFirst, btnY2Row, btnWidth, btnHeight);
//		btnReports.setIcon(reportsIcon);
//		mainMenuPanel.add(btnReports);

//		graphsIcon = new ImageIcon(path + "\\icons\\graphsicon.png");
//		btnGraphs = new JButton("");
//		btnGraphs.setBounds(btnX2Row, btnY2Row, btnWidth, btnHeight);
//		btnGraphs.setIcon(graphsIcon);
//		mainMenuPanel.add(btnGraphs);
//
//		accountsIcon = new ImageIcon(path + "\\icons\\accountsicon.png");
//		btnAccounts = new JButton("");
//		btnAccounts.setBounds(btnX3Row, btnY2Row, btnWidth, btnHeight);
//		btnAccounts.setIcon(accountsIcon);
//		mainMenuPanel.add(btnAccounts);
//
//		btnNetworth = new JButton("");
//		btnNetworth.setBounds(btnXFirst, btnY3Row, btnWidth, btnHeight);
////		btnLogout.setIcon(logoutIcon);
//		mainMenuPanel.add(btnNetworth);
//
//		btnexit = new JButton("");
//		btnexit.setBounds(btnX2Row, btnY3Row, btnWidth, btnHeight);
////		btnexit.setIcon(logoutIcon);
//		mainMenuPanel.add(btnexit);

		logoutIcon = new ImageIcon(path + "\\icons\\logouticon.png");
		btnLogout = new RkButton(new Color(255,153,153),new Color(204,0,0),new Color(204,0,0),"Logout");
		btnLogout.setBounds(btnX4Row, btnYFirst, btnWidth, btnHeight);
		btnLogout.setIcon(logoutIcon);
		Font boldFont = new Font(btnLogout.getFont().getName(), Font.BOLD, btnLogout.getFont().getSize());
		btnLogout.setFont(boldFont);
		mainMenuPanel.add(btnLogout);

	}

	private String getpath() {

		URL url = ApplicationStart.class.getProtectionDomain().getCodeSource().getLocation();
		File file = new File(url.getFile());
		return file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - 4);

	}

}
