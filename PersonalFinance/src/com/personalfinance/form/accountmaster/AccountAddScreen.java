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

public class AccountAddScreen extends JPanel {

	public JLabel accountNameLbl, ActiveLbl;
	public JTextField accountNametxtBox;
	public JComboBox<String> yesNoCombo;
	AccountMasterModel accountModel;
	ArrayList<AccountMasterModel> lstaccountModel;
	Connection connection;
	DbConnection dbconn = new DbConnection();

	int x = 500, y = 200, lblwidth = 100, height = 20, txtwidth = 200, combowidth = 75;

	public AccountAddScreen() {

		setBounds(CommonValues.contentPanelX, CommonValues.contentPanelY, CommonValues.screenWidth,
				CommonValues.screenHeight);
		createMenus();

	}

	public void saveAccount() {
		try {

			if (accountNametxtBox.getText().toString().equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(getParent(), "AccountName is empty");
				accountNametxtBox.requestFocus();
			} else {
				connection = dbconn.createMasterConnection();
				String query = "select convert (max(substring(keycode,2,1))+1,char) as accountnum from accountmaster;";
				Statement statement = connection.createStatement();
				ResultSet rs1 = statement.executeQuery(query);
				if (rs1.next()) {
					String keycode = "A" + rs1.getString("accountnum");
					String Yesno = "Y";
					if (!yesNoCombo.getSelectedItem().toString().equalsIgnoreCase("yes")) {
						Yesno = "N";
					}

					String query1 = "insert into accountMaster (Keycode,name,active,createddate,createdtime) values (?,?,?,curdate(),now());";

					PreparedStatement preparestatment = connection.prepareStatement(query1);

					preparestatment.setString(1, keycode);
					preparestatment.setString(2, accountNametxtBox.getText().toString());
					preparestatment.setString(3, Yesno);
					preparestatment.executeUpdate();
					JOptionPane.showMessageDialog(getParent(), "Saved Successfully");
					accountNametxtBox.setText("");
					accountNametxtBox.requestFocus();
					
				}
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
