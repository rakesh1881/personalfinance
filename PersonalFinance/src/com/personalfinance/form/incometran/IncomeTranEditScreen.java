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
import com.personalfinance.model.IncomeMasterModel;
import com.toedter.calendar.JDateChooser;

public class IncomeTranEditScreen extends JPanel {
	
	private static final long serialVersionUID = 1L;
	public JLabel incomeNameLbl, datelbl,descriptionLbl,amountLbl,incomeAccountlbl;
	public JComboBox<String> incomeNameComboBox,accountNameComboBox;
	JDateChooser datechooser;
	public JTextField amountTxtBox,descriptionTxtBox;
	
	IncomeMasterModel incomeModel;
	ArrayList<IncomeMasterModel> lstincomeModel;
	Connection connection;
	DbConnection dbconn = new DbConnection();
	String trankeycode;

	int x = 500, y = 200, lblwidth = 100, height = 20, txtwidth = 200, combowidth = 75;

	public IncomeTranEditScreen() {

		setBounds(CommonValues.contentPanelX, CommonValues.contentPanelY, CommonValues.screenWidth,
				CommonValues.screenHeight);
		createMenus();

	}

	public void updateValues() {
		try {
			
			//LAST WORK
			connection = dbconn.createTranConnection();
			
			String query ="select keycode from demo.incomemaster where name='"+incomeNameComboBox.getSelectedItem()+"'";
			Statement statement  = connection.createStatement();
			ResultSet rs1 = statement.executeQuery(query);
			if(rs1.next()) {
				
				String query1 = "update incometran set description='" + descriptionTxtBox.getText().toString() + "', "
						+ "amount='" +amountTxtBox.getText() + "',keycode='"+rs1.getString("keycode")+"'  WHERE trankey='" + trankeycode + "'";

				PreparedStatement preparestatment = connection.prepareStatement(query1);
				preparestatment.executeUpdate();

				JOptionPane.showMessageDialog(getParent(), "Updated Successfully");
			
			}else {
				JOptionPane.showMessageDialog(getParent(), "NOT Updated");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public void setValuestoUpdateIncome(String trankey) {
		try {

			this.trankeycode=trankey;
			
				connection = dbconn.createTranConnection();
				String query = "select i.name,t.trandate,t.amount,t.description from incometran t left join demo.incomemaster as i on i.keyCode=t.keycode where t.trankey='"+trankey+"' order by trandate;";
				Statement statement = connection.createStatement();
				ResultSet rs1 = statement.executeQuery(query);
				if (rs1.next()) {
					incomeNameComboBox.setSelectedItem(rs1.getString("name"));
					datechooser.setDate(rs1.getDate("trandate"));
					descriptionTxtBox.setText(rs1.getString("description"));
					amountTxtBox.setText(rs1.getString("amount"));
				}

			

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public IncomeMasterModel setValuesForIncomeModel() {

		incomeModel = new IncomeMasterModel();
		if (!amountTxtBox.getText().toString().equalsIgnoreCase(""))
			incomeModel.setIncomeName(amountTxtBox.getText());
		else
			JOptionPane.showMessageDialog(getParent(), "IncomeName is Empty");

		if (incomeNameComboBox.getSelectedItem().toString().equalsIgnoreCase("YES"))
			incomeModel.setActive("Y");
		else
			incomeModel.setActive("N");

		return incomeModel;

	}

	public void clearDetailsReqFocusToText() {
		amountTxtBox.setText("");
		incomeNameComboBox.setSelectedItem("YES");
		amountTxtBox.requestFocus();
	}

	public void closeThisPanelOpenTheRequestedPanel(JPanel panel) {

	}

	public void setObjectsVisibleFalse() {

		incomeNameLbl.setVisible(false);
		datelbl.setVisible(false);
		amountTxtBox.setVisible(false);
		incomeNameComboBox.setVisible(false);

	}

	public void setObjectsVisibleTrue() {

		incomeNameLbl.setVisible(true);
		datelbl.setVisible(true);
		amountTxtBox.setVisible(true);
		incomeNameComboBox.setVisible(true);

	}

	public void setObjectsEnabledFalse() {

		datelbl.setEnabled(false);
		amountTxtBox.setEnabled(false);
		incomeNameLbl.setEnabled(false);
		incomeNameComboBox.setEnabled(false);
	}

	public void setObjectsEnabledTrue() {

		datelbl.setEnabled(true);
		amountTxtBox.setEnabled(true);
		incomeNameLbl.setEnabled(true);
		incomeNameComboBox.setEnabled(true);

	}

	public void createMenus() {

		incomeNameLbl = new JLabel("Income Name");
		incomeNameLbl.setBounds(x, y, lblwidth, height);
		add(incomeNameLbl);

		datelbl = new JLabel("Date");
		datelbl.setBounds(x, y += 50, lblwidth, height);
		add(datelbl);
		
		descriptionLbl = new JLabel("Description");
		descriptionLbl.setBounds(x, y +=50, lblwidth, height);
		add(descriptionLbl);
		
		
		amountLbl = new JLabel("Amount");
		amountLbl.setBounds(x, y +=50, lblwidth, height);
		add(amountLbl);

		
		incomeAccountlbl = new JLabel("Income Account");
		incomeAccountlbl.setBounds(x, y += 50, lblwidth, height);
		add(incomeAccountlbl);
		
		
		y=200;
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
		
		
		descriptionTxtBox=  new JTextField();
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
			String query = "select name as incomename from demo.incomemaster;";
			Statement statement = connection.createStatement();
			ResultSet rs1 = statement.executeQuery(query);
			while (rs1.next()) {
				incomeNameComboBox.addItem(rs1.getString("incomeName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
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
