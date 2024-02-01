package com.personalfinance.form.Accounttran;

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
import com.personalfinance.model.AccountTranModel;

public class AccountTranViewScreen  extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TopPanel topPanel;
	BottomPanel bottomPanel;
	AccountTranModel accountTranModel;
	JTable accountTbl;
	JScrollPane accountScrollPane;
	AccountTranModel accountmodel;
	ArrayList<AccountTranModel> lstaccountModel;
	Connection  connection;
	DbConnection dbcon = new DbConnection();
	

	public AccountTranViewScreen() {
		setBounds(CommonValues.contentPanelX, CommonValues.contentPanelY, CommonValues.screenWidth, CommonValues.screenHeight);
		setVisible(true);
		//createMenus();
	}

	public void getAccountView() {
		try {
			dbcon = new DbConnection();
			connection = dbcon.createTranConnection();
			String query="select i.name,t.trandate,t.openingamount,t.description,t.trankey from accounttran t left join demo.accountmaster as i on i.keyCode=t.keycode order by trandate;";
			Statement statement = connection.createStatement();
			ResultSet rs1=statement.executeQuery(query);
			lstaccountModel = new ArrayList<AccountTranModel>();
			while(rs1.next()) {
				accountmodel = new AccountTranModel();
				accountmodel.setAccountName(rs1.getString("name"));
				accountmodel.setTrandate(rs1.getString("trandate"));
				accountmodel.setAmount(rs1.getDouble("openingamount"));
				accountmodel.setDescription(rs1.getString("description"));
				accountmodel.setTrankey(rs1.getString("trankey"));
				
				lstaccountModel.add(accountmodel);
			}
			if(lstaccountModel.size()>0) {
				addlistToJtable(lstaccountModel);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void addlistToJtable(ArrayList<AccountTranModel> lstaccountModel) {
		
		DefaultTableModel tablemodel = new DefaultTableModel();
		tablemodel.addColumn("Name");
		tablemodel.addColumn("Trandate");
		tablemodel.addColumn("Amount");
		tablemodel.addColumn("Description");
		tablemodel.addColumn("TranKey");
		

		for(AccountTranModel data : lstaccountModel) {
			Vector<Object> row = new Vector<Object>();
			row.add(data.getAccountName());
			row.add(data.getTrandate());
			row.add(data.getAmount());
			row.add(data.getDescription());
			row.add(data.getTrankey());
			
			tablemodel.addRow(row);
			
			
		}
		
        accountTbl = new JTable(tablemodel);
		
		TableColumn name = accountTbl.getColumnModel().getColumn(0);
		name.setPreferredWidth(5);
		TableColumn trandate = accountTbl.getColumnModel().getColumn(1);
		trandate.setPreferredWidth(5);
		TableColumn amount = accountTbl.getColumnModel().getColumn(2);
		amount.setPreferredWidth(5);
		
		TableColumn description = accountTbl.getColumnModel().getColumn(3);
		description.setPreferredWidth(500);
		
		TableColumn trankey = accountTbl.getColumnModel().getColumn(4);
		trankey.setPreferredWidth(5);
		accountTbl.setBounds((CommonValues.screenWidth-1000)/2,100, 1000, 500);
		
		
	    accountScrollPane = new JScrollPane(accountTbl);
	    accountScrollPane.setBounds((CommonValues.screenWidth-1000)/2,100, 1000, 500);
	    
	    add(accountScrollPane);
			
	}
	
 public String getSelectedKeycode() {
	 
	 if(accountTbl.getSelectedRow()==-1) {
		 JOptionPane.showMessageDialog(getParent(), "No Row Selected");
		 }
	 return  accountTbl.getValueAt(accountTbl.getSelectedRow(), 4).toString();
 }
	
	
}
