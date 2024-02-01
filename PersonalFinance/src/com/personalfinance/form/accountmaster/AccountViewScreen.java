package com.personalfinance.form.accountmaster;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.personalfinance.common.BottomPanel;
import com.personalfinance.common.CommonValues;
import com.personalfinance.common.DbConnection;
import com.personalfinance.common.TopPanel;
import com.personalfinance.model.AccountMasterModel;

public class AccountViewScreen  extends JPanel{
	
	TopPanel topPanel;
	BottomPanel bottomPanel;
	AccountMasterModel AccountMasterModel;
	JTable AccountTbl;
	JScrollPane AccountScrollPane;
	AccountMasterModel Accountmodel;
	ArrayList<AccountMasterModel> lstAccountModel;
	Connection  connection;
	DbConnection dbcon = new DbConnection();
	

	public AccountViewScreen() {
		setBounds(CommonValues.contentPanelX, CommonValues.contentPanelY, CommonValues.screenWidth, CommonValues.screenHeight);
		setVisible(true);
		//createMenus();
	}

	public void getAccountView() {
		try {
			connection = dbcon.createMasterConnection();
			String query="Select name as Accountname,active,keycode  from AccountMaster;";
			Statement statement = connection.createStatement();
			ResultSet rs1=statement.executeQuery(query);
			lstAccountModel = new ArrayList<AccountMasterModel>();
			while(rs1.next()) {
				Accountmodel = new AccountMasterModel();
				Accountmodel.setAccountId(rs1.getString("keycode"));
				Accountmodel.setAccountName(rs1.getString("Accountname"));
				Accountmodel.setActive(rs1.getString("active"));
				
				lstAccountModel.add(Accountmodel);
			}
			if(lstAccountModel.size()>0) {
				addlistToJtable(lstAccountModel);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void addlistToJtable(ArrayList<AccountMasterModel> lstAccountModel) {
		
		DefaultTableModel tablemodel = new DefaultTableModel();
		tablemodel.addColumn("code");
		tablemodel.addColumn("Account Name");
		tablemodel.addColumn("Active");
		

		for(AccountMasterModel data : lstAccountModel) {
			Vector<Object> row = new Vector<Object>();
			row.add(data.getAccountId());
			row.add(data.getAccountName());
			row.add(data.getActive());
			
			tablemodel.addRow(row);
			
		}
		
        AccountTbl = new JTable(tablemodel);
		
		TableColumn tablecolumn = AccountTbl.getColumnModel().getColumn(0);
		tablecolumn.setPreferredWidth(1);
		TableColumn tablecolumn2 = AccountTbl.getColumnModel().getColumn(2);
		tablecolumn2.setPreferredWidth(1);
		AccountTbl.setBounds((CommonValues.screenWidth-300)/2,100, 300, 500);
		
	    AccountScrollPane = new JScrollPane(AccountTbl);
	    AccountScrollPane.setBounds((CommonValues.screenWidth-300)/2,100, 300, 500);
	    
	    add(AccountScrollPane);
		
		
		
		
		
	}
	
 public String getSelectedKeycode() {
	 
	 if(AccountTbl.getSelectedRow()==-1) {
		 JOptionPane.showMessageDialog(getParent(), "No Row Selected");
		 
		 }
		 
	 return  AccountTbl.getValueAt(AccountTbl.getSelectedRow(), 0).toString();
 }
	
	
}
