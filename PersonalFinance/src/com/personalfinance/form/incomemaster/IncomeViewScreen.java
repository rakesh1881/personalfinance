package com.personalfinance.form.incomemaster;

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
import com.personalfinance.model.IncomeMasterModel;

public class IncomeViewScreen  extends JPanel{
	
	TopPanel topPanel;
	BottomPanel bottomPanel;
	IncomeMasterModel incomeMasterModel;
	JTable incomeTbl;
	JScrollPane incomeScrollPane;
	IncomeMasterModel incomemodel;
	ArrayList<IncomeMasterModel> lstincomeModel;
	Connection  connection;
	DbConnection dbcon = new DbConnection();
	

	public IncomeViewScreen() {
		setBounds(CommonValues.contentPanelX, CommonValues.contentPanelY, CommonValues.screenWidth, CommonValues.screenHeight);
		setVisible(true);
		//createMenus();
	}

	public void getIncomeView() {
		try {
			connection = dbcon.createMasterConnection();
			String query="Select name as incomename,active,keycode  from incomeMaster;";
			Statement statement = connection.createStatement();
			ResultSet rs1=statement.executeQuery(query);
			lstincomeModel = new ArrayList<IncomeMasterModel>();
			while(rs1.next()) {
				incomemodel = new IncomeMasterModel();
				incomemodel.setIncomeId(rs1.getString("keycode"));
				incomemodel.setIncomeName(rs1.getString("incomename"));
				incomemodel.setActive(rs1.getString("active"));
				
				lstincomeModel.add(incomemodel);
			}
			if(lstincomeModel.size()>0) {
				addlistToJtable(lstincomeModel);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void addlistToJtable(ArrayList<IncomeMasterModel> lstincomeModel) {
		
		DefaultTableModel tablemodel = new DefaultTableModel();
		tablemodel.addColumn("code");
		tablemodel.addColumn("Income Name");
		tablemodel.addColumn("Active");
		

		for(IncomeMasterModel data : lstincomeModel) {
			Vector<Object> row = new Vector<Object>();
			row.add(data.getIncomeId());
			row.add(data.getIncomeName());
			row.add(data.getActive());
			
			tablemodel.addRow(row);
			
		}
		
        incomeTbl = new JTable(tablemodel);
		
		TableColumn tablecolumn = incomeTbl.getColumnModel().getColumn(0);
		tablecolumn.setPreferredWidth(1);
		TableColumn tablecolumn2 = incomeTbl.getColumnModel().getColumn(2);
		tablecolumn2.setPreferredWidth(1);
		incomeTbl.setBounds((CommonValues.screenWidth-300)/2,100, 300, 500);
		
	    incomeScrollPane = new JScrollPane(incomeTbl);
	    incomeScrollPane.setBounds((CommonValues.screenWidth-300)/2,100, 300, 500);
	    
	    add(incomeScrollPane);
			
	}
	
 public String getSelectedKeycode() {
	 
	 if(incomeTbl.getSelectedRow()==-1) {
		 JOptionPane.showMessageDialog(getParent(), "No Row Selected");
		 
		 }
		 
	 return  incomeTbl.getValueAt(incomeTbl.getSelectedRow(), 0).toString();
 }
	
	
}
