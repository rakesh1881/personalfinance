package com.personalfinance.form.expensemaster;

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
import com.personalfinance.model.ExpenseMasterModel;

public class ExpenseAddScreen extends JPanel {

	public JLabel expenseNameLbl, ActiveLbl;
	public JTextField expenseNametxtBox;
	public JComboBox<String> yesNoCombo;
	ExpenseMasterModel expenseModel;
	ArrayList<ExpenseMasterModel> lstexpenseModel;
	Connection connection;
	DbConnection dbconn = new DbConnection();

	int x = 500, y = 200, lblwidth = 100, height = 20, txtwidth = 200, combowidth = 75;

	public ExpenseAddScreen() {

		setBounds(CommonValues.contentPanelX, CommonValues.contentPanelY, CommonValues.screenWidth,
				CommonValues.screenHeight);
		createMenus();

	}

	public void saveExpense() {
		try {

			if (expenseNametxtBox.getText().toString().equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(getParent(), "ExpenseName is empty");
				expenseNametxtBox.requestFocus();
			} else {
				connection = dbconn.createMasterConnection();
				String query = "select convert (max(substring(keycode,2,1))+1,char) as expensenum from expensemaster;";
				Statement statement = connection.createStatement();
				ResultSet rs1 = statement.executeQuery(query);
				if (rs1.next()) {
					String keycode = "E" + rs1.getString("expensenum");
					String Yesno = "Y";
					if (!yesNoCombo.getSelectedItem().toString().equalsIgnoreCase("yes")) {
						Yesno = "N";
					}

					String query1 = "insert into expenseMaster (Keycode,name,active,createddate,createdtime) values (?,?,?,curdate(),now());";

					PreparedStatement preparestatment = connection.prepareStatement(query1);

					preparestatment.setString(1, keycode);
					preparestatment.setString(2, expenseNametxtBox.getText().toString());
					preparestatment.setString(3, Yesno);
					preparestatment.executeUpdate();
					JOptionPane.showMessageDialog(getParent(), "Saved Successfully");
					expenseNametxtBox.setText("");
					expenseNametxtBox.requestFocus();
					
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ExpenseMasterModel setValuesForExpenseModel() {

		expenseModel = new ExpenseMasterModel();
		if (!expenseNametxtBox.getText().toString().equalsIgnoreCase(""))
			expenseModel.setExpenseName(expenseNametxtBox.getText());
		else
			JOptionPane.showMessageDialog(getParent(), "ExpenseName is Empty");

		if (yesNoCombo.getSelectedItem().toString().equalsIgnoreCase("YES"))
			expenseModel.setActive("Y");
		else
			expenseModel.setActive("N");

		return expenseModel;

	}

	public void clearDetailsReqFocusToText() {
		expenseNametxtBox.setText("");
		yesNoCombo.setSelectedItem("YES");
		expenseNametxtBox.requestFocus();

	}

	public void closeThisPanelOpenTheRequestedPanel(JPanel panel) {

	}

	public void setObjectsVisibleFalse() {

		expenseNameLbl.setVisible(false);
		ActiveLbl.setVisible(false);
		expenseNametxtBox.setVisible(false);
		yesNoCombo.setVisible(false);

	}

	public void setObjectsVisibleTrue() {

		expenseNameLbl.setVisible(true);
		ActiveLbl.setVisible(true);
		expenseNametxtBox.setVisible(true);
		yesNoCombo.setVisible(true);

	}

	public void setObjectsEnabledFalse() {

		ActiveLbl.setEnabled(false);
		expenseNametxtBox.setEnabled(false);
		expenseNameLbl.setEnabled(false);
		yesNoCombo.setEnabled(false);
	}

	public void setObjectsEnabledTrue() {

		ActiveLbl.setEnabled(true);
		expenseNametxtBox.setEnabled(true);
		expenseNameLbl.setEnabled(true);
		yesNoCombo.setEnabled(true);

	}

	public void createMenus() {

		expenseNameLbl = new JLabel("Expense Name");
		expenseNameLbl.setBounds(x, y, lblwidth, height);
		add(expenseNameLbl);

		ActiveLbl = new JLabel("Active");
		ActiveLbl.setBounds(x, y + 50, lblwidth, height);
		add(ActiveLbl);

		expenseNametxtBox = new JTextField("");
		expenseNametxtBox.setBounds(x += lblwidth + 100, y, txtwidth, height);
		expenseNametxtBox.requestFocus();
		add(expenseNametxtBox);

		yesNoCombo = new JComboBox<>();
		yesNoCombo.setBounds(x, y += 50, combowidth, height);
		yesNoCombo.addItem("YES");
		yesNoCombo.addItem("NO");
		add(yesNoCombo);

	}

}
