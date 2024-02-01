package com.personalfinance.form.incometran;

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
import com.personalfinance.model.IncomeTranModel;

public class IncomeTranViewScreen  extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TopPanel topPanel;
	BottomPanel bottomPanel;
	IncomeMasterModel incomeMasterModel;
	JTable incomeTbl;
	JScrollPane incomeScrollPane;
	IncomeTranModel incomemodel;
	ArrayList<IncomeTranModel> lstincomeModel;
	Connection  connection;
	DbConnection dbcon = new DbConnection();
	

	public IncomeTranViewScreen() {
		setBounds(CommonValues.contentPanelX, CommonValues.contentPanelY, CommonValues.screenWidth, CommonValues.screenHeight);
		setVisible(true);
		//createMenus();
	}

	public void getIncomeView() {
		try {
			dbcon = new DbConnection();
			connection = dbcon.createTranConnection();
			String query="select i.name,t.trandate,t.amount,t.description,t.trankey from incometran t left join demo.incomemaster as i on i.keyCode=t.keycode order by trandate;";
			Statement statement = connection.createStatement();
			ResultSet rs1=statement.executeQuery(query);
			lstincomeModel = new ArrayList<IncomeTranModel>();
			while(rs1.next()) {
				incomemodel = new IncomeTranModel();
				incomemodel.setIncomeName(rs1.getString("name"));
				incomemodel.setTrandate(rs1.getString("trandate"));
				incomemodel.setAmount(rs1.getDouble("amount"));
				incomemodel.setDescription(rs1.getString("description"));
				incomemodel.setTrankey(rs1.getString("trankey"));
				
				lstincomeModel.add(incomemodel);
			}
			if(lstincomeModel.size()>0) {
				addlistToJtable(lstincomeModel);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void addlistToJtable(ArrayList<IncomeTranModel> lstincomeModel) {
		
		DefaultTableModel tablemodel = new DefaultTableModel();
		tablemodel.addColumn("Name");
		tablemodel.addColumn("Trandate");
		tablemodel.addColumn("Amount");
		tablemodel.addColumn("Description");
		tablemodel.addColumn("TranKey");
		

		for(IncomeTranModel data : lstincomeModel) {
			Vector<Object> row = new Vector<Object>();
			row.add(data.getIncomeName());
			row.add(data.getTrandate());
			row.add(data.getAmount());
			row.add(data.getDescription());
			row.add(data.getTrankey());
			
			tablemodel.addRow(row);
			
			
		}
		
        incomeTbl = new JTable(tablemodel);
		
		TableColumn name = incomeTbl.getColumnModel().getColumn(0);
		name.setPreferredWidth(5);
		TableColumn trandate = incomeTbl.getColumnModel().getColumn(1);
		trandate.setPreferredWidth(5);
		TableColumn amount = incomeTbl.getColumnModel().getColumn(2);
		amount.setPreferredWidth(5);
		
		TableColumn description = incomeTbl.getColumnModel().getColumn(3);
		description.setPreferredWidth(500);
		
		TableColumn trankey = incomeTbl.getColumnModel().getColumn(4);
		trankey.setPreferredWidth(5);
		incomeTbl.setBounds((CommonValues.screenWidth-1000)/2,100, 1000, 500);
		
		
	    incomeScrollPane = new JScrollPane(incomeTbl);
	    incomeScrollPane.setBounds((CommonValues.screenWidth-1000)/2,100, 1000, 500);
	    
	    add(incomeScrollPane);
			
	}
	
 public String getSelectedKeycode() {
	 
	 if(incomeTbl.getSelectedRow()==-1) {
		 JOptionPane.showMessageDialog(getParent(), "No Row Selected");
		 }
	 return  incomeTbl.getValueAt(incomeTbl.getSelectedRow(), 4).toString();
 }
	
	
}
