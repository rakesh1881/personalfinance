package com.personalfinance.form.expensetran;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.personalfinance.common.CommonValues;
import com.personalfinance.common.DbConnection;
import com.personalfinance.model.ExpenseTranModel;
import com.personalfinance.model.IncomeMasterModel;
import com.toedter.calendar.JDateChooser;

public class ExpenseTranScreen extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel expenseNameLbl, datelbl, descriptionLbl, amountLbl,expenseAccount;
	public JComboBox<String> expenseNameComboBox,accountNameComboBox;
	JDateChooser datechooser;
	public JTextField amountTxtBox, descriptionTxtBox;
	

	ExpenseTranModel expenseTranModel;
	ArrayList<ExpenseTranModel> lstexpenseModel;
	Connection connection;
	DbConnection dbconn = new DbConnection();

	int x = 500, y = 200, lblwidth = 100, height = 20, txtwidth = 200, combowidth = 75;

	public ExpenseTranScreen() {

		setBounds(CommonValues.contentPanelX, CommonValues.contentPanelY, CommonValues.screenWidth,
				CommonValues.screenHeight);
		createMenus();

	}

	public void saveIncome() {
		try {

			if (amountTxtBox.getText().toString().equalsIgnoreCase("")
					|| Double.valueOf(amountTxtBox.getText()) <= 0.0) {
				JOptionPane.showMessageDialog(getParent(), "invalid Amount");
				expenseNameComboBox.requestFocus();
			} else {
				connection = dbconn.createTranConnection();
				String query = "select keycode from demo.expensemaster where name='"
						+ expenseNameComboBox.getSelectedItem().toString() + "';";
				Statement statement = connection.createStatement();
				ResultSet rs1 = statement.executeQuery(query);
				if (rs1.next()) {

					String query1 = "call sp_createExpenseTransaction(?,?,?,?,?);";

					PreparedStatement preparestatment = connection.prepareStatement(query1);

					preparestatment.setDouble(1, Double.valueOf(amountTxtBox.getText()));
					preparestatment.setString(2, descriptionTxtBox.getText());
					preparestatment.setString(3, String.valueOf(CommonValues.currentDate));
					preparestatment.setString(4, expenseNameComboBox.getSelectedItem().toString());
					preparestatment.setString(5, accountNameComboBox.getSelectedItem().toString());
					preparestatment.executeUpdate();
					JOptionPane.showMessageDialog(getParent(), "Saved Successfully");
					expenseNameComboBox.requestFocus();

				}
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(getParent(), e.getMessage());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(getParent(), "Please Enter Number in the Field Amount");
		}

	}

	public ExpenseTranModel setValuesForIncomeModel() {

		expenseTranModel = new ExpenseTranModel();
		if (!expenseNameComboBox.getSelectedItem().toString().equalsIgnoreCase(""))
			expenseTranModel.setExpenseName(expenseNameComboBox.getSelectedItem().toString());
		else
			JOptionPane.showMessageDialog(getParent(), "IncomeName is Empty");

//		if (amountTxtBox.getText().toString().equalsIgnoreCase("YES"))
//			incomeModel.setActive("Y");
//		else
//			incomeModel.setActive("N");

		return expenseTranModel;

	}

	public void clearDetailsReqFocusToText() {
//		incomeNameComboBox.setText("");
//		amountTxtBox.setSelectedItem("YES");
		expenseNameComboBox.requestFocus();

	}

	public void closeThisPanelOpenTheRequestedPanel(JPanel panel) {

	}

	public void setObjectsVisibleFalse() {

		expenseNameLbl.setVisible(false);
		amountLbl.setVisible(false);
		expenseNameComboBox.setVisible(false);
		amountTxtBox.setVisible(false);

	}

	public void setObjectsVisibleTrue() {

		expenseNameLbl.setVisible(true);
		amountLbl.setVisible(true);
		expenseNameComboBox.setVisible(true);
		amountTxtBox.setVisible(true);

	}

	public void setObjectsEnabledFalse() {

		amountLbl.setEnabled(false);
		expenseNameComboBox.setEnabled(false);
		expenseNameLbl.setEnabled(false);
		amountTxtBox.setEnabled(false);
	}

	public void setObjectsEnabledTrue() {

		amountLbl.setEnabled(true);
		expenseNameComboBox.setEnabled(true);
		expenseNameLbl.setEnabled(true);
		amountTxtBox.setEnabled(true);

	}

	public void createMenus() {

		expenseNameLbl = new JLabel("Expense Name");
		expenseNameLbl.setBounds(x, y, lblwidth, height);
		add(expenseNameLbl);

		datelbl = new JLabel("Date");
		datelbl.setBounds(x, y += 50, lblwidth, height);
		add(datelbl);

		descriptionLbl = new JLabel("Description");
		descriptionLbl.setBounds(x, y += 50, lblwidth, height);
		add(descriptionLbl);

		amountLbl = new JLabel("Amount");
		amountLbl.setBounds(x, y += 50, lblwidth, height);
		add(amountLbl);
		
		expenseAccount = new JLabel("Expense Account");
		expenseAccount.setBounds(x, y += 50, lblwidth, height);
		add(expenseAccount);

		y = 200;
		expenseNameComboBox = new JComboBox<>();
		expenseNameComboBox.setBounds(x += lblwidth + 100, y, txtwidth, height);
		getincomedetails(expenseNameComboBox);
		expenseNameComboBox.requestFocus();
		add(expenseNameComboBox);

		datechooser = new JDateChooser();
		datechooser.setBounds(x, y += 50, txtwidth, height);
		datechooser.setDate(CommonValues.currentDate);
		datechooser.setEnabled(false);
		add(datechooser);

		descriptionTxtBox = new JTextField();
		descriptionTxtBox.setBounds(x, y += 50, txtwidth, height);
		add(descriptionTxtBox);

		amountTxtBox = new JTextField();
		amountTxtBox.setBounds(x, y += 50, txtwidth, height);
		add(amountTxtBox);
		
		
		accountNameComboBox = new JComboBox<>();
		accountNameComboBox.setBounds(x , y+=50, txtwidth, height);
		getAccountdetails(accountNameComboBox);
		add(accountNameComboBox);

	}

	private void getincomedetails(JComboBox<String> incomeNameComboBox) {
		try {
			connection = dbconn.createTranConnection();
			String query = "select name as expensename from demo.expensemaster;";
			Statement statement = connection.createStatement();
			ResultSet rs1 = statement.executeQuery(query);
			while (rs1.next()) {
				incomeNameComboBox.addItem(rs1.getString("expensename"));
			}
		} catch (Exception e) {

		}

	}
	private void getAccountdetails(JComboBox<String> accountNameComboBox) {
		try {
			connection = dbconn.createTranConnection();
			String query = "select name as accountname from demo.Accountmaster;";
			Statement statement = connection.createStatement();
			ResultSet rs1 = statement.executeQuery(query);
			while (rs1.next()) {
				accountNameComboBox.addItem(rs1.getString("accountname"));
			}
		} catch (Exception e) {

		}

	}
	
}
