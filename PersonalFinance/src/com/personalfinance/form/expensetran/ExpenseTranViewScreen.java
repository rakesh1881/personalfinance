package com.personalfinance.form.expensetran;

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
import com.personalfinance.model.ExpenseTranModel;
import com.personalfinance.model.IncomeMasterModel;
import com.personalfinance.model.IncomeTranModel;

public class ExpenseTranViewScreen  extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TopPanel topPanel;
	BottomPanel bottomPanel;
	//ExpenseTranModel incomeMasterModel;
	JTable incomeTbl;
	JScrollPane incomeScrollPane;
	ExpenseTranModel expenseModel;
	ArrayList<ExpenseTranModel> lstExpenseModel;
	Connection  connection;
	DbConnection dbcon = new DbConnection();
	

	public ExpenseTranViewScreen() {
		setBounds(CommonValues.contentPanelX, CommonValues.contentPanelY, CommonValues.screenWidth, CommonValues.screenHeight);
		setVisible(true);
		//createMenus();
	}

	public void getIncomeView() {
		try {
			dbcon = new DbConnection();
			connection = dbcon.createTranConnection();
			String query="select i.name,t.trandate,t.amount,t.description,t.trankey from expensetran t left join demo.expensemaster as i on i.keyCode=t.keycode order by trandate;";
			Statement statement = connection.createStatement();
			ResultSet rs1=statement.executeQuery(query);
			lstExpenseModel = new ArrayList<ExpenseTranModel>();
			while(rs1.next()) {
				expenseModel = new ExpenseTranModel();
				expenseModel.setExpenseName(rs1.getString("name"));
				expenseModel.setTrandate(rs1.getString("trandate"));
				expenseModel.setAmount(rs1.getDouble("amount"));
				expenseModel.setDescription(rs1.getString("description"));
				expenseModel.setTrankey(rs1.getString("trankey"));
				
				lstExpenseModel.add(expenseModel);
			}
			if(lstExpenseModel.size()>0) {
				addlistToJtable(lstExpenseModel);
			}else {
				JOptionPane.showMessageDialog(getParent(), "No Information To View");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void addlistToJtable(ArrayList<ExpenseTranModel> lstexpenseModel) {
		
		DefaultTableModel tablemodel = new DefaultTableModel();
		tablemodel.addColumn("Name");
		tablemodel.addColumn("Trandate");
		tablemodel.addColumn("Amount");
		tablemodel.addColumn("Description");
		tablemodel.addColumn("TranKey");
		

		for(ExpenseTranModel data : lstexpenseModel) {
			Vector<Object> row = new Vector<Object>();
			row.add(data.getExpenseName());
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
