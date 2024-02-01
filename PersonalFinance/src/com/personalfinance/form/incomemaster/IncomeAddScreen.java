package com.personalfinance.form.incomemaster;

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
import com.personalfinance.model.IncomeMasterModel;

public class IncomeAddScreen extends JPanel {

	public JLabel incomeNameLbl, ActiveLbl;
	public JTextField incomeNametxtBox;
	public JComboBox<String> yesNoCombo;
	IncomeMasterModel incomeModel;
	ArrayList<IncomeMasterModel> lstincomeModel;
	Connection connection;
	DbConnection dbconn = new DbConnection();

	int x = 500, y = 200, lblwidth = 100, height = 20, txtwidth = 200, combowidth = 75;

	public IncomeAddScreen() {

		setBounds(CommonValues.contentPanelX, CommonValues.contentPanelY, CommonValues.screenWidth,
				CommonValues.screenHeight);
		createMenus();

	}

	public void saveIncome() {
		try {

			if (incomeNametxtBox.getText().toString().equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(getParent(), "IncomeName is empty");
				incomeNametxtBox.requestFocus();
			} else {
				connection = dbconn.createMasterConnection();
				String query = "select convert (max(substring(keycode,2,1))+1,char) as incomenum from incomemaster;";
				Statement statement = connection.createStatement();
				ResultSet rs1 = statement.executeQuery(query);
				if (rs1.next()) {
					String keycode = "I" + rs1.getString("incomenum");
					String Yesno = "Y";
					if (!yesNoCombo.getSelectedItem().toString().equalsIgnoreCase("yes")) {
						Yesno = "N";
					}

					String query1 = "insert into incomeMaster (Keycode,name,active,createddate,createdtime) values (?,?,?,curdate(),now());";

					PreparedStatement preparestatment = connection.prepareStatement(query1);

					preparestatment.setString(1, keycode);
					preparestatment.setString(2, incomeNametxtBox.getText().toString());
					preparestatment.setString(3, Yesno);
					preparestatment.executeUpdate();
					JOptionPane.showMessageDialog(getParent(), "Saved Successfully");
					incomeNametxtBox.setText("");
					incomeNametxtBox.requestFocus();
					
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public IncomeMasterModel setValuesForIncomeModel() {

		incomeModel = new IncomeMasterModel();
		if (!incomeNametxtBox.getText().toString().equalsIgnoreCase(""))
			incomeModel.setIncomeName(incomeNametxtBox.getText());
		else
			JOptionPane.showMessageDialog(getParent(), "IncomeName is Empty");

		if (yesNoCombo.getSelectedItem().toString().equalsIgnoreCase("YES"))
			incomeModel.setActive("Y");
		else
			incomeModel.setActive("N");

		return incomeModel;

	}

	public void clearDetailsReqFocusToText() {
		incomeNametxtBox.setText("");
		yesNoCombo.setSelectedItem("YES");
		incomeNametxtBox.requestFocus();

	}

	public void closeThisPanelOpenTheRequestedPanel(JPanel panel) {

	}

	public void setObjectsVisibleFalse() {

		incomeNameLbl.setVisible(false);
		ActiveLbl.setVisible(false);
		incomeNametxtBox.setVisible(false);
		yesNoCombo.setVisible(false);

	}

	public void setObjectsVisibleTrue() {

		incomeNameLbl.setVisible(true);
		ActiveLbl.setVisible(true);
		incomeNametxtBox.setVisible(true);
		yesNoCombo.setVisible(true);

	}

	public void setObjectsEnabledFalse() {

		ActiveLbl.setEnabled(false);
		incomeNametxtBox.setEnabled(false);
		incomeNameLbl.setEnabled(false);
		yesNoCombo.setEnabled(false);
	}

	public void setObjectsEnabledTrue() {

		ActiveLbl.setEnabled(true);
		incomeNametxtBox.setEnabled(true);
		incomeNameLbl.setEnabled(true);
		yesNoCombo.setEnabled(true);

	}

	public void createMenus() {

		incomeNameLbl = new JLabel("Income Name");
		incomeNameLbl.setBounds(x, y, lblwidth, height);
		add(incomeNameLbl);

		ActiveLbl = new JLabel("Active");
		ActiveLbl.setBounds(x, y + 50, lblwidth, height);
		add(ActiveLbl);

		incomeNametxtBox = new JTextField("");
		incomeNametxtBox.setBounds(x += lblwidth + 100, y, txtwidth, height);
		incomeNametxtBox.requestFocus();
		add(incomeNametxtBox);

		yesNoCombo = new JComboBox<>();
		yesNoCombo.setBounds(x, y += 50, combowidth, height);
		yesNoCombo.addItem("YES");
		yesNoCombo.addItem("NO");
		add(yesNoCombo);

	}

}
