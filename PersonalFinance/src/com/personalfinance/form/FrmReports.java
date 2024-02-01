package com.personalfinance.form;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.personalfinance.common.CommonValues;
import com.personalfinance.common.DbConnection;
import com.personalfinance.common.MasterMainIconPanel;
import com.personalfinance.common.RkButton;
import com.personalfinance.model.AccountSummaryModel;
import com.personalfinance.model.ExpenseSummaryModel;
import com.personalfinance.model.ExpenseTranModel;
import com.personalfinance.model.IncomeSummaryModel;
import com.toedter.calendar.JDateChooser;

public class FrmReports extends JFrame {
	JLabel frmDateLbl, toDateLbl;
	RkButton btnView ,btnclose,btncloserep;
	JPanel reportsPanel, viewPanel;
	MasterMainIconPanel masterMainIconPanel;
	JDateChooser datechooserFromDate, datechooserToDate;
	JCheckBox asOnChkBox;
	JTable accountSummaryTable, incomeSummarytbl, expenseSummarytbl;
	JScrollPane accountSummaryScrollPane, incomeSummaryScrollpane, expenseSummaryScrollPane;
	Connection connection;
	DbConnection dbcon = new DbConnection();
	ArrayList<AccountSummaryModel> lstAccountSummary;
	IncomeSummaryModel incomesummary;
	ArrayList<IncomeSummaryModel> lstIncomesummary;
	ArrayList<ExpenseSummaryModel> lstExpenseSummary;
	ExpenseSummaryModel expenseSummaryModel;
	AccountSummaryModel accountModelSummary;
	DecimalFormat df = new DecimalFormat("0.00");
	MainMenu main ;
	

	int x = 200, y = 200, width = 150, height = 30, space = 200;

	public FrmReports() {

		setTitle("Reports");
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

		btnView.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reportsPanel.setVisible(false);
				viewPanel.setVisible(true);
				viewReport();

			}
		});
		
		
		
		btnclose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reportsPanel.setVisible(true);
				viewPanel.setVisible(false);

			}
		});
		
		btncloserep.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reportsPanel.setVisible(false);
				main = new MainMenu();
				main.setVisible(true);
				setVisible(false);

			}
		});

	}

	private void createMenus() {

		reportsPanel = new JPanel();
		reportsPanel.setBounds(0, 0, CommonValues.screenWidth, CommonValues.screenHeight);
		reportsPanel.setVisible(true);
		reportsPanel.setLayout(null);
		reportsPanel.setBackground(new Color(221, 221, 221));
		add(reportsPanel);

		viewPanel = new JPanel();
		viewPanel.setBounds(0, 0, CommonValues.screenWidth, CommonValues.screenHeight);
		viewPanel.setVisible(false);
		viewPanel.setLayout(null);
		viewPanel.setBackground(new Color(221, 221, 221));
		add(viewPanel);

		asOnChkBox = new JCheckBox("As ON");
		asOnChkBox.setBounds(x, y, width, height);
		asOnChkBox.setSelected(true);
		reportsPanel.add(asOnChkBox);

		frmDateLbl = new JLabel("From Date");
		frmDateLbl.setBounds(x += space, y, width, height);
		reportsPanel.add(frmDateLbl);

		datechooserFromDate = new JDateChooser();
		datechooserFromDate.setBounds(x += space - 130, y, width, height);
		datechooserFromDate.setDate(CommonValues.currentDate);
		reportsPanel.add(datechooserFromDate);

		toDateLbl = new JLabel("To Date");
		toDateLbl.setBounds(x += space, y, width, height);
		reportsPanel.add(toDateLbl);

		datechooserToDate = new JDateChooser();
		datechooserToDate.setBounds(x += space - 130, y, width, height);
		datechooserToDate.setDate(CommonValues.currentDate);
		reportsPanel.add(datechooserToDate);

		btnView = new RkButton("View");
		btnView.setBounds(x += space, y, width, height);
		reportsPanel.add(btnView);
		
		btncloserep = new RkButton("CLOSE REPORT");
		btncloserep.setBounds(1200,700 , width, height);
		reportsPanel.add(btncloserep);
		
		btnclose  = new RkButton("CLOSE");
		btnclose.setBounds(1200,700 , width, height);
		viewPanel.add(btnclose);

	}

	private void viewReport() {

		try {
			String date = "2024-01-24";
			String query = "call sp_getsummaryofAccount('" + date + "');";
			connection = dbcon.createTranConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			lstAccountSummary = new ArrayList<AccountSummaryModel>();
			while (rs.next()) {

				accountModelSummary = new AccountSummaryModel();
				accountModelSummary.setAccountName(rs.getString("accountName"));
				accountModelSummary.setOpeningAmount(rs.getDouble("openingamount"));
				accountModelSummary.setIncomeAmount(rs.getDouble("incomeamount"));
				accountModelSummary.setExpenseAmount(rs.getDouble("expenseamount"));
				accountModelSummary.setClosingAmount(rs.getDouble("closingAmount"));
				lstAccountSummary.add(accountModelSummary);
			}
			if (lstAccountSummary.size() > 0) {
				addAccountSummary(lstAccountSummary);
			}

			String query1 = " select m.name as incomeName ,sum(ifnull(i.amount,0.00)) IncomeAmount from  demo.incomemaster as m left join  incometran as i  on i.keycode=m.keycode group by m.name;";
			ResultSet rs2 = statement.executeQuery(query1);
			lstIncomesummary = new ArrayList<IncomeSummaryModel>();
			while (rs2.next()) {
				incomesummary = new IncomeSummaryModel();
				incomesummary.setIncomeName(rs2.getString("incomeName"));
				incomesummary.setIncomeAmount(rs2.getDouble("incomeAmount"));
				lstIncomesummary.add(incomesummary);
			}

			if (lstIncomesummary.size() > 0) {
				addIncomeSummary(lstIncomesummary);
			}

			String query2 = "select m.name as ExpenseName ,sum(ifnull(i.amount,0.00)) ExpenseAmount from  demo.Expensemaster as m left join  Expensetran as i  on i.keycode=m.keycode group by m.name;";
			ResultSet rs3 = statement.executeQuery(query2);
			lstExpenseSummary = new ArrayList<ExpenseSummaryModel>();
			while (rs3.next()) {
				expenseSummaryModel = new ExpenseSummaryModel();
				expenseSummaryModel.setExpenseName(rs3.getString("expenseName"));
				expenseSummaryModel.setExpenseAmount(rs3.getDouble("expenseAmount"));
				lstExpenseSummary.add(expenseSummaryModel);
			}

			if (lstExpenseSummary.size() > 0) {
				addExpenseSummary(lstExpenseSummary);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private void addIncomeSummary(ArrayList<IncomeSummaryModel> lstIncomesummary) {

		DefaultTableModel tablemodel = new DefaultTableModel();
		tablemodel.addColumn("Income Name");
		tablemodel.addColumn("Income Amount");

		for (IncomeSummaryModel data : lstIncomesummary) {
			Vector<Object> row = new Vector<Object>();
			row.add(data.getIncomeName());
			row.add(data.getIncomeAmount());
			tablemodel.addRow(row);
		}
		incomeSummarytbl = new JTable(tablemodel);
		incomeSummarytbl.setRowHeight(30);
		incomeSummarytbl.setDefaultEditor(Object.class, null);
		incomeSummaryScrollpane = new JScrollPane(incomeSummarytbl);
		incomeSummaryScrollpane.setBounds((CommonValues.screenWidth -1300) / 2, 300, 300, 500);
		viewPanel.add(incomeSummaryScrollpane);

	}

	private void addExpenseSummary(ArrayList<ExpenseSummaryModel> lstExpensesummary) {

		DefaultTableModel tablemodel = new DefaultTableModel();
		tablemodel.addColumn("Expense Name");
		tablemodel.addColumn("Expense Amount");

		for (ExpenseSummaryModel data : lstExpensesummary) {
			Vector<Object> row = new Vector<Object>();
			row.add(data.getExpenseName());
			row.add(data.getExpenseAmount());
			tablemodel.addRow(row);
		}
		expenseSummarytbl = new JTable(tablemodel);
		expenseSummarytbl.setRowHeight(30);
		expenseSummarytbl.setDefaultEditor(Object.class, null);
		expenseSummaryScrollPane = new JScrollPane(expenseSummarytbl);
		expenseSummaryScrollPane.setBounds(500, 300, 300, 500);
		viewPanel.add(expenseSummaryScrollPane);

	}

	public void addAccountSummary(ArrayList<AccountSummaryModel> lstaccountModelSummary) {

		DefaultTableModel tablemodel = new DefaultTableModel();
		tablemodel.addColumn(" ");

		int j = 0;
		Vector<Object> row = new Vector<Object>();

		for (int i = 0; i < lstaccountModelSummary.size(); i++) {

			AccountSummaryModel data = lstaccountModelSummary.get(i);

			if (j == 0) {
				if (i == 0)
					row.add("Opening Amount");

				tablemodel.addColumn(data.getAccountName());
				row.add(df.format(data.getOpeningAmount()));

				if (i == lstaccountModelSummary.size() - 1) {
					tablemodel.addRow(row);
					row = new Vector<Object>();
					j++;
					i = -1;
				}

			}

			else if (j == 1) {

				if (i == 0)
					row.add("Income Amount");

				row.add(df.format(data.getIncomeAmount()));

				if (i == lstaccountModelSummary.size() - 1) {
					tablemodel.addRow(row);
					row = new Vector<Object>();
					j++;
					i = -1;
				}

			}

			else if (j == 2) {
				if (i == 0)
					row.add("Expense Amount");

				row.add(df.format(data.getExpenseAmount()));

				if (i == lstaccountModelSummary.size() - 1) {
					tablemodel.addRow(row);
					row = new Vector<Object>();
					j++;
					i = -1;
				}

			}

			else if (j == 3) {

				if (i == 0)
					row.add("Closing Amount");

				row.add(df.format(data.getClosingAmount()));

				if (i == lstaccountModelSummary.size() - 1) {
					tablemodel.addRow(row);
					row = new Vector<Object>();

				}

			}

		}

		accountSummaryTable = new JTable(tablemodel);

		accountSummaryTable.setRowHeight(40);
		accountSummaryTable.setDefaultEditor(Object.class, null);

		for (int i = 0; i < lstaccountModelSummary.size(); i++) {
			TableColumn name = accountSummaryTable.getColumnModel().getColumn(i);
			name.setPreferredWidth(70);
		}
//		TableColumn trandate = accountSummaryTable.getColumnModel().getColumn(1);
//		trandate.setPreferredWidth(10);
//		TableColumn amount = accountSummaryTable.getColumnModel().getColumn(2);
//		amount.setPreferredWidth(5);

//		TableColumn description = accountSummaryTable.getColumnModel().getColumn(3);
//		description.setPreferredWidth(500);

//		TableColumn trankey = accountSummaryTable.getColumnModel().getColumn(4);
//		trankey.setPreferredWidth(5);
//		accountSummaryTable.setBounds((CommonValues.screenWidth-1000)/2,100, 1000, 500);
//		

		accountSummaryScrollPane = new JScrollPane(accountSummaryTable);
		accountSummaryScrollPane.setBounds((CommonValues.screenWidth - 1300) / 2, 100, 1300, 185);

		viewPanel.add(accountSummaryScrollPane);

	}
}
