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

public class ExpenseEditScreen extends JPanel {
	
	private JLabel expenseNameLbl, ActiveLbl;
	public JTextField expenseNametxtBox;
	public JComboBox<String> yesNoCombo;
	ExpenseMasterModel expenseModel;
	ArrayList<ExpenseMasterModel> lstexpenseModel;
	Connection connection;
	DbConnection dbconn = new DbConnection();
	String expenseKeycode;

	int x = 500, y = 200, lblwidth = 100, height = 20, txtwidth = 200, combowidth = 75;

	public ExpenseEditScreen() {

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
				String query1 = "update expensemaster set name='" + expenseNametxtBox.getText().toString() + "', "
						+ "active='" +yesno + "'  WHERE keycode='" + expenseKeycode + "'";

				PreparedStatement preparestatment = connection.prepareStatement(query1);
				preparestatment.executeUpdate();
				JOptionPane.showMessageDialog(getParent(), "Updated Successfully");
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

	public void setValuestoUpdateExpense(String keycode) {
		try {

			this.expenseKeycode=keycode;
			
				connection = dbconn.createMasterConnection();
				String query = "select name,active from expensemaster where keycode='" + keycode + "';";
				Statement statement = connection.createStatement();
				ResultSet rs1 = statement.executeQuery(query);
				if (rs1.next()) {
					String Yesno = rs1.getString("active");
					if (Yesno.equalsIgnoreCase("Y")) {
						yesNoCombo.setSelectedItem("YES");
					}else
						yesNoCombo.setSelectedItem("NO");
					expenseNametxtBox.setText(rs1.getString("name"));
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
