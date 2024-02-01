package com.personalfinance.form.Accounttran;

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
import com.personalfinance.model.AccountTranModel;
import com.toedter.calendar.JDateChooser;

public class AccountTranScreen extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel accountNameLbl, datelbl, descriptionLbl, openingAmountlbl;
	public JComboBox<String> accountNameComboBox;
	JDateChooser datechooser;
	public JTextField amountTxtBox, descriptionTxtBox;

	AccountTranModel accountModel;
	ArrayList<AccountTranModel> lstaccountModel;
	Connection connection;
	DbConnection dbconn = new DbConnection();

	int x = 500, y = 200, lblwidth = 100, height = 20, txtwidth = 200, combowidth = 75;

	public AccountTranScreen() {

		setBounds(CommonValues.contentPanelX, CommonValues.contentPanelY, CommonValues.screenWidth,
				CommonValues.screenHeight);
		createMenus();

	}

	public void saveAccount() {
		try {

			if (amountTxtBox.getText().toString().equalsIgnoreCase("")
					|| Double.valueOf(amountTxtBox.getText()) <= 0.0) {
				JOptionPane.showMessageDialog(getParent(), "invalid Amount");
				accountNameComboBox.requestFocus();
			} else {
				connection = dbconn.createTranConnection();
				String query = "select keycode from demo.accountmaster where name='"
						+ accountNameComboBox.getSelectedItem().toString() + "';";
				Statement statement = connection.createStatement();
				ResultSet rs1 = statement.executeQuery(query);
				if (rs1.next()) {

					String query1 = "call sp_createAccountTransaction(?,?,?,?);";

					PreparedStatement preparestatment = connection.prepareStatement(query1);

					preparestatment.setDouble(1, Double.valueOf(amountTxtBox.getText()));
					preparestatment.setString(2, descriptionTxtBox.getText());
					preparestatment.setString(3, String.valueOf(CommonValues.currentDate));
					preparestatment.setString(4, accountNameComboBox.getSelectedItem().toString());
					preparestatment.executeUpdate();
					JOptionPane.showMessageDialog(getParent(), "Saved Successfully");
					accountNameComboBox.requestFocus();

				}
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(getParent(), e.getMessage());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(getParent(), "Please Enter Number in the Field Amount");
		}

	}

	public AccountTranModel setValuesForAccountModel() {

		accountModel = new AccountTranModel();
		if (!accountNameComboBox.getSelectedItem().toString().equalsIgnoreCase(""))
			accountModel.setAccountName(accountNameComboBox.getSelectedItem().toString());
		else
			JOptionPane.showMessageDialog(getParent(), "AccountName is Empty");

//		if (amountTxtBox.getText().toString().equalsIgnoreCase("YES"))
//			accountModel.setActive("Y");
//		else
//			accountModel.setActive("N");

		return accountModel;

	}

	public void clearDetailsReqFocusToText() {
//		accountNameComboBox.setText("");
//		amountTxtBox.setSelectedItem("YES");
		accountNameComboBox.requestFocus();

	}

	public void closeThisPanelOpenTheRequestedPanel(JPanel panel) {

	}

	public void setObjectsVisibleFalse() {

		accountNameLbl.setVisible(false);
		openingAmountlbl.setVisible(false);
		accountNameComboBox.setVisible(false);
		amountTxtBox.setVisible(false);

	}

	public void setObjectsVisibleTrue() {

		accountNameLbl.setVisible(true);
		openingAmountlbl.setVisible(true);
		accountNameComboBox.setVisible(true);
		amountTxtBox.setVisible(true);

	}

	public void setObjectsEnabledFalse() {

		openingAmountlbl.setEnabled(false);
		accountNameComboBox.setEnabled(false);
		accountNameLbl.setEnabled(false);
		amountTxtBox.setEnabled(false);
	}

	public void setObjectsEnabledTrue() {

		openingAmountlbl.setEnabled(true);
		accountNameComboBox.setEnabled(true);
		accountNameLbl.setEnabled(true);
		amountTxtBox.setEnabled(true);

	}

	public void createMenus() {

		accountNameLbl = new JLabel("Account Name");
		accountNameLbl.setBounds(x, y, lblwidth, height);
		add(accountNameLbl);

		datelbl = new JLabel("Date");
		datelbl.setBounds(x, y += 50, lblwidth, height);
		add(datelbl);

		descriptionLbl = new JLabel("Description");
		descriptionLbl.setBounds(x, y += 50, lblwidth, height);
		add(descriptionLbl);

		openingAmountlbl = new JLabel("Opening Amount");
		openingAmountlbl.setBounds(x, y += 50, lblwidth, height);
		add(openingAmountlbl);

		y = 200;
		accountNameComboBox = new JComboBox<>();
		accountNameComboBox.setBounds(x += lblwidth + 100, y, txtwidth, height);
		getaccountdetails(accountNameComboBox);
		accountNameComboBox.requestFocus();
		add(accountNameComboBox);

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

	}

	private void getaccountdetails(JComboBox<String> accountNameComboBox) {
		try {
			connection = dbconn.createTranConnection();
			String query = "select name as accountname from demo.accountmaster;";
			Statement statement = connection.createStatement();
			ResultSet rs1 = statement.executeQuery(query);
			while (rs1.next()) {
				accountNameComboBox.addItem(rs1.getString("accountName"));
			}
		} catch (Exception e) {

		}

	}
}
