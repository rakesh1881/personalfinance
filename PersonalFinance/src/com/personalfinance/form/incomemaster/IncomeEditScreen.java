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

public class IncomeEditScreen extends JPanel {
	
	private JLabel incomeNameLbl, ActiveLbl;
	public JTextField incomeNametxtBox;
	public JComboBox<String> yesNoCombo;
	IncomeMasterModel incomeModel;
	ArrayList<IncomeMasterModel> lstincomeModel;
	Connection connection;
	DbConnection dbconn = new DbConnection();
	String incomeKeycode;

	int x = 500, y = 200, lblwidth = 100, height = 20, txtwidth = 200, combowidth = 75;

	public IncomeEditScreen() {

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
				String query1 = "update incomemaster set name='" + incomeNametxtBox.getText().toString() + "', "
						+ "active='" +yesno + "'  WHERE keycode='" + incomeKeycode + "'";

				PreparedStatement preparestatment = connection.prepareStatement(query1);
				preparestatment.executeUpdate();
				JOptionPane.showMessageDialog(getParent(), "Updated Successfully");
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

	public void setValuestoUpdateIncome(String keycode) {
		try {

			this.incomeKeycode=keycode;
			
				connection = dbconn.createMasterConnection();
				String query = "select name,active from incomemaster where keycode='" + keycode + "';";
				Statement statement = connection.createStatement();
				ResultSet rs1 = statement.executeQuery(query);
				if (rs1.next()) {
					String Yesno = rs1.getString("active");
					if (Yesno.equalsIgnoreCase("Y")) {
						yesNoCombo.setSelectedItem("YES");
					}else
						yesNoCombo.setSelectedItem("NO");
					incomeNametxtBox.setText(rs1.getString("name"));
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
