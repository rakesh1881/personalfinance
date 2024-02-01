package com.personalfinance.form.accountmaster;

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

public class AccountEditScreen extends JPanel {
	
	private JLabel accountNameLbl, ActiveLbl;
	public JTextField accountNametxtBox;
	public JComboBox<String> yesNoCombo;
	AccountMasterModel accountModel;
	ArrayList<AccountMasterModel> lstaccountModel;
	Connection connection;
	DbConnection dbconn = new DbConnection();
	String accountKeycode;

	int x = 500, y = 200, lblwidth = 100, height = 20, txtwidth = 200, combowidth = 75;

	public AccountEditScreen() {

		setBounds(CommonValues.contentPanelX, CommonValues.contentPanelY, CommonValues.screenWidth,
				CommonValues.screenHeight);
		createMenus();

	}

	public void updateValues() {
		try {
			connection = dbconn.createMasterConnection();
			String yesno = "Y";
			if (!yesNoCombo.getSelectedItem().toString().equalsIgnoreCase("yes")) {
				yesno = "N";
			}
				String query1 = "update accountmaster set name='" + accountNametxtBox.getText().toString() + "', "
						+ "active='" +yesno + "'  WHERE keycode='" + accountKeycode + "'";

				PreparedStatement preparestatment = connection.prepareStatement(query1);
				preparestatment.executeUpdate();
				JOptionPane.showMessageDialog(getParent(), "Updated Successfully");
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

	public void setValuestoUpdateAccount(String keycode) {
		try {

			this.accountKeycode=keycode;
			
				connection = dbconn.createMasterConnection();
				String query = "select name,active from accountmaster where keycode='" + keycode + "';";
				Statement statement = connection.createStatement();
				ResultSet rs1 = statement.executeQuery(query);
				if (rs1.next()) {
					String Yesno = rs1.getString("active");
					if (Yesno.equalsIgnoreCase("Y")) {
						yesNoCombo.setSelectedItem("YES");
					}else
						yesNoCombo.setSelectedItem("NO");
					accountNametxtBox.setText(rs1.getString("name"));
				}

			

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public AccountMasterModel setValuesForAccountModel() {

		accountModel = new AccountMasterModel();
		if (!accountNametxtBox.getText().toString().equalsIgnoreCase(""))
			accountModel.setAccountName(accountNametxtBox.getText());
		else
			JOptionPane.showMessageDialog(getParent(), "AccountName is Empty");

		if (yesNoCombo.getSelectedItem().toString().equalsIgnoreCase("YES"))
			accountModel.setActive("Y");
		else
			accountModel.setActive("N");

		return accountModel;

	}

	public void clearDetailsReqFocusToText() {
		accountNametxtBox.setText("");
		yesNoCombo.setSelectedItem("YES");
		accountNametxtBox.requestFocus();

	}

	public void closeThisPanelOpenTheRequestedPanel(JPanel panel) {

	}

	public void setObjectsVisibleFalse() {

		accountNameLbl.setVisible(false);
		ActiveLbl.setVisible(false);
		accountNametxtBox.setVisible(false);
		yesNoCombo.setVisible(false);

	}

	public void setObjectsVisibleTrue() {

		accountNameLbl.setVisible(true);
		ActiveLbl.setVisible(true);
		accountNametxtBox.setVisible(true);
		yesNoCombo.setVisible(true);

	}

	public void setObjectsEnabledFalse() {

		ActiveLbl.setEnabled(false);
		accountNametxtBox.setEnabled(false);
		accountNameLbl.setEnabled(false);
		yesNoCombo.setEnabled(false);
	}

	public void setObjectsEnabledTrue() {

		ActiveLbl.setEnabled(true);
		accountNametxtBox.setEnabled(true);
		accountNameLbl.setEnabled(true);
		yesNoCombo.setEnabled(true);

	}

	public void createMenus() {

		accountNameLbl = new JLabel("Account Name");
		accountNameLbl.setBounds(x, y, lblwidth, height);
		add(accountNameLbl);

		ActiveLbl = new JLabel("Active");
		ActiveLbl.setBounds(x, y + 50, lblwidth, height);
		add(ActiveLbl);

		accountNametxtBox = new JTextField("");
		accountNametxtBox.setBounds(x += lblwidth + 100, y, txtwidth, height);
		accountNametxtBox.requestFocus();
		add(accountNametxtBox);

		yesNoCombo = new JComboBox<>();
		yesNoCombo.setBounds(x, y += 50, combowidth, height);
		yesNoCombo.addItem("YES");
		yesNoCombo.addItem("NO");
		add(yesNoCombo);

	}

}
