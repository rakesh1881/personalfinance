package com.personalfinance.form.expensetran;

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
import com.personalfinance.model.ExpenseTranModel;
import com.personalfinance.model.IncomeMasterModel;
import com.toedter.calendar.JDateChooser;

public class ExpenseTranEditScreen extends JPanel {
	
	private static final long serialVersionUID = 1L;
	public JLabel expenseNameLbl, datelbl,descriptionLbl,amountLbl,expenseAccountlbl;
	public JComboBox<String> expenseNameComboBox,accountNameComboBox;
	JDateChooser datechooser;
	public JTextField amountTxtBox,descriptionTxtBox;
	
	ExpenseTranModel expenseModel;
	ArrayList<IncomeMasterModel> lstincomeModel;
	Connection connection;
	DbConnection dbconn = new DbConnection();
	String trankeycode;

	int x = 500, y = 200, lblwidth = 100, height = 20, txtwidth = 200, combowidth = 75;

	public ExpenseTranEditScreen() {

		setBounds(CommonValues.contentPanelX, CommonValues.contentPanelY, CommonValues.screenWidth,
				CommonValues.screenHeight);
		createMenus();

	}

	public void updateValues() {
		try {
			
			//LAST WORK
			connection = dbconn.createTranConnection();
			
			String query ="select keycode from demo.expensemaster where name='"+expenseNameComboBox.getSelectedItem()+"'";
			Statement statement  = connection.createStatement();
			ResultSet rs1 = statement.executeQuery(query);
			if(rs1.next()) {
				
				String query1 = "update expensetran set description='" + descriptionTxtBox.getText().toString() + "', "
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
				String query = "select i.name,t.trandate,t.amount,t.description from expensetran t left join demo.expensemaster as i on i.keyCode=t.keycode where t.trankey='"+trankey+"' order by trandate;";
				Statement statement = connection.createStatement();
				ResultSet rs1 = statement.executeQuery(query);
				if (rs1.next()) {
					expenseNameComboBox.setSelectedItem(rs1.getString("name"));
					datechooser.setDate(rs1.getDate("trandate"));
					descriptionTxtBox.setText(rs1.getString("description"));
					amountTxtBox.setText(rs1.getString("amount"));
				}

			

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ExpenseTranModel setValuesForExpenseModel() {

		expenseModel = new ExpenseTranModel();
		if (!amountTxtBox.getText().toString().equalsIgnoreCase(""))
			expenseModel.setExpenseName(amountTxtBox.getText());
		else
			JOptionPane.showMessageDialog(getParent(), "ExpenseName is Empty");

//		if (expenseNameComboBox.getSelectedItem().toString().equalsIgnoreCase("YES"))
//			expenseModel.setActive("Y");
//		else
//			expenseModel.setActive("N");

		return expenseModel;

	}

	public void clearDetailsReqFocusToText() {
		amountTxtBox.setText("");
		expenseNameComboBox.setSelectedItem("YES");
		amountTxtBox.requestFocus();
	}

	public void closeThisPanelOpenTheRequestedPanel(JPanel panel) {

	}

	public void setObjectsVisibleFalse() {

		expenseNameLbl.setVisible(false);
		datelbl.setVisible(false);
		amountTxtBox.setVisible(false);
		expenseNameComboBox.setVisible(false);

	}

	public void setObjectsVisibleTrue() {

		expenseNameLbl.setVisible(true);
		datelbl.setVisible(true);
		amountTxtBox.setVisible(true);
		expenseNameComboBox.setVisible(true);

	}

	public void setObjectsEnabledFalse() {

		datelbl.setEnabled(false);
		amountTxtBox.setEnabled(false);
		expenseNameLbl.setEnabled(false);
		expenseNameComboBox.setEnabled(false);
	}

	public void setObjectsEnabledTrue() {

		datelbl.setEnabled(true);
		amountTxtBox.setEnabled(true);
		expenseNameLbl.setEnabled(true);
		expenseNameComboBox.setEnabled(true);

	}

	public void createMenus() {

		expenseNameLbl = new JLabel("Expense Name");
		expenseNameLbl.setBounds(x, y, lblwidth, height);
		add(expenseNameLbl);

		datelbl = new JLabel("Date");
		datelbl.setBounds(x, y += 50, lblwidth, height);
		add(datelbl);
		
		descriptionLbl = new JLabel("Description");
		descriptionLbl.setBounds(x, y +=50, lblwidth, height);
		add(descriptionLbl);
		
		
		amountLbl = new JLabel("Amount");
		amountLbl.setBounds(x, y +=50, lblwidth, height);
		add(amountLbl);
		
		expenseAccountlbl = new JLabel("Expense Account");
		expenseAccountlbl.setBounds(x, y += 50, lblwidth, height);
		add(expenseAccountlbl);


		y=200;
		expenseNameComboBox = new JComboBox<>();
		expenseNameComboBox.setBounds(x += lblwidth + 100, y, txtwidth, height);
		getincomedetails(expenseNameComboBox);
		expenseNameComboBox.requestFocus();
		add(expenseNameComboBox);

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
			String query = "select name as expenseName from demo.expensemaster;";
			Statement statement = connection.createStatement();
			ResultSet rs1 = statement.executeQuery(query);
			while (rs1.next()) {
				incomeNameComboBox.addItem(rs1.getString("expenseName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	private void getAccountdetails(JComboBox<String> accountNameComboBox) {
		try {
			connection = dbconn.createTranConnection();
			String query = "select name as accountname from demo.Accountmaster;";
			Statement statement = connection.createStatement();
			ResultSet rs1 = statement.executeQuery(query);
			while (rs1.next()) {
				accountNameComboBox.addItem(rs1.getString("accountname"));
			}
		} catch (Exception e) {

		}

	}

}
