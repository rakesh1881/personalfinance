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
import com.personalfinance.model.AccountMasterModel;
import com.toedter.calendar.JDateChooser;

public class AccountTranEditScreen extends JPanel {
	
	private static final long serialVersionUID = 1L;
	public JLabel accountNameLbl, datelbl,descriptionLbl,amountLbl;
	public JComboBox<String> accountNameComboBox;
	JDateChooser datechooser;
	public JTextField amountTxtBox,descriptionTxtBox;
	
	AccountMasterModel accountModel;
	ArrayList<AccountMasterModel> lstaccountModel;
	Connection connection;
	DbConnection dbconn = new DbConnection();
	String trankeycode;

	int x = 500, y = 200, lblwidth = 100, height = 20, txtwidth = 200, combowidth = 75;

	public AccountTranEditScreen() {

		setBounds(CommonValues.contentPanelX, CommonValues.contentPanelY, CommonValues.screenWidth,
				CommonValues.screenHeight);
		createMenus();

	}

	public void updateValues() {
		try {
			
			//LAST WORK
			connection = dbconn.createTranConnection();
			
			String query ="select keycode from demo.accountmaster where name='"+accountNameComboBox.getSelectedItem()+"'";
			Statement statement  = connection.createStatement();
			ResultSet rs1 = statement.executeQuery(query);
			if(rs1.next()) {
				
				String query1 = "update accounttran set description='" + descriptionTxtBox.getText().toString() + "', "
						+ "openingamount='" +amountTxtBox.getText() + "',keycode='"+rs1.getString("keycode")+"'  WHERE trankey='" + trankeycode + "'";

				PreparedStatement preparestatment = connection.prepareStatement(query1);
				preparestatment.executeUpdate();

				JOptionPane.showMessageDialog(getParent(), "Updated Successfully");
			
			}else {
				JOptionPane.showMessageDialog(getParent(), "NOT Updated");
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(getParent(), e.getMessage());
		}
		catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(getParent(), "Empty List");
		}
		
		
	}

	public void setValuestoUpdateAccount(String trankey) {
		try {

			this.trankeycode=trankey;
			
				connection = dbconn.createTranConnection();
				String query = "select i.name,t.trandate,t.openingamount,t.description from accounttran t left join demo.accountmaster as i on i.keyCode=t.keycode where t.trankey='"+trankey+"' order by trandate;";
				Statement statement = connection.createStatement();
				ResultSet rs1 = statement.executeQuery(query);
				if (rs1.next()) {
					accountNameComboBox.setSelectedItem(rs1.getString("name"));
					datechooser.setDate(rs1.getDate("trandate"));
					descriptionTxtBox.setText(rs1.getString("description"));
					amountTxtBox.setText(rs1.getString("openingamount"));
				}

			

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public AccountMasterModel setValuesForAccountModel() {

		accountModel = new AccountMasterModel();
		if (!amountTxtBox.getText().toString().equalsIgnoreCase(""))
			accountModel.setAccountName(amountTxtBox.getText());
		else
			JOptionPane.showMessageDialog(getParent(), "AccountName is Empty");

		if (accountNameComboBox.getSelectedItem().toString().equalsIgnoreCase("YES"))
			accountModel.setActive("Y");
		else
			accountModel.setActive("N");

		return accountModel;

	}

	public void clearDetailsReqFocusToText() {
		amountTxtBox.setText("");
		accountNameComboBox.setSelectedItem("YES");
		amountTxtBox.requestFocus();
	}

	public void closeThisPanelOpenTheRequestedPanel(JPanel panel) {

	}

	public void setObjectsVisibleFalse() {

		accountNameLbl.setVisible(false);
		datelbl.setVisible(false);
		amountTxtBox.setVisible(false);
		accountNameComboBox.setVisible(false);

	}

	public void setObjectsVisibleTrue() {

		accountNameLbl.setVisible(true);
		datelbl.setVisible(true);
		amountTxtBox.setVisible(true);
		accountNameComboBox.setVisible(true);

	}

	public void setObjectsEnabledFalse() {

		datelbl.setEnabled(false);
		amountTxtBox.setEnabled(false);
		accountNameLbl.setEnabled(false);
		accountNameComboBox.setEnabled(false);
	}

	public void setObjectsEnabledTrue() {

		datelbl.setEnabled(true);
		amountTxtBox.setEnabled(true);
		accountNameLbl.setEnabled(true);
		accountNameComboBox.setEnabled(true);

	}

	public void createMenus() {

		accountNameLbl = new JLabel("Account Name");
		accountNameLbl.setBounds(x, y, lblwidth, height);
		add(accountNameLbl);

		datelbl = new JLabel("Date");
		datelbl.setBounds(x, y += 50, lblwidth, height);
		add(datelbl);
		
		descriptionLbl = new JLabel("Description");
		descriptionLbl.setBounds(x, y +=50, lblwidth, height);
		add(descriptionLbl);
		
		
		amountLbl = new JLabel("Amount");
		amountLbl.setBounds(x, y +=50, lblwidth, height);
		add(amountLbl);

		y=200;
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
		
		
		descriptionTxtBox=  new JTextField();
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
			e.printStackTrace();
		}

	}

}
