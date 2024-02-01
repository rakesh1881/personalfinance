package com.personalfinance.form.expensemaster;

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
import com.personalfinance.model.ExpenseMasterModel;

public class ExpenseViewScreen  extends JPanel{
	
	TopPanel topPanel;
	BottomPanel bottomPanel;
	ExpenseMasterModel expenseMasterModel;
	JTable expenseTbl;
	JScrollPane expenseScrollPane;
	ExpenseMasterModel expensemodel;
	ArrayList<ExpenseMasterModel> lstexpenseModel;
	Connection  connection;
	DbConnection dbcon = new DbConnection();
	

	public ExpenseViewScreen() {
		setBounds(CommonValues.contentPanelX, CommonValues.contentPanelY, CommonValues.screenWidth, CommonValues.screenHeight);
		setVisible(true);
		//createMenus();
	}

	public void getExpenseView() {
		try {
			connection = dbcon.createMasterConnection();
			String query="Select name as expensename,active,keycode  from expenseMaster;";
			Statement statement = connection.createStatement();
			ResultSet rs1=statement.executeQuery(query);
			lstexpenseModel = new ArrayList<ExpenseMasterModel>();
			while(rs1.next()) {
				expensemodel = new ExpenseMasterModel();
				expensemodel.setExpenseId(rs1.getString("keycode"));
				expensemodel.setExpenseName(rs1.getString("expensename"));
				expensemodel.setActive(rs1.getString("active"));
				
				lstexpenseModel.add(expensemodel);
			}
			if(lstexpenseModel.size()>0) {
				addlistToJtable(lstexpenseModel);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void addlistToJtable(ArrayList<ExpenseMasterModel> lstexpenseModel) {
		
		DefaultTableModel tablemodel = new DefaultTableModel();
		tablemodel.addColumn("code");
		tablemodel.addColumn("Expense Name");
		tablemodel.addColumn("Active");
		

		for(ExpenseMasterModel data : lstexpenseModel) {
			Vector<Object> row = new Vector<Object>();
			row.add(data.getExpenseId());
			row.add(data.getExpenseName());
			row.add(data.getActive());
			
			tablemodel.addRow(row);
			
		}
		
        expenseTbl = new JTable(tablemodel);
		
		TableColumn tablecolumn = expenseTbl.getColumnModel().getColumn(0);
		tablecolumn.setPreferredWidth(1);
		TableColumn tablecolumn2 = expenseTbl.getColumnModel().getColumn(2);
		tablecolumn2.setPreferredWidth(1);
		expenseTbl.setBounds((CommonValues.screenWidth-300)/2,100, 300, 500);
		
	    expenseScrollPane = new JScrollPane(expenseTbl);
	    expenseScrollPane.setBounds((CommonValues.screenWidth-300)/2,100, 300, 500);
	    
	    add(expenseScrollPane);
		
		
		
		
		
	}
	
 public String getSelectedKeycode() {
	 
	 if(expenseTbl.getSelectedRow()==-1) {
		 JOptionPane.showMessageDialog(getParent(), "No Row Selected");
		 
		 }
		 
	 return  expenseTbl.getValueAt(expenseTbl.getSelectedRow(), 0).toString();
 }
	
	
}
