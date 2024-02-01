package com.personalfinance.form.incometran;

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
import com.personalfinance.common.Listeners;
import com.personalfinance.model.IncomeMasterModel;
import com.toedter.calendar.JDateChooser;

public class IncomeTranScreen extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel incomeNameLbl, datelbl, descriptionLbl, amountLbl,incomeAccountlbl;
	public JComboBox<String> incomeNameComboBox,accountNameComboBox;
	JDateChooser datechooser;
	public JTextField amountTxtBox, descriptionTxtBox;

	IncomeMasterModel incomeModel;
	IncomeTranCommonScreen incomeCommonScreen;
	ArrayList<IncomeMasterModel> lstincomeModel;
	Connection connection;
	DbConnection dbconn = new DbConnection();
	Listeners listeners = new Listeners();

	int x = 500, y = 200, lblwidth = 100, height = 20, txtwidth = 200, combowidth = 75;

	public IncomeTranScreen() {

		setBounds(CommonValues.contentPanelX, CommonValues.contentPanelY, CommonValues.screenWidth,
				CommonValues.screenHeight);
		createMenus();
		listeners();

	}

	private void listeners() {
		
		listeners.changeFocustoNext(incomeNameComboBox, descriptionTxtBox);
		listeners.changeFocustoNext(descriptionTxtBox, amountTxtBox);
		listeners.changeFocustoNext(amountTxtBox, accountNameComboBox);
//		listeners.changeFocustoNext( accountNameComboBox,incomeCommonScreen.getBtnSave());
		
		
		
	}

	public void saveIncome() {
		try {

			if (amountTxtBox.getText().toString().equalsIgnoreCase("")
					|| Double.valueOf(amountTxtBox.getText()) <= 0.0) {
				JOptionPane.showMessageDialog(getParent(), "invalid Amount");
				incomeNameComboBox.requestFocus();
			} else {
				connection = dbconn.createTranConnection();
				String query = "select keycode from demo.incomemaster where name='"
						+ incomeNameComboBox.getSelectedItem().toString() + "';";
				Statement statement = connection.createStatement();
				ResultSet rs1 = statement.executeQuery(query);
				if (rs1.next()) {

					String query1 = "call sp_createIncomeTransaction(?,?,?,?,?);";

					PreparedStatement preparestatment = connection.prepareStatement(query1);

					preparestatment.setDouble(1, Double.valueOf(amountTxtBox.getText()));
					preparestatment.setString(2, descriptionTxtBox.getText());
					preparestatment.setString(3, String.valueOf(CommonValues.currentDate));
					preparestatment.setString(4, incomeNameComboBox.getSelectedItem().toString());
					preparestatment.setString(5, accountNameComboBox.getSelectedItem().toString());
					preparestatment.executeUpdate();
					JOptionPane.showMessageDialog(getParent(), "Saved Successfully");
					incomeNameComboBox.requestFocus();

				}
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(getParent(), e.getMessage());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(getParent(), "Please Enter Number in the Field Amount");
		}

	}

	public IncomeMasterModel setValuesForIncomeModel() {

		incomeModel = new IncomeMasterModel();
		if (!incomeNameComboBox.getSelectedItem().toString().equalsIgnoreCase(""))
			incomeModel.setIncomeName(incomeNameComboBox.getSelectedItem().toString());
		else
			JOptionPane.showMessageDialog(getParent(), "IncomeName is Empty");

//		if (amountTxtBox.getText().toString().equalsIgnoreCase("YES"))
//			incomeModel.setActive("Y");
//		else
//			incomeModel.setActive("N");

		return incomeModel;

	}

	public void clearDetailsReqFocusToText() {
//		incomeNameComboBox.setText("");
//		amountTxtBox.setSelectedItem("YES");
		incomeNameComboBox.requestFocus();

	}

	public void closeThisPanelOpenTheRequestedPanel(JPanel panel) {

	}

	public void setObjectsVisibleFalse() {

		incomeNameLbl.setVisible(false);
		amountLbl.setVisible(false);
		incomeNameComboBox.setVisible(false);
		amountTxtBox.setVisible(false);

	}

	public void setObjectsVisibleTrue() {

		incomeNameLbl.setVisible(true);
		amountLbl.setVisible(true);
		incomeNameComboBox.setVisible(true);
		amountTxtBox.setVisible(true);

	}

	public void setObjectsEnabledFalse() {

		amountLbl.setEnabled(false);
		incomeNameComboBox.setEnabled(false);
		incomeNameLbl.setEnabled(false);
		amountTxtBox.setEnabled(false);
	}

	public void setObjectsEnabledTrue() {

		amountLbl.setEnabled(true);
		incomeNameComboBox.setEnabled(true);
		incomeNameLbl.setEnabled(true);
		amountTxtBox.setEnabled(true);

	}

	public void createMenus() {

		incomeNameLbl = new JLabel("Income Name");
		incomeNameLbl.setBounds(x, y, lblwidth, height);
		add(incomeNameLbl);

		datelbl = new JLabel("Date");
		datelbl.setBounds(x, y += 50, lblwidth, height);
		add(datelbl);

		descriptionLbl = new JLabel("Description");
		descriptionLbl.setBounds(x, y += 50, lblwidth, height);
		add(descriptionLbl);

		amountLbl = new JLabel("Amount");
		amountLbl.setBounds(x, y += 50, lblwidth, height);
		add(amountLbl);
		
		incomeAccountlbl = new JLabel("Income Account");
		incomeAccountlbl.setBounds(x, y += 50, lblwidth, height);
		add(incomeAccountlbl);

		y = 200;
		incomeNameComboBox = new JComboBox<>();
		incomeNameComboBox.setBounds(x += lblwidth + 100, y, txtwidth, height);
		getincomedetails(incomeNameComboBox);
		incomeNameComboBox.requestFocus();
		add(incomeNameComboBox);

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
			connection = dbconn.createMasterConnection();
			String query = "select name as incomename from incomemaster;";
			Statement statement = connection.createStatement();
			ResultSet rs1 = statement.executeQuery(query);
			while (rs1.next()) {
				incomeNameComboBox.addItem(rs1.getString("incomeName"));
			}
		} catch (Exception e) {

		}

	}
	private void getAccountdetails(JComboBox<String> accountNameComboBox) {
		try {
			connection = dbconn.createMasterConnection();
			String query = "select name as accountname from Accountmaster;";
			Statement statement = connection.createStatement();
			ResultSet rs1 = statement.executeQuery(query);
			while (rs1.next()) {
				accountNameComboBox.addItem(rs1.getString("accountname"));
			}
		} catch (Exception e) {

		}

	}
}
